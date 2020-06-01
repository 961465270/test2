package com.qdu.test.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.qdu.test.bean.Food;
import com.qdu.test.bean.Page;
import com.qdu.test.bean.Ping;
import com.qdu.test.mapper.FoodMapper;
import com.qdu.test.service.FoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Administrator on 2020/5/15.
 */
@Service
public class FoodServiceImpl implements FoodService {

    @Autowired
    FoodMapper foodMapper;
    @Override
    public void addFood(Food food) {
        foodMapper.addFood(food);
    }

    @Override
    public Page<Food> queryList(int pageNum, int pageSize, Food food) {
        PageHelper.startPage(pageNum,pageSize);
        List<Food> foodList = foodMapper.queryList(food);
        PageInfo<Food> pageInfo = new PageInfo<>(foodList);

        return new Page<Food>(
                pageInfo.getPageNum(),
                pageInfo.getPageSize(),
                foodList,
                pageInfo.getTotal(),
                pageInfo.getPages(),
                pageInfo.getPrePage(),
                pageInfo.getNextPage(),
                pageInfo.isIsFirstPage(),
                pageInfo.isIsLastPage());
    }

    @Override
    public List<Ping> queryPing(Integer foodId) {
        return foodMapper.queryPing(foodId);
    }

    @Override
    public void insert(Ping ping) {
        foodMapper.insert(ping);
    }
}
