package com.dirk.fs.html_fs.controller;

import com.dirk.fs.html_fs.service.FsService;
import com.dirk.fs.html_fs.utils.UnFileUtils;
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
public class FsController {

    @Autowired
    private FsService fsService;

    /**
     * 接收 url: / 跳转至主页
     * @return
     */
    @GetMapping("/")
    public String toHtmlFs(){
        return "fs/index";
    }

    @PostMapping("/fs/update")
    @ResponseBody
    public String updateFile(MultipartFile file) throws IOException {

        String rtn = fsService.unFile(file);

        return rtn;
    }

}
