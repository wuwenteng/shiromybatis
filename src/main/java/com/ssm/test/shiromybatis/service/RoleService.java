package com.ssm.test.shiromybatis.service;

import com.ssm.test.shiromybatis.dao.RoleDao;
import com.ssm.test.shiromybatis.pojo.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author ASUS
 */

@Service
public class RoleService {
    @Autowired
    private RoleDao roleDao;

    public Role getRoleById(int id) {
        Role role = roleDao.findById(id);
        return role;
    }

    public List<Role> getRoleList() {
        return roleDao.findAll();
    }

    public int saveRole(Role role) {
        roleDao.save(role);
        return roleDao.findByRoleName(role.getRoleName()).getId();
    }

    public int deleteRole(int id) {
        Role role = roleDao.findById(id);
        role.setDeleteStatus("2");
        return saveRole(role);
    }
}
