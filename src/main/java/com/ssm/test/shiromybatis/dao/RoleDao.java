package com.ssm.test.shiromybatis.dao;

import com.ssm.test.shiromybatis.pojo.Role;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author ASUS
 */
public interface RoleDao extends JpaRepository<Role,Integer> {

    /**
     * 获取一个角色
     * @param id
     * @return
     */
    Role findById(int id);

    /**
     * 使用角色名字获取role信息
     * @param roleName
     * @return
     */
    Role findByRoleName(String roleName);

}
