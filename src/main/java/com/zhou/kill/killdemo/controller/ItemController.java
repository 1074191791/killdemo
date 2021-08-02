package com.zhou.kill.killdemo.controller;

import com.zhou.kill.killdemo.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/item")
public class ItemController {
    @Autowired
    private ItemService itemService;

    @GetMapping("/dokill/{id}")
    @ResponseBody
    public Boolean doKill(@PathVariable Long id) {
        return  itemService.doKill(id);
    }
}
