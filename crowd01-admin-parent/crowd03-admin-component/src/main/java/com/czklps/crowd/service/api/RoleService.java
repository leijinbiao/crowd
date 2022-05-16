package com.czklps.crowd.service.api;

import com.czklps.crowd.entity.Admin;
import com.czklps.crowd.entity.Role;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface RoleService {
    List<Role> getAll();

    PageInfo<Role> getPageInfo(String keyword, Integer pageNum, Integer pageSize);

    void saveRole(Role role);

    void udpateRole(Role role);

    void removeRole(List<Integer> arrayId);

    List<Role> getAssignedRole(Integer adminId);

    List<Role> getUnAssignRole(Integer adminId);
}
