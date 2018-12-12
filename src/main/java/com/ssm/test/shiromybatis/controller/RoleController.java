package com.ssm.test.shiromybatis.controller;

import com.ssm.test.shiromybatis.pojo.Role;
import com.ssm.test.shiromybatis.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author ASUS
 */

@RestController
@RequestMapping("/role")
public class RoleController {
    @Autowired
    private RoleService roleService;

    @GetMapping("/{id}")
    public ResponseEntity getRoleById(@PathVariable int id) {
        Role role = roleService.getRoleById(id);
        if (role != null) {
            return ResponseEntity.ok(role);
        } else {
            return ResponseEntity.badRequest().body("没有该角色");
        }
    }

    @GetMapping("")
    public ResponseEntity getRoleList() {
        List<Role> roles = roleService.getRoleList();
        return ResponseEntity.ok(roles);
    }

    @PostMapping("")
    public ResponseEntity saveRole(@RequestBody Role role) {

        int id = roleService.saveRole(role);
        Role role1 = roleService.getRoleById(id);
        if (role1 != null) {
            return ResponseEntity.ok(role1);
        } else {
            return ResponseEntity.badRequest().body("保存失败");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteRole(@PathVariable int id) {
        int idAfter = roleService.deleteRole(id);
        return ResponseEntity.ok(idAfter);
    }
}
