package com.ssm.test.shiromybatis.controller;

import com.ssm.test.shiromybatis.exception.ErrorException;
import com.ssm.test.shiromybatis.pojo.Permission;
import com.ssm.test.shiromybatis.service.PermissionService;
import com.ssm.test.shiromybatis.util.CopyUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author ASUS
 */
@RestController
@RequestMapping("/permission")
public class PermissionController {
    @Autowired
    private PermissionService permissionService;

    @GetMapping("/")
    public ResponseEntity listPermission(@RequestParam String username) throws ErrorException {
        List<Permission> list = permissionService.getPermissionByUsername(username);
        if (list.size() > 0) {
            return ResponseEntity.ok(list);
        } else {
            return ResponseEntity.badRequest().body("没有权限");
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity getPermission(@PathVariable int id) {
        Permission permission = permissionService.getPermission(id);
        if (permission != null) {
            return ResponseEntity.ok(permission);
        } else {
            return ResponseEntity.badRequest().body("没有该权限");
        }
    }

    @PostMapping("")
    public ResponseEntity savePermission(@RequestBody Permission permission) {
        try {
            int id = permissionService.savePermission(permission);
            Permission permission1 = permissionService.getPermission(id);
            if (permission1 != null) {
                return ResponseEntity.ok(permission1);
            } else {
                return ResponseEntity.badRequest().body("保存失败");
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Permission保存失败");
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity updatePermission(@RequestBody Permission permission,@PathVariable int id) {
        try {
            Permission permissionOri = permissionService.getPermission(id);
            Permission p = CopyUtil.copyPermission(permission,permissionOri);
            int idAfterSave = permissionService.savePermission(p);
            if (idAfterSave == id) {
                return ResponseEntity.ok(p);
            } else {
                return ResponseEntity.badRequest().body("更新失败");
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("更新失败");
        }
    }

}
