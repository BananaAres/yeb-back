package com.sundaohan.server.controller;


import com.sundaohan.server.pojo.Menu;
import com.sundaohan.server.service.IAdminService;
import com.sundaohan.server.service.IMenuService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author sundaohan
 * @since 2021-07-21
 */
@RestController
@RequestMapping("system/cfg")
public class MenuController {

    @Autowired
    private IMenuService menuService;

    @ApiOperation(value = "通过用户id查询菜单列表")
    @GetMapping("menu")
    public List<Menu> getMenusByAdminId(){
        System.out.println("进入菜单请求");
        return menuService.getMenusByAdminId();
    }
}
