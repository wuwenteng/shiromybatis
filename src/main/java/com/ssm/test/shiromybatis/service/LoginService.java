package com.ssm.test.shiromybatis.service;

import com.ssm.test.shiromybatis.pojo.ReturnData;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Service;

/**
 * @author ASUS
 */

@Service
public class LoginService {

    public ReturnData auth(String username, String password) {

        System.out.println("用户名：" + username);
        System.out.println("密码：" + password);

        ReturnData data = new ReturnData();
        UsernamePasswordToken token = new UsernamePasswordToken(username,password);

        Subject subject = SecurityUtils.getSubject();
        try {
            subject.login(token);
            data.setCode("200");
            data.setMsg("验证成功");
        } catch (AuthenticationException e) {
            data.setCode("400");
            data.setMsg("验证失败");
        }

        System.out.println(data);

        return data;
    }
}
