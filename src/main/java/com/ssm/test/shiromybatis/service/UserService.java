package com.ssm.test.shiromybatis.service;

import com.google.common.base.Preconditions;
import com.ssm.test.shiromybatis.dao.UserDao;
import com.ssm.test.shiromybatis.exception.ErrorException;
import com.ssm.test.shiromybatis.pojo.User;
import com.ssm.test.shiromybatis.util.CopyUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author ASUS
 */
@Service
public class UserService {

    @Autowired
    private UserDao userDao;

    public User getUserById(int id) {
        return userDao.findById(id);
    }

    public List<User> listUser() {
        return userDao.findAll();
    }

    public int saveUser(User user) {
        User user1 = userDao.save(user);
        return user1.getId();
    }

    public int updateUser(int id, User user) throws ErrorException {
        try {
            User userOri = userDao.findById(id);
            Preconditions.checkNotNull(userOri);
            // 将新值幅值给旧值,保存
            CopyUtil.copyUser(user,userOri);
            int idNew = saveUser(userOri);
            return idNew;
        } catch (Exception e) {
            throw new ErrorException("400","更新用户失败");
        }
    }

    public int deleteUser(int id) throws ErrorException {
        try {
            User user = userDao.findById(id);
            user.setDeleteStatus("2");
            int idAfter = saveUser(user);
            return idAfter;
        } catch (Exception e) {
            throw new ErrorException("400","删除用户失败");
        }
    }

    public User getUserByName(String username) throws ErrorException {
        try {
            User user = userDao.findByUsername(username);
            if (user == null) {
                throw new ErrorException();
            } else {
                return user;
            }
        } catch (Exception e) {
            throw new ErrorException("400","通过用户名获取用户失败");
        }
    }
}
