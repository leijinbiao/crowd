package com.czklps.crowd.mapper;

import com.czklps.crowd.entity.Admin;
import com.czklps.crowd.entity.Menu;
import com.czklps.crowd.entity.Role;
import com.czklps.crowd.exception.LoginAcctAlreadyInUseException;
import com.czklps.crowd.service.api.AdminService;
import com.czklps.crowd.service.api.MenuService;
import com.czklps.crowd.service.api.RoleService;
import com.czklps.crowd.util.CrowdUtil;
import com.google.gson.Gson;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring-persist-mybatis.xml", "classpath:spring-persist-tx.xml"})
public class AdminMapperTest {

    @Autowired
    private AdminService adminService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private MenuService menuService;

    @Test
    public void getAll() {
        List<Menu> list = menuService.getAll();
        System.out.println(list);
        Menu root = null;
        Map<Integer, Menu> map = new HashMap();
        for (Menu menu : list) {
            map.put(menu.getId(), menu);
        }

        for (Menu menu : list) {
            if(menu.getPid() == 0){
                root = menu;
                continue;
            }
            Menu parentNode = map.get(menu.getPid());
            parentNode.getChildren().add(menu);

        }
        Gson gson = new Gson();
        System.out.println(gson.toJson(root));
//        System.out.println(buildTree(list, 0));
    }

    /**
     * 根据 list 转换成 tree
     *
     * @param list list数据
     * @param pid  root的pid
     * @return 返回生成树形结构的 list
     */
    private List<Menu> buildTree(List<Menu> list, Integer pid) {
        // 定义一个空的 list 存放生成的 tree
        List<Menu> tree = new ArrayList<>();
        // 遍历这个 list 数据
        for (Menu menu : list) {
            //根据 @param pid 查找 root Node
            if (Objects.equals(menu.getPid(), pid)) {
                // 如果查找到调用 @Method findChild 方法递归查找子节点
                tree.add(findChild(menu, list));
            }
        }
        return tree;
    }

    /**
     * 递归查找子节点
     *
     * @param menu 父节点
     * @param list list数据
     * @return 返回一个子节点
     */
    private Menu findChild(Menu menu, List<Menu> list) {
        // 遍历这个 list 数据
        for (Menu m : list) {
            //根据 parent Node 的 id 查找子节点
            //如果子节点的 pid 与 parent Node 的 id相等
            if (Objects.equals(m.getPid(), menu.getId())) {
                //如果相等那么添加在 parent Node 上
                //并且查找当前的节点是否有子节点
                menu.getChildren().add(findChild(m, list));
            }
        }
        //返回节点
        return menu;
    }

    @Test
    public void insert() throws LoginAcctAlreadyInUseException {
        System.out.println(CrowdUtil.md5("123456"));
//        for (int i = 0; i < 123; i++) {
//            Admin admin = new Admin(null, "jerry" + i, "123456" + i, "杰瑞" + i, "jerry" + i + "@qq.com", null);
//            adminService.saveAdmin(admin);
//            Role role = new Role(null, "role" + i);
//            roleService.saveRole(role);
//        }
//        Admin admin = new Admin(null, "jerry", "123456", "杰瑞", "jerry@qq.com", null);
//        adminService.saveAdmin(admin);

    }

}