package com.example._20240904ordersysquardem00.controller;

import com.example._20240904ordersysquardem00.model.Menu;
import com.example._20240904ordersysquardem00.service.MenuService;
import com.example._20240904ordersysquardem00.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/menus")
public class MenuController {

    @Autowired
    private MenuService menuService;

    @Autowired
    private RestaurantService restaurantService;

    @GetMapping
    public String listMenus(Model model) {
        model.addAttribute("menus", menuService.getAllMenus());
        return "menus";
    }

    @GetMapping("/{id}")
    public String viewMenu(@PathVariable String id, Model model) {
        menuService.getMenuById(id).ifPresent(menu -> model.addAttribute("menu", menu));
        return "menu-details";
    }

    @GetMapping("/new")
    public String newMenuForm(Model model) {
        model.addAttribute("menu", new Menu());
        model.addAttribute("restaurants", restaurantService.getAllRestaurants());
        return "menu-form";
    }

    @PostMapping
    public String saveMenu(@ModelAttribute Menu menu) {
        menuService.saveMenu(menu);
        return "redirect:/menus";
    }

    @GetMapping("/{id}/edit")
    public String editMenuForm(@PathVariable String id, Model model) {
        menuService.getMenuById(id).ifPresent(menu -> model.addAttribute("menu", menu));
        model.addAttribute("restaurants", restaurantService.getAllRestaurants());
        return "menu-form";
    }

    @PostMapping("/{id}")
    public String updateMenu(@PathVariable String id, @ModelAttribute Menu menu) {
        menu.setMenuId(id);
        menuService.saveMenu(menu);
        return "redirect:/menus";
    }

    @GetMapping("/{id}/delete")
    public String deleteMenu(@PathVariable String id) {
        menuService.deleteMenu(id);
        return "redirect:/menus";
    }

    // API - get all menus
    @GetMapping("/api")
    @ResponseBody
    public List<Menu> getMenusApi() {
        return menuService.getAllMenus();
    }

    // API - get a single data from menus
    @GetMapping("/api/{id}")
    @ResponseBody
    public Menu getMenuApi(@PathVariable String id) {
        return menuService.getMenuById(id).orElse(null);
    }
    // API - create menu
    @PostMapping("/api")
    @ResponseBody
    public Menu createMenuApi(@RequestBody Menu menu) {
        return menuService.saveMenu(menu);
    }

    // API - update menu
    @PutMapping("/api/{id}")
    @ResponseBody
    public Menu updateMenuApi(@PathVariable String id, @RequestBody Menu menu) {
        menu.setMenuId(id);
        return menuService.saveMenu(menu);
    }
    // API - delete menu
    @DeleteMapping("/api/{id}")
    @ResponseBody
    public void deleteMenuApi(@PathVariable String id) {
        menuService.deleteMenu(id);
    }

}