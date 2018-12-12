package com.ssm.test.shiromybatis.service;

import com.ssm.test.shiromybatis.dao.PermissionDao;
import com.ssm.test.shiromybatis.exception.ErrorException;
import com.ssm.test.shiromybatis.pojo.Permission;
import com.ssm.test.shiromybatis.pojo.RolePermission;
import com.ssm.test.shiromybatis.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ASUS
 */
@Service
public class PermissionService {

    @Autowired
    private PermissionDao permissionDao;
    @Autowired
    private UserService userService;
    @Autowired
    private RolePermissionService rolePermissionService;

    public Permission getPermission(int id) {
        return permissionDao.findById(id);
    }

    public List<Permission> getPermissionByUsername(String username) throws ErrorException {
        User user = userService.getUserByName(username);
        int id = user.getId();
        List<Permission> permissions = new ArrayList<>();
        // roleId == 1时表示该角色时管理员，权限最高
        if (user.getRoleId() == 1) {
            permissions = permissionDao.findAll();
        } else {
            List<RolePermission> list = rolePermissionService.getRolePermissionByRoleId(id);
            permissions = new ArrayList<>();
            for (RolePermission rp : list) {
                Permission permission = permissionDao.findById(rp.getPermissionId());
                if (permission != null) {
                    permissions.add(permission);
                }
            }
        }

        return permissions;
    }

    public int savePermission(Permission permission) {
        Permission permission1 = permissionDao.save(permission);
        return permission1.getId();
    }

}
