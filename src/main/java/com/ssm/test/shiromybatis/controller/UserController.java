package com.ssm.test.shiromybatis.controller;

import com.ssm.test.shiromybatis.pojo.User;
import com.ssm.test.shiromybatis.service.UserService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author ASUS
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/{id}")
    public ResponseEntity getUser(@PathVariable int id) {
        try {
            User user = userService.getUserById(id);
            if (user != null) {
                return ResponseEntity.ok(user);
            } else {
                return ResponseEntity.badRequest().body("没有该用户！");
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("系统错误");
        }
    }

    @RequiresPermissions("user:list")
    @GetMapping("")
    public ResponseEntity listUser() {
        try {
            List<User> users = userService.listUser();
            if (users.size() > 0) {
                return ResponseEntity.ok(users);
            } else {
                return ResponseEntity.badRequest().body("空");
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("获取用户列表失败");
        }
    }

    @PostMapping("")
    public ResponseEntity createUser(@RequestBody User user) {
        try {
            int id = userService.saveUser(user);
            User user1 = userService.getUserById(id);
            return ResponseEntity.ok(user1);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("保存失败");
        }
    }

}
