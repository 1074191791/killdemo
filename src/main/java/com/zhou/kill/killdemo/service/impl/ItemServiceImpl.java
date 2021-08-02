package com.zhou.kill.killdemo.service.impl;

import com.zhou.kill.killdemo.entity.TbItem;
import com.zhou.kill.killdemo.mapper.ItemMapper;
import com.zhou.kill.killdemo.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.concurrent.locks.Lock;

@Service("itemService")
public class ItemServiceImpl implements ItemService {

    @Autowired
    private ItemMapper itemMapper;

    @Resource(name = "redisLock")
    private Lock lock;

    @Override
    public Boolean doKill(Long id) {
        lock.lock();
        TbItem res = itemMapper.selectById(id);
        if(res.getItemStock() > 0) {
            //res.setItemStock(res.getItemStock() -1);
            //itemMapper.update(res, new QueryWrapper<TbItem>().eq("id",id));
            System.out.println(res);
            Integer integer = itemMapper.updateById2(res);
            if(integer > 0) {
                //更新成功
                System.out.println("true");
                lock.unlock();
                return true;
            } else {
                //抢购失败
                System.out.println("false");
                return false;
            }
        }
        System.out.println("没有库存了，直接返回" + System.currentTimeMillis());
        return false;
    }
}
