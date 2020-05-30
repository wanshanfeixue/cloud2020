package com.edian.springcloud.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 支付实体
 * @author admin
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Payment implements Serializable{
    //主键id
    private long id;
    //支付流水号
    private String serial;
}
