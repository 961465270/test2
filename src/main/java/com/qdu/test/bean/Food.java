package com.qdu.test.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by Administrator on 2020/5/14.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Food {
    private int id;
    private String name;
    private String userName;
    private String type;
    private String pic;
    private String introduce;
    private String way;
}
