package com.zty.study.user.api;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zty.study.cache.CookieUtils;
import com.zty.study.cache.RedisUtils;
import com.zty.study.commons.Sms.SendSms;
import com.zty.study.commons.config.Constants;
import com.zty.study.commons.config.R;
import com.zty.study.commons.exception.StudyBaseException;
import com.zty.study.commons.param.BaseParam;
import com.zty.study.repo.component.Pager;
import com.zty.study.repo.model.User;
import com.zty.study.user.intfer.IUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 前端控制器
 *
 * @author lsy
 * @since 2020-05-07
 */
@Api(value = "用户")
@RestController
@RequestMapping("/user")
public class UserApi {

    @Autowired
    private IUserService targetService;

    @Autowired
    private SendSms sendSms;

    @Autowired
    private RedisUtils redisUtils;

    @Autowired
    private HttpServletRequest httpServletRequest;


    /**
     * 获取短信验证码
     *
     * @return
     */
    @ApiOperation(value = "获取短信验证码", notes = "获取短信验证码", produces = "application/json")
    @PostMapping(value = "getCode")
    public R getCode(@RequestParam String phone) {
        String code = sendSms.getCode();
        String otpcode = redisUtils.getMap(phone);
        if (!StringUtils.isEmpty(otpcode)) {
            throw new StudyBaseException(Constants.StatusType.CODE_ERROR.getMsg());
        }
        httpServletRequest.getSession().setAttribute(phone, code);
        sendSms.SendSmsMessage(phone, code);
        redisUtils.setMap(phone, code);
        redisUtils.expire(phone);
        return R.ok(code);
    }


    @ApiOperation(value = "根据手机号登录", notes = "根据手机号登录", produces = "application/json")
    @GetMapping(value = "/loginByphone")
    public R<Boolean> loginByphone(@RequestParam String phone, @RequestParam String code,HttpServletResponse response) {

        if (StringUtils.isEmpty(code)) {
            throw new StudyBaseException(Constants.StatusType.REDIS_ERROR.getMsg());
        }
        String otpCode = redisUtils.getMap(phone);
        if (!code.equals(otpCode)) {
            throw new StudyBaseException(Constants.StatusType.LOG_ERROR.getMsg());
        }

        CookieUtils.creatCookie(phone, response);

        return R.ok();
    }

    /**
     * 分页查询数据
     */
    @ApiOperation(value = "查询分页数据", notes = "查询分页数据", response = User.class)
    @PostMapping(value = "/page")
    public R<IPage<User>> pageUser(@RequestBody Pager<BaseParam<User>> pager) {
        return R.ok(targetService.pageUser(pager), Constants.SUCCESS);
    }

    /**
     * 查询全部数据
     */
    @ApiOperation(value = "查询全部数据", notes = "查询全部数据", response = User.class)
    @PostMapping(value = "/list")
    public R<List<User>> listUser() {
        RedisUtils.set("dd", "dsddsfdssdfssdsdffdsdsd");
        return R.ok(targetService.list(), Constants.SUCCESS);
    }


    /**
     * 新增数据
     * //TODO 注明所有业务场景
     */
    @ApiOperation(value = "新增数据", notes = "新增数据", response = Boolean.class)
    @PostMapping(value = "/save")
    public R<Boolean> save(@RequestBody User entity) {
        return R.ok(targetService.save(entity.defv()), Constants.SUCCESS);
    }

    /**
     * 更新数据
     * //TODO 注明所有业务场景
     */
    @ApiOperation(value = "更新数据", notes = "更新数据", response = Boolean.class)
    @PostMapping(value = "/update")
    public R<Boolean> update(@RequestBody User entity) {
        return R.ok(targetService.updateById(entity), Constants.SUCCESS);
    }

    /**
     * 删除
     */
    @ApiOperation(value = "删除数据", notes = "删除数据", response = Boolean.class)
    @PostMapping(value = "/del")
    public R<Boolean> delete(@RequestBody List<Long> ids) {
        return R.ok(targetService.removeByIds(ids), Constants.SUCCESS);
    }


}

