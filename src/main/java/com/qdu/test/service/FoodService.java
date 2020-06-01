package com.qdu.test.service;

import com.qdu.test.bean.Food;
import com.qdu.test.bean.Page;
import com.qdu.test.bean.Ping;

import java.util.List;

/**
 * Created by Administrator on 2020/5/15.
 */
public interface FoodService {
    void addFood(Food food);

    Page<Food> queryList(int pageNum, int pageSize, Food food);

    List<Ping> queryPing(Integer foodId);

    void insert(Ping ping);
}
