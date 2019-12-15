package com.dirk.fs.html_fs.service.impl;

import com.dirk.fs.html_fs.service.FsService;
import com.dirk.fs.html_fs.utils.FileUtils;
import com.dirk.fs.html_fs.utils.UnFileUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author Ranger
 * @create 2019-12-15 11:46
 */
@Service
public class FsServiceImpl implements FsService {
    @Override
    public String unFile(MultipartFile mulFile) throws IOException {
        String suf = mulFile.getOriginalFilename().split("\\.")[1];
        if("zip".equals(suf)){
            //上传的文件类型是zip
            InputStream is = mulFile.getInputStream();
            File zipFile = new File(mulFile.getOriginalFilename());
            FileUtils.inputStreamToFile(is, zipFile);
            UnFileUtils.unZip(zipFile);
            return "success";
        }else if("rar".equals(suf)){
            //上传的文件类型是rar
        }else {
            return "unfile";
        }
        return null;
    }
}
