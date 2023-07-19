package com.linyun.article;

import com.alibaba.fastjson.JSONArray;
import com.linyun.article.mapper.ApArticleContentMapper;
import com.linyun.article.mapper.ArticleMapper;
import com.linyun.file.service.FileStorageService;
import com.linyun.model.article.pojo.ApArticleContent;
import freemarker.template.Configuration;
import freemarker.template.Template;
import io.netty.util.internal.StringUtil;
import org.apache.commons.lang.StringUtils;
import org.apache.ibatis.javassist.bytecode.ByteArray;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.io.ByteArrayInputStream;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * @author linyun
 * @since 2023/7/18 17:28
 */

@SpringBootTest
public class ArticleApplicationTest {
    @Resource
    private ApArticleContentMapper apArticleContentMapper;
    @Resource
    private Configuration configuration;
    @Resource
    private FileStorageService fileStorageService;

    @Resource
    private ArticleMapper articleMapper;

    @Test
    void MinIoTest() throws Exception {
        //1.获取文章内容
        ApArticleContent content = apArticleContentMapper.getArticleContentByArticleId(1303156149041758210L);
        if (content == null && StringUtils.isNotBlank(content.getContent())) {
            return;
        }
        //2.文件内容通过freemarker生成html文件
        Template template = configuration.getTemplate("article.ftl");

        Map<String, Object> map = new HashMap<>();
        map.put("content", JSONArray.parseArray(content.getContent()));
        StringWriter out = new StringWriter();
        //合成
        template.process(map, out);
        //3.把html文件上传到minIo
        ByteArrayInputStream in = new ByteArrayInputStream(out.toString().getBytes());
        String path = fileStorageService.uploadHtmlFile("", content.getArticleId() + "html", in);
        //4.修改ap_article表 保存static_url字段
        int i = articleMapper.updateStaticUrlById(content.getArticleId(), path);
        System.out.println(i);
    }

}
