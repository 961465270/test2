package com.qdu.test.service;

import com.qdu.test.bean.Page;
import com.qdu.test.bean.Users;

/**
 * Created by Administrator on 2020/5/3.
 */
public interface UsersService {
    void addUsers(Users users);



    Page<Users> queryUsers(int pageNum, int pageSize);

    void deleteById(int id);

    void updateUsers(Users users);

    boolean checkUsers(String name);

    Users queryUser(String name, String password);
}
