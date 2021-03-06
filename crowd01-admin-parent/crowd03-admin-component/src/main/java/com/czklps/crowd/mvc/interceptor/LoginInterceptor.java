package com.czklps.crowd.mvc.interceptor;

import com.czklps.crowd.constant.CrowdConstant;
import com.czklps.crowd.entity.Admin;
import com.czklps.crowd.exception.AccessForbiddenException;
import com.czklps.crowd.exception.LoginAcctAlreadyInUseException;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LoginInterceptor extends HandlerInterceptorAdapter {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws AccessForbiddenException {
        HttpSession session = request.getSession();

        Admin admin = (Admin) session.getAttribute(CrowdConstant.ATTR_NAME_LOGIN_ADMIN);

        if (admin == null) {

            throw new AccessForbiddenException(CrowdConstant.MESSAGE_ACCESS_FORBIDDEN);

        }

        return true;
    }

}
