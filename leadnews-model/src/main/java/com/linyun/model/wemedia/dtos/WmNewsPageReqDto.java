package com.linyun.model.wemedia.dtos;


import com.linyun.model.dtos.PageRequestDto;
import lombok.Data;

import java.util.Date;

/**
 * @author linyun
 */
@Data
public class WmNewsPageReqDto extends PageRequestDto {
    /**
     * 登录用户Id
     */
    private Integer userId;

    /**
     * 状态
     */
    private Short status;
    /**
     * 开始时间
     */
    private Date beginPubDate;
    /**
     * 结束时间
     */
    private Date endPubDate;
    /**
     * 所属频道ID
     */
    private Integer channelId;
    /**
     * 关键字
     */
    private String keyword;
}