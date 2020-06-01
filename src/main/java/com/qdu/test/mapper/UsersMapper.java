package com.qdu.test.mapper;

import com.qdu.test.bean.Users;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by Administrator on 2020/5/3.
 */
public interface UsersMapper {
    void addUsers(Users users);


    List<Users> queryUsers();

    void deleteById(int id);

    void updateUsers(Users users);

    Users checkUsers(String name);

    Users queryUser(@Param("name") String name, @Param("password") String password);
}
