package com.qdu.test.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.qdu.test.bean.Page;
import com.qdu.test.bean.Users;
import com.qdu.test.mapper.UsersMapper;
import com.qdu.test.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Administrator on 2020/5/3.
 */
@Service
public class UsersServiceImpl implements UsersService {

    @Autowired
    UsersMapper usersMapper;

    @Override
    public void addUsers(Users users) {
        usersMapper.addUsers(users);
    }

    @Override
    public Page<Users> queryUsers(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        List<Users> usersList = usersMapper.queryUsers();
        PageInfo<Users> pageInfo = new PageInfo<>(usersList);

        Page<Users> result = new Page<>(
                pageInfo.getPageNum(),
                pageInfo.getPageSize(),
                usersList,
                pageInfo.getTotal(),
                pageInfo.getPages(),
                pageInfo.getPrePage(),
                pageInfo.getNextPage(),
                pageInfo.isIsFirstPage(),
                pageInfo.isIsLastPage());
        return result;
    }

    @Override
    public void deleteById(int id) {
        usersMapper.deleteById(id);
    }

    @Override
    public void updateUsers(Users users) {
        usersMapper.updateUsers(users);
    }

    @Override
    public boolean checkUsers(String name) {
        if(usersMapper.checkUsers(name)==null){
            return true;
        }
        return false;
    }

    @Override
    public Users queryUser(String name, String password) {
        return usersMapper.queryUser(name,password);
    }


}
