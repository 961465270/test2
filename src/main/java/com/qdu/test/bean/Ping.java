package com.qdu.test.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by Administrator on 2020/5/29.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Ping {
    private int id;
    private Integer foodId;
    private String userName;
    private String foodText;
}
