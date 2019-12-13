package com.dirk.fs.html_fs.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author Ranger
 * @create 2019-12-13 16:17
 */
@Controller
public class FsController {

    @GetMapping("/")
    public String toHtmlFs(){
        return "fs/index";
    }

}
