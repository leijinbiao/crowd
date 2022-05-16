package com.czklps.crowd.mvc.controller;

import com.czklps.crowd.entity.Role;
import com.czklps.crowd.service.api.AdminService;
import com.czklps.crowd.service.api.RoleService;
import com.czklps.crowd.util.ResultEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class AssignController {
    @Autowired
    private RoleService roleService;

    @Autowired
    private AdminService adminService;

    @RequestMapping("/assign/to/assign/role/page")
    public String toAssignRolePage(
            @RequestParam(value = "keyword", defaultValue = "") String keyword,
            @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
            @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
            @RequestParam(value = "adminId") Integer adminId,
            ModelMap modelMap
            ){
        List<Role> assignedRoleList = roleService.getAssignedRole(adminId);
        List<Role> unAssignedRoleList = roleService.getUnAssignRole(adminId);

        modelMap.addAttribute("assignedRoleList",assignedRoleList);
        modelMap.addAttribute("unAssignRoleList",unAssignedRoleList);
        modelMap.addAttribute("adminId",adminId);
        modelMap.addAttribute("pageNum",pageNum);
        modelMap.addAttribute("keyword",keyword);
        modelMap.addAttribute("pageSize",pageSize);

        return "assignRole-page";
    }

    @RequestMapping("/assign/save")
    public String saveAdminRoleRelationship(
            @RequestParam(value = "keyword", defaultValue = "") String keyword,
            @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
            @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
            @RequestParam(value = "adminId") Integer adminId,
            @RequestParam(value = "",required = false) List<Integer> roleIdList
    ){

        adminService.saveAdminRoleRelationship(adminId,roleIdList);

        return "redirect:/admin?pageNum=" + pageNum + "&pageSize=" + pageSize + "&keyword=" + keyword;
    }
}
