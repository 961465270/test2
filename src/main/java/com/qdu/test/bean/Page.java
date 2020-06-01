package com.qdu.test.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Created by Administrator on 2020/5/4.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Page<T> {
    private int pageNum; // 当前页
    private int pageSize;
    private List<T> rows;
    private long total; // 总记录数
    private int pageCount; // 总页数
    private int pre; // 上一页
    private int next; // 下一页
    private boolean first; // 首页
    private boolean last; // 尾页
}
