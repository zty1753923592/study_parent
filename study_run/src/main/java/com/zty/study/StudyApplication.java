package com.zty.study;

import com.zty.study.commons.config.R;
import com.zty.study.commons.exception.StudyBaseException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@RestControllerAdvice    //配置全局异常生效
@SpringBootApplication
@EnableSwagger2
public class StudyApplication {
    public static void main(String[] args) {
        SpringApplication.run(StudyApplication.class, args);
    }

    /**
     * 全局异常捕获
     * @param ex
     * @return
     */
    @ExceptionHandler(StudyBaseException.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public R<String> result(StudyBaseException ex) {
        return R.failed(ex.getCode(), ex.getMsg());
    }
}
