package com.dirk.fs.html_fs.repository;

import com.dirk.fs.html_fs.po.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Ranger
 * @create 2019-12-16 11:52
 */
public interface UserRepository extends JpaRepository<User,Integer> {
    User findByUsernameOrEmail(String username, String email);

    User findByUsernameAndPassword(String username, String md5);

    User findByUsername(String username);
}
