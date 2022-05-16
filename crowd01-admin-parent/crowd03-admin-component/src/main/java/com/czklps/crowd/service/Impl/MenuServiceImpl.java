package com.czklps.crowd.service.Impl;

import com.czklps.crowd.entity.Menu;
import com.czklps.crowd.entity.MenuExample;
import com.czklps.crowd.mapper.MenuMapper;
import com.czklps.crowd.service.api.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MenuServiceImpl implements MenuService {
    @Autowired
    private MenuMapper menuMapper;

    @Override
    public List<Menu> getAll() {
        return menuMapper.selectByExample(new MenuExample());
    }

    @Override
    public Menu queryMenuById(Integer id) {
        return menuMapper.selectByPrimaryKey(id);
    }

    @Override
    public void saveMenu(Menu menu) {
        menuMapper.insert(menu);
    }

    @Override
    public void updateMenu(Menu menu) {
        menuMapper.updateByPrimaryKeySelective(menu);
    }

    @Override
    public void removeMenuById(Integer id) {
        menuMapper.deleteByPrimaryKey(id);
    }

}
