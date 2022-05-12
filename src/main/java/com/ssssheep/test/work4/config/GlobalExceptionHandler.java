package com.ssssheep.test.work4.config;

import cn.dev33.satoken.exception.DisableLoginException;
import cn.dev33.satoken.exception.NotLoginException;
import cn.dev33.satoken.exception.NotPermissionException;
import cn.dev33.satoken.exception.NotRoleException;
import com.ssssheep.test.work4.dto.Result;
import org.quartz.SchedulerException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Objects;

/**
 * Created By Intellij IDEA
 *
 * @author Xinrui Yu
 * @date 2022/2/27 18:54 星期日
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler
    public Result handlerException(Exception e, HttpServletRequest request, HttpServletResponse response) throws Exception{
        System.out.println("**************全局异常**************");
        e.printStackTrace();
        Result result = null;
        if(e instanceof NotLoginException){
            result = Result.notLogin().setMsg("未登录或登录已过期,请重新登录!");
        } else if (e instanceof NotRoleException){
            NotRoleException notRoleException = (NotRoleException) e;
            result = Result.noPermissions("需要角色:" + notRoleException.getRole());
        } else if (e instanceof NotPermissionException){
            NotPermissionException notPermissionException = (NotPermissionException) e;
            result = Result.noPermissions("需要权限:" + notPermissionException.getCode());
        } else if (e instanceof DisableLoginException){
            DisableLoginException disableLoginException = (DisableLoginException) e;
            result = Result.noPermissions("账号已被封禁:" + disableLoginException.getDisableTime() + "秒后解封!");
        } else if (e instanceof DataIntegrityViolationException){
            DataIntegrityViolationException exception = (DataIntegrityViolationException) e;
            result = Result.error("数据异常,操作失败!请联系管理员处理!");
        } else if(e instanceof MethodArgumentNotValidException){
            MethodArgumentNotValidException exception = (MethodArgumentNotValidException) e;
            FieldError fieldError = exception .getBindingResult().getFieldError();
            String field = Objects.requireNonNull(fieldError).getField();
            String msg = fieldError.getDefaultMessage();
            result = Result.error("数据输入不符合规定,错误字段:" + field + ",出错信息:" + msg );
        } else if (e instanceof SchedulerException) {
            SchedulerException exception = (SchedulerException) e;
            result = Result.error("定时任务异常,错误:" + exception.getMessage());
        } else {
            result = Result.error(e.getMessage());
        }
        return result;
    }
}
