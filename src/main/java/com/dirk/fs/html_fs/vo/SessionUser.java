package com.dirk.fs.html_fs.vo;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Ranger
 * @create 2019-12-16 16:57
 */
@Setter
@Getter
public class SessionUser {
    private Integer userId;
    private String username;

    public SessionUser() {}

    public SessionUser(Integer userId, String username) {
        this.userId = userId;
        this.username = username;
    }
}
