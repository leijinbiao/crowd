package com.czklps.crowd.mvc.config;

import com.czklps.crowd.constant.CrowdConstant;
import com.czklps.crowd.exception.AccessForbiddenException;
import com.czklps.crowd.exception.LoginAcctAlreadyInUseException;
import com.czklps.crowd.exception.LoginAcctAlreadyInUseForUpdateException;
import com.czklps.crowd.exception.LoginFailedException;
import com.czklps.crowd.util.CrowdUtil;
import com.czklps.crowd.util.ResultEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@ControllerAdvice
public class CrowdExceptionResolver {
    /**
     * 处理处理修改账号已经存在的异常
     * @param exception
     * @param request
     * @param response
     * @return
     * @throws IOException
     */
    @ExceptionHandler(value = LoginAcctAlreadyInUseForUpdateException.class)
    public ModelAndView resolverLoginAcctAlreadyInUseForUpdateException(LoginAcctAlreadyInUseForUpdateException exception, HttpServletRequest request, HttpServletResponse response) throws IOException {
        String viewName = "error";
        return commonException(viewName, exception, request, response);
    }

    /**
     * 处理处理账号已经存在的异常
     * @param exception
     * @param request
     * @param response
     * @return
     * @throws IOException
     */
    @ExceptionHandler(value = LoginAcctAlreadyInUseException.class)
    public ModelAndView resolverLoginAcctAlreadyInUseException(LoginAcctAlreadyInUseException exception, HttpServletRequest request, HttpServletResponse response) throws IOException {
        String viewName = "admin-add";
        return commonException(viewName, exception, request, response);
    }

    /**
     * 处理处理非法获取资源的异常
     * @param exception
     * @param request
     * @param response
     * @return
     * @throws IOException
     */
    @ExceptionHandler(value = AccessForbiddenException.class)
    public ModelAndView resolverAccessForbiddenException(AccessForbiddenException exception, HttpServletRequest request, HttpServletResponse response) throws IOException {
        String viewName = "admin-login";
        return commonException(viewName, exception, request, response);
    }

    /**
     * 处理登录失败的异常
     * @param exception
     * @param request
     * @param response
     * @return
     * @throws IOException
     */
    @ExceptionHandler(value = LoginFailedException.class)
    public ModelAndView resolverLoginFailedException(LoginFailedException exception, HttpServletRequest request, HttpServletResponse response) throws IOException {
        String viewName = "admin-login";
        return commonException(viewName, exception, request, response);
    }
    /**
     * 代码进行优化将冗余的代码提取出来
     *
     * @param viewName
     * @param exception
     * @param request
     * @param response
     * @return
     * @throws IOException
     */
    private ModelAndView commonException(String viewName, Exception exception, HttpServletRequest request, HttpServletResponse response) throws IOException {
        boolean requestType = CrowdUtil.judgeRequestType(request);
        //如果时 ajax 请求则直接返回空
        if (requestType) {
            ResultEntity<Object> failed = ResultEntity.failed(exception.getMessage());
            response.setStatus(404);
            response.getWriter().print(failed);

            return null;
        }
        ModelAndView modelAndView = new ModelAndView();

        modelAndView.setViewName(viewName);

        modelAndView.addObject(CrowdConstant.ATTR_NAME_EXCEPTION, exception.getMessage());
        return modelAndView;
    }
}
