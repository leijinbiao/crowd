package com.czklps.crowd.mvc.controller;

import com.czklps.crowd.entity.Menu;
import com.czklps.crowd.service.api.MenuService;
import com.czklps.crowd.util.ResultEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class MenuController {
    @Autowired
    private MenuService menuService;

    @RequestMapping("/menu/pageInfo")
    public ResultEntity<Menu> getPageInfo(){
        List<Menu> list = menuService.getAll();
        Map<Integer,Menu> map = new HashMap<>();
        Menu root = null;
        for (Menu menu : list) {

            Integer id = menu.getId();
            //将 list 的集合转换成 map 集合
            map.put(id, menu);

        }

        for (Menu menu : list) {
            Integer pid = menu.getPid();

            if(pid==0){
                root = menu;
                continue;
            }
            // 根据遍历的 menu 的 pid 到 map 中查找所对应的 Menu 对象
            Menu parentNode = map.get(pid);
            // 拿到父节点后添加子节点
            parentNode.getChildren().add(menu);
        }
        
        return ResultEntity.successWithData(root);
    }

    @RequestMapping("/menu/add")
    public ResultEntity<String> add(Menu menu){

        menuService.saveMenu(menu);

        return ResultEntity.successWithoutData();
    }

    @RequestMapping("/menu/update")
    public ResultEntity<String> update(Menu menu){

        menuService.updateMenu(menu);

        return ResultEntity.successWithoutData();
    }
    @RequestMapping("/menu/remove")
    public ResultEntity<String> remove(@RequestParam Integer id){

        menuService.removeMenuById(id);

        return ResultEntity.successWithoutData();
    }
}
