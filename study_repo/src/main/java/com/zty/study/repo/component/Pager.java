package com.zty.study.repo.component;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zty.study.commons.param.BaseParam;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 分页参数配置
 * @param <T>
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class Pager<T> {

    private int current = 1;

    private int page = 1;

    private int pageSize = 10;

    private T condition;

    public static <T> Page<T> translateParamPage(Pager<BaseParam<T>> page) {
        return new Page<T>(page.getPage(),page.getPageSize());
    }

    //你要in查询的话自己加
    /*public static <V,T> Page<T> translateInParamPage(Pager<InParam<V,T>> page) {
        return new Page<T>(page.getPage(),page.getPageSize());
    }*/
}
