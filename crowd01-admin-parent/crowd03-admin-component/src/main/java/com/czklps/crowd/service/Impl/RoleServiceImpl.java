package com.czklps.crowd.service.Impl;

import com.czklps.crowd.entity.Admin;
import com.czklps.crowd.entity.Role;
import com.czklps.crowd.entity.RoleExample;
import com.czklps.crowd.mapper.RoleMapper;
import com.czklps.crowd.service.api.RoleService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("roleService")
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleMapper roleMapper;

    @Override
    public List<Role> getAll() {
        return roleMapper.selectByExample(new RoleExample());
    }

    @Override
    public PageInfo<Role> getPageInfo(String keyword, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        List<Role> list = roleMapper.selectByKeyword(keyword);
        return new PageInfo<>(list);
    }

    @Override
    public void saveRole(Role role) {
        roleMapper.insert(role);
    }

    @Override
    public void udpateRole(Role role) {
        roleMapper.updateByPrimaryKey(role);
    }

    @Override
    public void removeRole(List<Integer> array) {
        RoleExample roleExample = new RoleExample();
        RoleExample.Criteria criteria = roleExample.createCriteria();
        criteria.andIdIn(array);
        roleMapper.deleteByExample(roleExample);
    }

    @Override
    public List<Role> getAssignedRole(Integer adminId) {
        return roleMapper.selectAssignedRole(adminId);
    }

    @Override
    public List<Role> getUnAssignRole(Integer adminId) {
        return roleMapper.selectUnAssignRole(adminId);
    }

}
