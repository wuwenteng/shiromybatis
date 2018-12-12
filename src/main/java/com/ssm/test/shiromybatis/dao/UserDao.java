package com.ssm.test.shiromybatis.dao;

import com.ssm.test.shiromybatis.pojo.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author ASUS
 */
public interface UserDao extends JpaRepository<User,Integer> {

    /**
     * 根据id查找一个用户
     * @param id
     * @return
     */
    User findById(int id);

    /**
     * 根据username查找用户
     * @param username
     * @return
     */
    User findByUsername(String username);

}
