package com.zhou.kill.killdemo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zhou.kill.killdemo.entity.TbItem;

public interface ItemMapper extends BaseMapper<TbItem> {

    TbItem selectById1(Long id);

    Integer updateById2(TbItem tbItem);
}
