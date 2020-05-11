package com.zty.study.user.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zty.study.commons.param.BaseParam;
import com.zty.study.repo.component.Pager;
import com.zty.study.repo.component.TranslatePage;
import com.zty.study.repo.mapper.UserMapper;
import com.zty.study.repo.model.User;
import com.zty.study.user.intfer.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


/**
 *  服务实现类
 * @author lsy    这写报错的是因为我没写那个框架
 * @since 2020-05-07
 */
@Slf4j
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>implements IUserService {

        /**
         * 根据条件查询数据列表
         * @param pager
         * @return
         */
        @Override
        public IPage<User> pageUser(Pager<BaseParam<User>> pager){
             return baseMapper.selectPage(Pager.translateParamPage(pager), TranslatePage.translateParam(pager.getCondition()));
        }
}
