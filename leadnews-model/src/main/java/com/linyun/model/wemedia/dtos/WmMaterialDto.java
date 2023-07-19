package com.linyun.model.wemedia.dtos;

import com.linyun.model.dtos.PageRequestDto;
import lombok.Data;

/**
 * @author linyun
 * @since 2023/7/19 14:22
 */

@Data
public class WmMaterialDto extends PageRequestDto {

    private Integer userId;

    /**
     * 1 查询收藏 0：未收藏
     */
    private short isCollection;

}
