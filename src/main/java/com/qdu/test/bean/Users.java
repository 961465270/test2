package com.qdu.test.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by Administrator on 2020/5/2.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Users {
private int id;
private String name;
private String password;
private int age;
private String gend;
private String hobby;
private int level;
}
