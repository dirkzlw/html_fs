package com.dirk.fs.html_fs.controller;

import com.dirk.fs.html_fs.po.User;
import com.dirk.fs.html_fs.service.UserService;
import com.dirk.fs.html_fs.vo.SessionUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Ranger
 * @create 2019-12-13 16:17
 */
@Controller
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 接收 url: / 跳转至主页
     *
     * @return
     */
    @GetMapping("/login")
    public String toLogin() {
        return "ugn/login";
    }

    /**
     * 登录
     *
     * @param user
     * @param request
     * @return
     */
    @PostMapping("/ugn/login")
    @ResponseBody
    public String login(User user,
                        HttpServletRequest request) {
        String rtn = userService.login(user.getUsername(), user.getPassword());
        if ("success".equals(rtn)) {
            User user2 = userService.findUserByUsername(user.getUsername());
            request.getSession().setAttribute("sessionUser", new SessionUser(user2.getUserId(), user2.getUsername()));
        }
        return rtn;
    }

    /**
     * 注册
     *
     * @param user
     * @return
     */
    @PostMapping("/ugn/register")
    @ResponseBody
    public String register(User user) {
        String rtn = userService.register(user);
        return rtn;
    }

}
