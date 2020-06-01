package com.qdu.test.mapper;

import com.qdu.test.bean.Food;
import com.qdu.test.bean.Ping;

import java.util.List;

/**
 * Created by Administrator on 2020/5/15.
 */
public interface FoodMapper {
    void addFood(Food food);

    List<Food> queryList(Food food);

    List<Ping> queryPing(Integer foodId);

    void insert(Ping ping);
}
