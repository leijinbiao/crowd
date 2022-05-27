package com.czklps.crowd.mvc.controller;

import com.czklps.crowd.constant.CrowdConstant;
import com.czklps.crowd.entity.Admin;
import com.czklps.crowd.entity.Role;
import com.czklps.crowd.service.api.AdminService;
import com.czklps.crowd.service.api.RoleService;
import com.czklps.crowd.util.ResultEntity;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class RoleController {
    @Autowired
    private RoleService roleService;

    @PreAuthorize("hasRole('部门')")
    @ResponseBody
    @RequestMapping("/role/pageInfo")
    public ResultEntity<PageInfo<Role>> getPageInfo(
            @RequestParam(value = "keyword", defaultValue = "") String keyword,
            @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
            @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize
    ) {
        PageInfo<Role> pageInfo = roleService.getPageInfo(keyword, pageNum, pageSize);
        return ResultEntity.successWithData(pageInfo);
    }

    @ResponseBody
    @RequestMapping("/role/add")
    public ResultEntity<String> add(Role role) {

        roleService.saveRole(role);

        return ResultEntity.successWithoutData();
    }

    @ResponseBody
    @RequestMapping("/role/update")
    public ResultEntity<String> update(Role role) {

        roleService.udpateRole(role);

        return ResultEntity.successWithoutData();
    }

    @ResponseBody
    @RequestMapping("/role/remove")
    public ResultEntity<String> remove(@RequestBody List<Integer> array) {

        roleService.removeRole(array);

        return ResultEntity.successWithoutData();
    }

}
