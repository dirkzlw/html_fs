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
    @Value("${ftp.host}")
    private String ftpHost;
    @Value("${ftp.username}")
    private String ftpUsername;
    @Value("${ftp.password}")
    private String ftpPassword;
    @Value("${ftp.home}")
    private String ftpHome;

    @Override
    public String unFile(MultipartFile mulFile) {
        String suf = mulFile.getOriginalFilename().split("\\.")[1];
        if ("zip".equals(suf)) {
            //上传的文件类型是zip
            try {
                File zipFile = new File(mulFile.getOriginalFilename());
                FileUtils.inputStreamToFile(mulFile.getInputStream(), zipFile);
                System.out.println("encryptor.decrypt(ftpHost) = " + encryptor.decrypt(ftpHost));
                UnFileUtils.unZip(zipFile,
                        encryptor.decrypt(ftpHost),
                        encryptor.decrypt(ftpUsername),
                        encryptor.decrypt(ftpPassword),
                        ftpHome);
            } catch (IOException e) {
                return "fail";
            }
            return "success";
        } else if ("rar".equals(suf)) {
            //上传的文件类型是rar
            System.out.println("FsServiceImpl.unFile");
            try {
                File rarFile = new File(mulFile.getOriginalFilename());
                FileUtils.inputStreamToFile(mulFile.getInputStream(), rarFile);
                UnFileUtils.unRar(rarFile);
            } catch (Exception e) {
                return "fail";
            }

        } else {
            return "unfile";
        }
        return null;
    }
}
