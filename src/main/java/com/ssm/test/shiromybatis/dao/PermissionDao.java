package com.ssm.test.shiromybatis.dao;

import com.ssm.test.shiromybatis.pojo.Permission;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author ASUS
 */
public interface PermissionDao extends JpaRepository<Permission,Integer> {
    /**
     * 根据id查找
     * @return
     */
    Permission findById(int id);

}
