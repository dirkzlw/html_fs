package com.dirk.fs.html_fs.service;

import com.dirk.fs.html_fs.po.User;

/**
 * @author Ranger
 * @create 2019-12-16 12:23
 */
public interface UserService {
    String register(User user);

    String login(String username, String password);
}
