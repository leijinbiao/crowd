package com.czklps.crowd.mvc.config;

import com.czklps.crowd.entity.Admin;
import com.czklps.crowd.entity.Role;
import com.czklps.crowd.exception.LoginFailedException;
import com.czklps.crowd.service.api.AdminService;
import com.czklps.crowd.service.api.AuthService;
import com.czklps.crowd.service.api.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CrowdUserDetailsService implements UserDetailsService {
    @Autowired
    private AdminService adminService;
    @Autowired
    private RoleService roleService;
    @Autowired
    private AuthService authService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Admin admin = adminService.getAdminByLoginAcct(username);

        Integer adminId = admin.getId();

        List<Role> assignedRoleList = roleService.getAssignedRole(adminId);

        List<String> assignAuthList = authService.getAssignAuthIdByAdminId(adminId);

        List<GrantedAuthority> authorities = new ArrayList<>();

        for (Role r : assignedRoleList) {

            SimpleGrantedAuthority role = new SimpleGrantedAuthority("ROLE_" + r.getName());

            authorities.add(role);
        }
        
        for (String authName : assignAuthList) {
            
            SimpleGrantedAuthority authority = new SimpleGrantedAuthority(authName);

            authorities.add(authority);
        }

        SecurityAdmin securityAdmin = new SecurityAdmin(admin, authorities);

        return securityAdmin;
    }
}
