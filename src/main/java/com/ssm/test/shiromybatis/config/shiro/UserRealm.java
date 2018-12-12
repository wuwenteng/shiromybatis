package com.ssm.test.shiromybatis.config.shiro;

import com.alibaba.fastjson.JSONObject;
import com.ssm.test.shiromybatis.exception.ErrorException;
import com.ssm.test.shiromybatis.pojo.User;
import com.ssm.test.shiromybatis.service.UserService;
import com.ssm.test.shiromybatis.util.Constants;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.Permission;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collection;

/**
 * @author ASUS
 */
public class UserRealm extends AuthorizingRealm {
    @Autowired
    private UserService userService;
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        Session session = SecurityUtils.getSubject().getSession();
        // 查询用户的权限
        Permission permission = (Permission) session.getAttribute(Constants.SESSION_USER_PERMISSION);
        System.out.println("permission的值为：" + permission);
        JSONObject permissionStr = (JSONObject) JSONObject.toJSON(permission);
        // 为当前用户设置角色和权限
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        authorizationInfo.addStringPermissions((Collection<String>) permissionStr);
        return authorizationInfo;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        String loginName = (String) authenticationToken.getPrincipal();
        String password = new String((char[])authenticationToken.getCredentials());

        // 将密码明文转换成MD5加密，数据库存密文
        Md5Hash md5Hash = new Md5Hash(password);
        String passwordNew = String.valueOf(md5Hash);
        // 去数据库查询结果
        User user = null;
        try {
            user = userService.getUserByName(loginName);
        } catch (ErrorException e) {
            e.printStackTrace();
        }
        if (user == null) {
            throw new UnknownAccountException();
        }
        System.out.println(user.getPassword());
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
                user.getUsername(),user.getPassword(),
                getName()
        );

        // session中不保存密码
        user.setPassword("null");
        // 将信息存入session
        SecurityUtils.getSubject().getSession().setAttribute(Constants.SESSION_USER_INFO,user);

        return authenticationInfo;
    }
}
