package com.example._20240904ordersysquardem00.controller;

import com.example._20240904ordersysquardem00.model.Menu;
import com.example._20240904ordersysquardem00.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;


@Controller
@RequestMapping("/menus")
public class MenuController {

    @Autowired
    private MenuService menuService;

    @GetMapping
    public String listMenus(Model model) {
        List<Menu> menus = menuService.getAllMenus();
        model.addAttribute("menus", menus);
        return "menus";
    }

    @GetMapping("/{id}")
    public String getMenu(@PathVariable String id, Model model) {
        Menu menu = menuService.getMenuById(id).orElse(null);
        model.addAttribute("menu", menu);
        return "menu-details";
    }
}