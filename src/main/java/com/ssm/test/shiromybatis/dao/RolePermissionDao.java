package com.ssm.test.shiromybatis.dao;

import com.ssm.test.shiromybatis.pojo.RolePermission;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author ASUS
 */
public interface RolePermissionDao extends JpaRepository<RolePermission,Integer> {
    /**
     * 获取一个角色授权信息
     * @param id
     * @return
     */
    RolePermission findById(int id);

    /**
     * 获取一个角色授权信息
     * @param id
     * @return
     */
    List<RolePermission> findByRoleId(int id);
}
