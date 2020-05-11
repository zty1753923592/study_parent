package com.zty.study.commons.config;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 业务常量类
 */
public interface Constants {

    int SUCCESS = 200;


    //自定义状态码枚举类
    @Getter
    @AllArgsConstructor
    enum StatusType {

        REDIS_ERROR(5001, "redis报错"),

        CODE_ERROR(5002,"验证码未过期"),

        INVALID_PARAM_MSG(5003,"无效参数");

        //状态码
        int code;

        //信息
        String msg;

        public static Integer getCode(int code) {
            for (StatusType type : values()) {
                if (type.getCode() == code) {
                    return code;
                }
            }
            return null;
        }

        public static String getMsg(String msg) {
            for (StatusType type : values()) {
                if (type.getMsg().equals(msg)) {
                    return msg;
                }
            }
            return null;
        }
    }
}
