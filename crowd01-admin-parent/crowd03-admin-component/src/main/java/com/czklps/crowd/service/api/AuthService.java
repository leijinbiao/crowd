package com.czklps.crowd.service.api;

import com.czklps.crowd.entity.Auth;

import java.util.List;
import java.util.Map;

public interface AuthService {

    List<Auth> getAllAuth();

    List<Integer> getAssignAuthIdByRoleId(Integer roleId);

    void saveRoleAuthRelationship(Map<String, List<Integer>> map);

    List<String> getAssignAuthIdByAdminId(Integer adminId);
}
