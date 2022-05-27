package com.czklps.crowd.service.Impl;

import com.czklps.crowd.entity.Auth;
import com.czklps.crowd.entity.AuthExample;
import com.czklps.crowd.mapper.AuthMapper;
import com.czklps.crowd.service.api.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service("authService")
public class AuthServiceImpl implements AuthService {

    @Autowired
    private AuthMapper authMapper;

    @Override
    public List<Auth> getAllAuth() {
        return authMapper.selectByExample(new AuthExample());
    }

    @Override
    public List<Integer> getAssignAuthIdByRoleId(Integer roleId) {
        return authMapper.selectAuthIdByRoleId(roleId);
    }

    @Override
    public void saveRoleAuthRelationship(Map<String, List<Integer>> map) {
        List<Integer> roleIdList = map.get("roleId");

        Integer roleId = roleIdList.get(0);

        authMapper.deleteOldRelationship(roleId);

        List<Integer> authIdArray = map.get("authIdArray");

        if (authIdArray != null && authIdArray.size() != 0) {
            authMapper.insertNewRelationship(roleId, authIdArray);
        }

    }

    @Override
    public List<String> getAssignAuthIdByAdminId(Integer adminId) {
        return authMapper.selectAssignAuthIdByAdminId(adminId);
    }

}
