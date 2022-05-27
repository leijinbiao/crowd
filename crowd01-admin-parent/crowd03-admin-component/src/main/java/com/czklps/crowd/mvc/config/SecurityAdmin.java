package com.czklps.crowd.mvc.config;

import com.czklps.crowd.entity.Admin;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

public class SecurityAdmin extends User implements Serializable {
    private Admin originAdmin;

    public SecurityAdmin(Admin originAdmin, List<GrantedAuthority> authorities) {
        super(originAdmin.getLoginAcct(), originAdmin.getUserPswd(),authorities);
        this.originAdmin = originAdmin;
    }

    public Admin getOriginAdmin() {
        return originAdmin;
    }

    public void setOriginAdmin(Admin originAdmin) {
        this.originAdmin = originAdmin;
    }
}
