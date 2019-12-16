package com.dirk.fs.html_fs.po;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Ranger
 * @create 2019-12-16 11:31
 */
@Entity
@Table(name = "t_user")
@Setter
@Getter
public class User {
    //用户ID
    @Id
    @Column(length = 10)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userId;
    // 用户名
    @Column(length = 30)
    private String username;
    // 密码
    @Column(length = 40)
    private String password;
    // 邮箱
    @Column(length = 40)
    private String email;
    // 所上传的文件列表
    @OneToMany(mappedBy = "user")
    private Set<HtmlFile> hfSet = new HashSet<>();

    public User() {}

    public User(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", hfSet=" + hfSet +
                '}';
    }
}
