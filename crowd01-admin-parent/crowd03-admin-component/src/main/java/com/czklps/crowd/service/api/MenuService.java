package com.czklps.crowd.service.api;

import com.czklps.crowd.entity.Menu;

import java.util.List;

public interface MenuService {
    List<Menu> getAll();
    Menu queryMenuById(Integer id);

    void saveMenu(Menu menu);

    void updateMenu(Menu menu);

    void removeMenuById(Integer id);
}
