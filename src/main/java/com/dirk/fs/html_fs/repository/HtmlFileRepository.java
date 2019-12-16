package com.dirk.fs.html_fs.repository;

import com.dirk.fs.html_fs.po.HtmlFile;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Ranger
 * @create 2019-12-16 11:53
 */
public interface HtmlFileRepository extends JpaRepository<HtmlFile,Integer> {
    HtmlFile findByUrl(String url);
}
