package com.dirk.fs.html_fs.controller;

import com.dirk.fs.html_fs.service.FsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * @author Ranger
 * @create 2019-12-13 16:17
 */
@Controller
public class MainController {

    /**
     * 接收 url: / 跳转至主页
     *
     * @return
     */
    @GetMapping("/")
    public String toHtmlFs() {
        return "index";
    }

}
