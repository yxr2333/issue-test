package com.ssssheep.test.work4.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * Created By Intellij IDEA
 *
 * @author Xinrui Yu
 * @date 2022/2/27 18:56 星期日
 */
@ApiModel(value = "统一返回结果")
public class Result implements Serializable {
    private static final long serialVersionUID = 1L;
    public static final int CODE_SUCCESS = 200;
    public static final int CODE_ERROR = 500;
    public static final int CODE_WARNING = 501;
    public static final int CODE_NOT_JUR = 403;
    public static final int CODE_NOT_LOGIN = 401;
    public static final int CODE_INVALID_REQUEST = 400;

    @ApiModelProperty(value = "业务状态码",notes = "成功：200\n,失败：500\n,警告：501\n,权限不足：403\n,未登录：401\n,")
    public int code;
    @ApiModelProperty(value = "业务信息")
    public String msg;
    @ApiModelProperty(value = "请求数据")
    public Object data;

    public int getCode(){
        return this.code;
    }

    public Result setMsg(String msg){
        this.msg = msg;
        return this;
    }

    public String getMsg(){
        return this.msg;
    }

    public Result setData(Object data){
         this.data = data;
         return this;
    }
    @SuppressWarnings("unchecked")
    public <T> T getData(Class<T> cs){
        return (T) data;
    }

    public Result(int code, String msg, Object data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    /**
     * 返回成功
     */
    public static Result success(){
        return new Result(CODE_SUCCESS,null,null);
    }
    public static Result success(String msg){
        return new Result(CODE_SUCCESS,msg,null);
    }
    public static Result success(String msg,Object data){
        return new Result(CODE_SUCCESS,msg,data);
    }
    public static Result success(Object data){
        return new Result(CODE_SUCCESS,null,data);
    }
    public static Result success(Object... data){
        return new Result(CODE_SUCCESS,null,data);
    }

    /**
     * 返回失败
     */
    public static Result error(){
        return new Result(CODE_ERROR,"error",null);
    }
    public static Result error(String msg){
        return new Result(CODE_ERROR,msg,null);
    }

    /**
     * 返回警告
     */
    public static Result warning(){
        return new Result(CODE_WARNING,"warning",null);
    }
    public static Result warning(String msg){
        return new Result(CODE_WARNING,msg,null);
    }

    /**
     * 未登录
     */
    public static Result notLogin(){
        return new Result(CODE_NOT_LOGIN,"未登录，请先进行登录",null);
    }

    /**
     * 没有权限
     */
    public static Result noPermissions(String msg){
        return new Result(CODE_NOT_JUR,msg,null);
    }

    public static Result get(int code,String msg){
        return new Result(code,msg,null);
    }


    /**
     * 根据布尔值来确定返回结果
     * @param b 布尔值
     * @return true = ok, false = error
     */
    public static Result getByBoolean(boolean b){
        return b ? success("ok") : error("error");
    }

    @Override
    public String toString() {
        return "Result{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }
}
