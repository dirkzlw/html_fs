package com.dirk.fs.html_fs.po;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * @author Ranger
 * @create 2019-12-16 11:31
 */
@Entity
@Table(name = "t_html_file")
@Getter
@Setter
public class HtmlFile {
    //ID
    @Id
    @Column(length = 10)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer hfId;
    @Column(length = 100)
    private String url;
    //上传时间
    @Column(length = 40)
    private String createTime;
    // 所属用户
    @ManyToOne
    @JoinColumn(name="userId")
    private User user;

    public HtmlFile() {}

    public HtmlFile(String url, String createTime, User user) {
        this.url = url;
        this.createTime = createTime;
        this.user = user;
    }
}
