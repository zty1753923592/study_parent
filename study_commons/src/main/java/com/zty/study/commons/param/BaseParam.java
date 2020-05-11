package com.zty.study.commons.param;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * sql 排序参数类
 * @param <T>
 */
@Data
@Accessors(chain = true)
public class BaseParam<T> extends TimeParam<T>{

    //sql 排序字段名称
    private String orderBy;

    //排序方式   0：倒序   1正序   mysql数据库生成的mapper.xml文件要自己改一下，否则项目跑步起来，你看号了
    private int asc;
}
