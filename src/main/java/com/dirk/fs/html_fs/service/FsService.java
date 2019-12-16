package com.dirk.fs.html_fs.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * @author Ranger
 * @create 2019-12-15 11:45
 */
public interface FsService {
    String unFile(MultipartFile file,String username,String url);
}
