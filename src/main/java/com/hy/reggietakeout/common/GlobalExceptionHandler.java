package com.hy.reggietakeout.common;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;

/**
 * @author arainetion
 * @version 1.0
 * @date 2023/5/18 17:17
 * @description 全局异常处理
 */
@Slf4j
@ResponseBody
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(SQLException.class)
    public R<String> sqlExceptionHandler(SQLException e) {

        log.error(e.getMessage());
        if (e instanceof SQLIntegrityConstraintViolationException)
            return R.error("该账号已存在");
        return R.error(e.getMessage());
    }
}
