package com.sundaohan.server.controller;

import com.sundaohan.server.pojo.Admin;
import com.sundaohan.server.service.IAdminService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Auther sundaohan
 * @Package com.sundaohan.server.controller
 * @Title ChatController
 * @Description 在线聊天
 * @Date 2021/8/5 下午2:03
 */
@RestController
@RequestMapping("chat")
public class ChatController {

    @Autowired
    private IAdminService adminService;

    @ApiOperation(value = "获取所有操作员")
    @GetMapping("listAll")
    public List<Admin> getAllAdmin(String keywords){
        return adminService.getAllAdmins(keywords);
    }
}
