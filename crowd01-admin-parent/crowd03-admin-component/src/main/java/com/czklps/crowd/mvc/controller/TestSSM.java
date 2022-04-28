package com.czklps.crowd.mvc.controller;

import com.czklps.crowd.entity.Admin;
import com.czklps.crowd.service.api.AdminService;
import com.czklps.crowd.util.CrowdUtil;
import com.czklps.crowd.util.ResultEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class TestSSM {
    @Autowired
    @Qualifier("adminService")
    private AdminService adminService;

    @RequestMapping("/testSSM")
    public String testSSM(Model model, HttpServletRequest request) {
        model.addAttribute("admin", adminService.getAll());
        int i = 10 / 0;
        return "success";
    }

    @RequestMapping("/testAjax")
    @ResponseBody
    public ResultEntity<List<Admin>> testAjax(@RequestBody List<Integer> array, HttpServletRequest request) {
        List<Admin> admins = adminService.getAll();

        return ResultEntity.successWithData(admins);
    }

}
