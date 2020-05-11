package com.zty.study.repo.component;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.zty.study.commons.param.BaseParam;

/**
 * sql参数拼接类
 */
public class TranslatePage {

    /**
     * BaseParam类的拼接
     * @param param
     * @param <T>
     * @return
     */
    public static <T> QueryWrapper<T> translateParam(BaseParam<T> param){
        if(param==null){
            return new QueryWrapper<T>();
        }
        return new QueryWrapper<T>(param.getCondition())
                .ge(param.getStartTime() != null, StringUtils.camelToUnderline(param.getDateName()),param.getStartTime())
                .lt(param.getEndTime() != null, StringUtils.camelToUnderline(param.getDateName()),param.getEndTime())
                .orderBy(param.getOrderBy()!=null,param.getAsc() > 0 , StringUtils.camelToUnderline(param.getOrderBy()));
    }
}
