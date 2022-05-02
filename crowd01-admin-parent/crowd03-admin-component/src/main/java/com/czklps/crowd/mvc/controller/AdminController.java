package com.czklps.crowd.mvc.controller;

import com.czklps.crowd.constant.CrowdConstant;
import com.czklps.crowd.entity.Admin;
import com.czklps.crowd.exception.LoginFailedException;
import com.czklps.crowd.service.api.AdminService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@Controller
public class AdminController {
    @Autowired
    private AdminService adminService;

    @RequestMapping(value = "/admin/doLogin", method = RequestMethod.POST)
    public String doLogin(Admin data, HttpSession session) throws LoginFailedException {
        Admin admin = adminService.getAdminByLoginAcct(data);

        session.setAttribute(CrowdConstant.ATTR_NAME_LOGIN_ADMIN, admin);
        return "redirect:/admin/main";
    }

    @RequestMapping(value = "/admin/add", method = RequestMethod.POST)
    public String add(Admin data){
        adminService.saveAdmin(data);
        return "redirect:/admin?pageNum="+Integer.MAX_VALUE;
    }

    @RequestMapping(value = "/admin/checkout", method = RequestMethod.GET)
    public String checkout(HttpSession session) {
        session.removeAttribute(CrowdConstant.ATTR_NAME_LOGIN_ADMIN);
        return "redirect:/admin/login";
    }

    @RequestMapping("/admin")
    public String getPageInfo(
            @RequestParam(value = "keyword",defaultValue = "") String keyword,
            @RequestParam(value = "pageNum",defaultValue = "1") Integer pageNum,
            @RequestParam(value = "pageSize",defaultValue = "5") Integer pageSize,
            Model model) {
        PageInfo<Admin> pageInfo = adminService.getPageInfo(keyword, pageNum, pageSize);
        model.addAttribute(CrowdConstant.ATTR_NAME_PAGE_INFO,pageInfo);
        return "admin-page";
    }

    @RequestMapping(value = {"/admin/{id}/{pageNum}","/admin/{id}/{pageNum}/{keyword}"},method = RequestMethod.DELETE)
    public String remove(
            @PathVariable("id") Integer adminId,
            @PathVariable("pageNum") Integer pageNum,
            @PathVariable(value = "keyword",required = false) String keyword
    ){
        if(keyword == null || keyword.equals("null")) keyword = "";
        adminService.remove(adminId);
        return "redirect:/admin?pageNum="+pageNum+"&keyword="+keyword;
    }

}
