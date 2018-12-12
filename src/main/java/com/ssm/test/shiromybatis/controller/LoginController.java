package com.ssm.test.shiromybatis.controller;

import com.ssm.test.shiromybatis.exception.ErrorException;
import com.ssm.test.shiromybatis.pojo.ReturnData;
import com.ssm.test.shiromybatis.pojo.User;
import com.ssm.test.shiromybatis.service.LoginService;
import com.ssm.test.shiromybatis.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author ASUS
 */
@Controller
public class LoginController {

    @Autowired
    private LoginService loginService;
    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public ResponseEntity login(@RequestParam String username, @RequestParam String password) throws ErrorException {
        ReturnData data = loginService.auth(username,password);
        if (data.getCode() == "200") {
            User user = userService.getUserByName(username);
            user.setPassword("null");
            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.badRequest().body(data.getMsg());
        }
    }

    @GetMapping("/logout")
    public ResponseEntity logout() {
        try {
            Subject subject = SecurityUtils.getSubject();
            subject.logout();
            return ResponseEntity.ok("退出成功");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("退出失败");
        }
    }
}
