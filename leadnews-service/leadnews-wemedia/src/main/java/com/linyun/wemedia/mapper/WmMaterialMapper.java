package com.linyun.wemedia.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.linyun.model.wemedia.dtos.WmMaterialDto;
import com.linyun.model.wemedia.pojos.WmMaterial;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author linyun
 */
@Mapper
public interface WmMaterialMapper extends BaseMapper<WmMaterial> {
    /**
     * 分页查询
     *
     * @param dto 存储页码:page，页面条数:size 和是否收藏 1：表示收藏 0：表示未收藏
     * @return
     */
    List<WmMaterial> listAllByUserIdAndIsCollection(WmMaterialDto dto);

    /**
     * 分页查询获取总条数
     * @param dto
     * @return
     */
    int getCountByUserIdAndIsCollection(WmMaterialDto dto);

    /**
     * 新增
     *
     * @param wmMaterial
     * @return
     */
    int insertAll(WmMaterial wmMaterial);

    /**
     * 整体修改
     *
     * @param wmMaterial
     * @return
     */
    int updateAll(WmMaterial wmMaterial);

}