package com.dirk.fs.html_fs.service.impl;

import com.dirk.fs.html_fs.service.FsService;
import com.dirk.fs.html_fs.utils.FileUtils;
import com.dirk.fs.html_fs.utils.UnFileUtils;
import org.jasypt.encryption.StringEncryptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

/**
 * @author Ranger
 * @create 2019-12-15 11:46
 */
@Service
public class FsServiceImpl implements FsService {

    @Autowired
    private StringEncryptor encryptor;
    @Value("${ftp.username}")
    private String ftpUsername;
    @Value("${ftp.host}")
    private String ftpHost;
    @Value("${ftp.port}")
    private Integer ftpPort;
    @Value("${ftp.password}")
    private String ftpPassword;
    @Value("${ftp.home}")
    private String ftpHome;

    /**
     * 解压文件并上传至FTP服务器
     * @param mulFile
     * @return
     */
    @Override
    public String unFile(MultipartFile mulFile,String username,String url) {
        String suf = mulFile.getOriginalFilename().split("\\.")[1];
        if ("zip".equals(suf)) {
            //上传的文件类型是zip
            try {
                //将MulFile转为File
                File zipFile = new File(mulFile.getOriginalFilename());
                FileUtils.inputStreamToFile(mulFile.getInputStream(), zipFile);
                //解压并上传
                UnFileUtils.unZip(zipFile,
                        ftpHost,
                        ftpPort,
                        ftpUsername,
                        ftpPassword,
                        ftpHome,
                        username,
                        url);
            } catch (IOException e) {
                return "fail";
            }
            return "success";
        } else if ("rar".equals(suf)) {
            //上传的文件类型是rar
            try {
                File rarFile = new File(mulFile.getOriginalFilename());
                FileUtils.inputStreamToFile(mulFile.getInputStream(), rarFile);
                UnFileUtils.unRar(rarFile,
                        ftpHost,
                        ftpPort,
                        ftpUsername,
                        ftpPassword,
                        ftpHome);
            } catch (Exception e) {
                return "fail";
            }
            return "success";
        } else {
            return "unfile";
        }
    }
}
