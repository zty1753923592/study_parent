package com.zty.study.user.intfer;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zty.study.commons.param.BaseParam;
import com.zty.study.repo.component.Pager;
import com.zty.study.repo.model.User;

/**
 *  服务类
 * @author lsy
 * @since 2020-05-07
 */
public interface IUserService extends IService<User> {

       /**
        * 根据条件分页查询数据
        * @param pager
        * @return IPage
        */
        IPage<User> pageUser(Pager<BaseParam<User>> pager);
}
