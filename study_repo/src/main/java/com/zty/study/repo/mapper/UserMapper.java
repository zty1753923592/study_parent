package com.zty.study.repo.mapper;

import org.apache.ibatis.annotations.Mapper;


import com.zty.study.repo.model.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 *  Mapper 接口
 * @author lsy
 * @since 2020-05-07
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

}
