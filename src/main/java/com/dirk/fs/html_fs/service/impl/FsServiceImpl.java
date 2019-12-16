package com.dirk.fs.html_fs.service.impl;

import com.dirk.fs.html_fs.po.HtmlFile;
import com.dirk.fs.html_fs.po.User;
import com.dirk.fs.html_fs.repository.HtmlFileRepository;
import com.dirk.fs.html_fs.repository.UserRepository;
import com.dirk.fs.html_fs.service.FsService;
import com.dirk.fs.html_fs.utils.FileUtils;
import com.dirk.fs.html_fs.utils.UnFileUtils;
import javafx.scene.input.DataFormat;
import org.jasypt.encryption.StringEncryptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Ranger
 * @create 2019-12-15 11:46
 */
@Service
public class FsServiceImpl implements FsService {

    @Autowired
    private StringEncryptor encryptor;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private HtmlFileRepository htmlFileRepository;
    @Value("${ftp.username}")
    private String ftpUsername;
    @Value("${ftp.host}")
    private String ftpHost;
    @Value("${ftp.port}")
    private Integer ftpPort;
    @Value("${nginx.port}")
    private Integer nginxPort;
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
    public String unFile(MultipartFile mulFile,String username) {

        String suf = mulFile.getOriginalFilename().split("\\.")[1];
        if ("zip".equals(suf)) {
            //上传的文件类型是zip
            String url ;
            try {
                //将MulFile转为File
                File zipFile = new File(mulFile.getOriginalFilename());
                FileUtils.inputStreamToFile(mulFile.getInputStream(), zipFile);
                //解压并上传
                url = UnFileUtils.unZip(zipFile,
                        ftpHost,
                        ftpPort,
                        nginxPort,
                        ftpUsername,
                        ftpPassword,
                        ftpHome,
                        username);
            } catch (IOException e) {
                return "fail";
            }
            String createTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
            User user = userRepository.findByUsername(username);
            HtmlFile hf = htmlFileRepository.findByUrl(url);
            if(null == hf){
                hf = new HtmlFile(url, createTime, user);
            }else {
                hf.setUrl(url);
                hf.setCreateTime(createTime);
            }
            htmlFileRepository.save(hf);
            user.getHfSet().add(hf);
            userRepository.save(user);
            return "success";
        } else if ("rar".equals(suf)) {
            //上传的文件类型是rar
            String url;
            try {
                File rarFile = new File(mulFile.getOriginalFilename());
                FileUtils.inputStreamToFile(mulFile.getInputStream(), rarFile);
                url = UnFileUtils.unRar(rarFile,
                        ftpHost,
                        ftpPort,
                        nginxPort,
                        ftpUsername,
                        ftpPassword,
                        ftpHome,
                        username);
            } catch (Exception e) {
                return "fail";
            }
            String createTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
            User user = userRepository.findByUsername(username);
            HtmlFile hf = htmlFileRepository.findByUrl(url);
            if(null == hf){
                hf = new HtmlFile(url, createTime, user);
            }else {
                hf.setUrl(url);
                hf.setCreateTime(createTime);
            }
            htmlFileRepository.save(hf);
            user.getHfSet().add(hf);
            userRepository.save(user);
            return "success";
        } else {
            return "unfile";
        }
    }
}
