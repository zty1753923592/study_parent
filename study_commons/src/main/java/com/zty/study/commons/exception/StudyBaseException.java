package com.zty.study.commons.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

/**
 * 全局异常处理信息类
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
public class StudyBaseException extends RuntimeException{

    //状态码
    private int code;

    //时间戳
    private LocalDateTime timestamp;

    //异常信息
    private String msg;


    public StudyBaseException(String msg) {
        super(msg);
        this.msg = msg;
    }

    public StudyBaseException(int code, String msg) {
        super(msg);
        this.code = code;
        this.msg = msg;
    }

    public StudyBaseException(int code, LocalDateTime timestamp, String msg) {
        super(msg);
        this.code = code;
        this.timestamp = timestamp;
        this.msg = msg;
    }
}
