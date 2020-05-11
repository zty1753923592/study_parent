package com.zty.study.commons.config;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

/**
 * 自定义返回信息
 * @param <T>
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
public class R<T> {

    @JsonView(value = ModelAndView.class)
    private int code;

    @JsonView(value = ModelAndView.class)
    private LocalDateTime timestamp;

    @JsonView(value = ModelAndView.class)
    private String message;

    @JsonView(value = ModelAndView.class)
    private T data;


    public static <T> R<T> ok(T data) {
        return resultApi(Constants.SUCCESS, LocalDateTime.now(), null, data);
    }

    public static <T> R<T> ok(T date, int code) {
        return resultApi(code, LocalDateTime.now(), null, date);
    }

    public static <T> R<T> failed(int code, String msg) {
        return resultApi(code, LocalDateTime.now(), msg, null);
    }

    public static <T> R<T> failed(String msg) {
        return resultApi(500, LocalDateTime.now(), msg, null);
    }

    public static <T> R<T> resultApi(int code, LocalDateTime timestamp, String message, T data) {
        R<T> result = new R<>();
        result.setCode(code);
        result.setTimestamp(timestamp);
        result.setMessage(message);
        result.setData(data);
        return result;
    }
}
