package com.zhou.kill.killdemo.entity;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class TbItem {
    /**
     * id
     */
    private Long id;

    /**
     * 商品编码
     */
    private String itemCode;

    /**
     * 商品名
     */
    private String itemName;

    /**
     * 价格
     */
    private BigDecimal itemPrice;

    /**
     * 库存
     */
    private Integer itemStock;


}
