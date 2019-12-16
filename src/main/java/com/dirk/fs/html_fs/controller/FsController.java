package com.dirk.fs.html_fs.controller;

import com.dirk.fs.html_fs.service.FsService;
import com.dirk.fs.html_fs.vo.SessionUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
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
     * 接收 url: / 跳转至上传页
     *
     * @return
     */
    @GetMapping("/to/upload")
    public String toHtmlFs() {
        return "fs/upload";
    }

    /**
     * 上传压缩文件
     *
     * @param file
     * @return
     * @throws IOException
     */
    @PostMapping("/fs/update")
    @ResponseBody
    public String updateFile(MultipartFile file,
                             HttpServletRequest request) {

        SessionUser sessionUser = (SessionUser) request.getSession().getAttribute("sessionUser");
        String rtn = fsService.unFile(file, sessionUser.getUsername());

        return rtn;
    }

}
