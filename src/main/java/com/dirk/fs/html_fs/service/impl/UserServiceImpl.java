package com.dirk.fs.html_fs.service.impl;

import com.dirk.fs.html_fs.po.User;
import com.dirk.fs.html_fs.repository.UserRepository;
import com.dirk.fs.html_fs.service.UserService;
import com.dirk.fs.html_fs.utils.MD5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Ranger
 * @create 2019-12-16 12:24
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    /**
     * 注册
     * @param user
     * @return
     */
    @Override
    public String register(User user) {
        User user2 = userRepository.findByUsernameOrEmail(user.getUsername(), user.getEmail());
        if(user2!=null)
            return "repeat";
        try {
            user.setPassword(MD5Utils.md5(user.getPassword()));
            userRepository.save(user);
        }catch (Exception e){
            throw new RuntimeException("用户注册异常！");
        }
        return "success";
    }

    /**
     * 登录
     * @param username
     * @param password
     * @return
     */
    @Override
    public String login(String username, String password) {
        User user = userRepository.findByUsernameAndPassword(username, MD5Utils.md5(password));
        if(null!=user)
            return "success";
        return "fail";
    }

    /**
     * 根据用户名查找用户
     * @param username
     * @return
     */
    @Override
    public User findUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}
