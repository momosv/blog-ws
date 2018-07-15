package cn.momosv.blog.ws.controller;

import cn.momosv.blog.ws.model.TbUserPO;

import cn.momosv.blog.ws.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
public class WSController {

    @Autowired
    UserService userService;

    @RequestMapping("/addUser")
    public String addUser(){
        TbUserPO userPO = new TbUserPO();
        userPO.setId(UUID.randomUUID().toString());
        userPO.setUserName("momo");
        userService.insertOne(userPO);
        TbUserPO userPO1= userService.selectByPrimaryKey1(userPO.getId());
        return userPO1.toString();
    }
}
