package com.linyun.wemedia;

import com.linyun.model.wemedia.dtos.WmNewsPageReqDto;
import com.linyun.model.wemedia.pojos.WmNews;
import com.linyun.wemedia.mapper.WmNewsMapper;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @author linyun
 * @since 2023/7/19 16:01
 */

@SpringBootTest
public class WemediaApplictionTest {
@Resource
    private WmNewsMapper wmNewsMapper;
@Test
    void testWmNewsMapper(){
    WmNewsPageReqDto dto = new WmNewsPageReqDto();
    dto.setEndPubDate(new Date());
    dto.setPage(1);
    dto.setSize(5);
    List<WmNews> page = wmNewsMapper.getPage(dto);
    System.out.println(page);
}

}
