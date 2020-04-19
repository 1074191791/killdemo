package com.zhou.kill.killdemo;

import com.zhou.kill.killdemo.entity.TbItem;
import com.zhou.kill.killdemo.mapper.ItemMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class KilldemoApplicationTests {

    @Autowired
    ItemMapper itemMapper;

    @Test
    void contextLoads() {

        TbItem tbItem = itemMapper.selectById(1L);
        System.out.println(tbItem);
    }

}
