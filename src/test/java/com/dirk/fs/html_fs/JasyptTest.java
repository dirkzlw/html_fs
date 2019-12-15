package com.dirk.fs.html_fs;

import org.jasypt.encryption.StringEncryptor;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author Ranger
 * @create 2019-10-21 10:33
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = HtmlFsApplication.class)
public class JasyptTest {

    @Autowired
    private StringEncryptor encryptor;

    @Test
    public void testJasypt(){

    }
}
