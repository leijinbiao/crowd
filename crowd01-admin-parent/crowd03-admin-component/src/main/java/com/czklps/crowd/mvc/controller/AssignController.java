package com.czklps.crowd.mvc.controller;

import com.czklps.crowd.entity.Auth;
import com.czklps.crowd.entity.Role;
import com.czklps.crowd.service.api.AdminService;
import com.czklps.crowd.service.api.AuthService;
import com.czklps.crowd.service.api.RoleService;
import com.czklps.crowd.util.ResultEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

@Controller
public class AssignController {
    @Autowired
    private RoleService roleService;

    @Autowired
    private AdminService adminService;

    @Autowired
    private AuthService authService;

    @ResponseBody
    @RequestMapping("assign/do/role/assign/auth")
    public ResultEntity<String> saveRoleAuthRelationship(@RequestBody Map<String, List<Integer>> map){

        authService.saveRoleAuthRelationship(map);

        return ResultEntity.successWithoutData();
    }

    @ResponseBody
    @RequestMapping("/assign/auth/id/by/role/id")
    public ResultEntity<List<Integer>> getAssignAuthIdByRoleId(@RequestParam("roleId") Integer roleId){

        List<Integer> list = authService.getAssignAuthIdByRoleId(roleId);

        return ResultEntity.successWithData(list);
    }

    @ResponseBody
    @RequestMapping("/assign/auth")
    public ResultEntity<List<Auth>> getAllAuth(){

        List<Auth> list = authService.getAllAuth();

        return ResultEntity.successWithData(list);
    }

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
