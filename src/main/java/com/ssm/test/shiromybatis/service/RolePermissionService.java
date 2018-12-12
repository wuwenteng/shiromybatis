package com.ssm.test.shiromybatis.service;

import com.ssm.test.shiromybatis.dao.RolePermissionDao;
import com.ssm.test.shiromybatis.pojo.RolePermission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author ASUS
 */

@Service
public class RolePermissionService {
    @Autowired
    private RolePermissionDao rolePermissionDao;

    public RolePermission getRolePermission(int id) {
        return rolePermissionDao.findById(id);
    }

    public List<RolePermission> getRolePermissionByRoleId(int roleId) {
        return rolePermissionDao.findByRoleId(roleId);
    }

    public RolePermission saveRolePermission(RolePermission rolePermission) {
        return rolePermissionDao.save(rolePermission);
    }

    public int deleteRolePermission(int id) {
        RolePermission rolePermission = rolePermissionDao.findById(id);
        rolePermission.setDeleteStatus("2");
        RolePermission rp = rolePermissionDao.save(rolePermission);
        return rp.getId();
    }
}
