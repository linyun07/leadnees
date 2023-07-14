package com.linyun.model.article.dtos;

import lombok.Data;

import java.util.Date;

/**
 * @author linyun
 * @since 2023/7/14 10:35
 */

@Data
public class ArticleHomeDto {
    // 最大时间
    Date maxBehatTime;
    // 最小时间
    Date minBehatTime;
    // 分页size
    Integer size;
    // 频道ID
    String tag;

}
