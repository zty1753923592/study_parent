package com.zty.study.commons.param;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

/**
 * sql 时间参数类
 * @param <T>
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class TimeParam<T> {

    //开始时间
    private LocalDateTime startTime;

    //结束时间
    private LocalDateTime endTime;

    //sql时间字段名称
    private String dateName;

    //数据
    private T condition;

    //操作者Id
    private Long optId;
}
