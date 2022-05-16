package com.czklps.crowd.service.Impl;

import com.czklps.crowd.constant.CrowdConstant;
import com.czklps.crowd.entity.Admin;
import com.czklps.crowd.entity.AdminExample;
import com.czklps.crowd.exception.LoginAcctAlreadyInUseException;
import com.czklps.crowd.exception.LoginAcctAlreadyInUseForUpdateException;
import com.czklps.crowd.exception.LoginFailedException;
import com.czklps.crowd.mapper.AdminMapper;
import com.czklps.crowd.service.api.AdminService;
import com.czklps.crowd.util.CrowdUtil;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Service("adminService")
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminMapper adminMapper;

    @Override
    public void saveAdmin(Admin admin) throws LoginAcctAlreadyInUseException {
        String password = CrowdUtil.md5(admin.getUserPswd());
        admin.setUserPswd(password);

        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-hh HH:mm:ss");
        String createTime = format.format(date);
        admin.setCreateTime(createTime);

        try {
            adminMapper.insert(admin);
        }catch (Exception e){
            if(e instanceof DuplicateKeyException) {
                throw new LoginAcctAlreadyInUseException(CrowdConstant.MESSAGE_LOGIN_ACCT_ALREADY_IN_USE);
            }
        }

    }

    @Override
    public List<Admin> getAll() {
        return adminMapper.selectByExample(new AdminExample());
    }

    /**
     * 通过 admin 来登录
     *
     * @param admin
     * @return
     * @throws LoginFailedException
     */
    @Override
    public Admin getAdminByLoginAcct(Admin admin) throws LoginFailedException {
        // 检查参数是否为空
        if (admin.getLoginAcct() == null || admin.getLoginAcct().length() == 0 ||
                admin.getUserPswd() == null || admin.getUserPswd().length() == 0) {
            throw new LoginFailedException(CrowdConstant.MESSAGE_LOGIN_FAILED);
        }

        AdminExample adminExample = new AdminExample();

        AdminExample.Criteria criteria = adminExample.createCriteria();

        criteria.andLoginAcctEqualTo(admin.getLoginAcct());

        List<Admin> list = adminMapper.selectByExample(adminExample);
        // 检查从数据库中查出的数据是否有值
        if (list == null || list.size() == 0) {
            throw new LoginFailedException(CrowdConstant.MESSAGE_LOGIN_FAILED);
        }

        // 如果查出为多个值的话
        if (list.size() > 1) {
            throw new RuntimeException(CrowdConstant.MESSAGE_SYSTEM_ERROR_LOGIN_NOT_UNIQUE);
        }
        // 数据库中的密码
        String userPswd = list.get(0).getUserPswd();
        // form 表单中的密码
        String userPswdForm = admin.getUserPswd();
        // 将密码转换成md5的格式
        String md5UserPswdForm = CrowdUtil.md5(userPswdForm);
        // 与数据库中的密码比较
        if (!Objects.equals(userPswd, md5UserPswdForm)) {
            throw new LoginFailedException(CrowdConstant.MESSAGE_LOGIN_FAILED);
        }
        // 返回这个对象
        return list.get(0);
    }

    @Override
    public PageInfo<Admin> getPageInfo(String keyword, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Admin> list = adminMapper.selectByKeyword(keyword);
        return new PageInfo<>(list);
    }

    @Override
    public void remove(Integer adminId) {
        adminMapper.deleteByPrimaryKey(adminId);
    }

    @Override
    public Admin getAdminById(Integer id) {
        return adminMapper.selectByPrimaryKey(id);
    }

    @Override
    public void editAdmin(Admin data) throws LoginAcctAlreadyInUseForUpdateException {
        try {
            adminMapper.updateByPrimaryKeySelective(data);
        }catch (Exception e){
            if(e instanceof DuplicateKeyException) {
                throw new LoginAcctAlreadyInUseForUpdateException(CrowdConstant.MESSAGE_LOGIN_ACCT_ALREADY_IN_USE);
            }
        }
    }

    @Override
    public void saveAdminRoleRelationship(Integer adminId, List<Integer> roleIdList) {
        adminMapper.deleteOldRelationship(adminId);
        adminMapper.insertNewRelationship(adminId,roleIdList);
    }

}
