package com.czklps.crowd.mvc.controller;

import com.czklps.crowd.constant.CrowdConstant;
import com.czklps.crowd.entity.Admin;
import com.czklps.crowd.exception.AccessForbiddenException;
import com.czklps.crowd.exception.LoginAcctAlreadyInUseException;
import com.czklps.crowd.exception.LoginAcctAlreadyInUseForUpdateException;
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
        return "redirect:/main";
    }

    @RequestMapping(value = "/admin/add", method = RequestMethod.POST)
    public String add(Admin data) throws LoginAcctAlreadyInUseException {
        adminService.saveAdmin(data);
        return "redirect:/admin?pageNum=" + Integer.MAX_VALUE;
    }

    @RequestMapping(value = {"/admin/toEdit/{id}/{pageNum}","/admin/toEdit/{id}/{pageNum}/{keyword}"})
    public String toEdit(@PathVariable Integer id,
                         @PathVariable(value = "pageNum") Integer pageNum,
                         @PathVariable(value = "keyword", required = false) String keyword,
                         Model model) {
        Admin admin = adminService.getAdminById(id);
        if (keyword == null || keyword.equals("null")) keyword = "";

        model.addAttribute("admin", admin);
        model.addAttribute("keyword",keyword);
        model.addAttribute("pageNum",pageNum);

        return "admin-edit";
    }

    @RequestMapping(value = "/admin/edit", method = RequestMethod.PUT)
    public String edit(@RequestParam(value = "keyword", required = false) String keyword,
                       @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                       Admin data) throws LoginAcctAlreadyInUseForUpdateException {
        System.out.println(pageNum);
        if (keyword == null || keyword.equals("null")) keyword = "";
        adminService.editAdmin(data);
        return "redirect:/admin?pageNum=" + pageNum + "&keyword=" + keyword;
    }

    @RequestMapping(value = "/admin/checkout", method = RequestMethod.GET)
    public String checkout(HttpSession session) {
        session.removeAttribute(CrowdConstant.ATTR_NAME_LOGIN_ADMIN);
        return "redirect:/admin/login";
    }

    @RequestMapping("/admin")
    public String getPageInfo(
            @RequestParam(value = "keyword", defaultValue = "") String keyword,
            @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
            @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
            Model model) {
        PageInfo<Admin> pageInfo = adminService.getPageInfo(keyword, pageNum, pageSize);
        model.addAttribute(CrowdConstant.ATTR_NAME_PAGE_INFO, pageInfo);
        return "admin-page";
    }

    @RequestMapping(value = {"/admin/{id}/{pageNum}", "/admin/{id}/{pageNum}/{keyword}"}, method = RequestMethod.DELETE)
    public String remove(
            @PathVariable("id") Integer adminId,
            @PathVariable("pageNum") Integer pageNum,
            @PathVariable(value = "keyword", required = false) String keyword
    ) {
        if (keyword == null || keyword.equals("null")) keyword = "";
        adminService.remove(adminId);
        return "redirect:/admin?pageNum=" + pageNum + "&keyword=" + keyword;
    }

}
