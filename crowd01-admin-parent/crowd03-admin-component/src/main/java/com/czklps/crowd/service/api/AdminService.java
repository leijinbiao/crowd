package com.czklps.crowd.service.api;


import com.czklps.crowd.entity.Admin;
import com.czklps.crowd.exception.LoginFailedException;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface AdminService {
    void saveAdmin(Admin admin);

    List<Admin> getAll();

    Admin getAdminByLoginAcct(Admin admin) throws LoginFailedException;

    PageInfo<Admin> getPageInfo(String keyword, Integer pageNum, Integer pageSize);

    void remove(Integer adminId);

}
