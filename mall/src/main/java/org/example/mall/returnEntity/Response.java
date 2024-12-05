package org.example.mall.returnEntity;

/**
 * 通用返回消息体
 * @param <T> 数据的类型
 */
public class Response<T> {

    // 返回数据
    private T data;

    // 消息
    private String msg;

    // 状态码
    private String code;

    // 默认构造器
    public Response() {}

    // 构造器
    public Response(T data, String msg, String code) {
        this.data = data;
        this.msg = msg;
        this.code = code;
    }

    // 获取数据
    public T getData() {
        return data;
    }

    // 设置数据
    public void setData(T data) {
        this.data = data;
    }

    // 获取消息
    public String getMsg() {
        return msg;
    }

    // 设置消息
    public void setMsg(String msg) {
        this.msg = msg;
    }

    // 获取状态码
    public String getCode() {
        return code;
    }

    // 设置状态码
    public void setCode(String code) {
        this.code = code;
    }

    // 返回成功响应，带数据
    public static <T> Response<T> success(T data) {
        return new Response<>(data, "操作成功", "200");
    }

    // 返回成功响应，无数据，第一个<T>说明这是一个泛型方法，Response<T>相当于指明了类型
    public static <T> Response<T> success() {
        return new Response<>(null, "操作成功", "200");
    }

    // 返回成功响应，自定义消息
    public static <T> Response<T> success(String msg) {
        return new Response<>(null, msg, "200");
    }
    public static <T> Response<T> success(T data,String code) {
        return new Response<>(data, "操作成功", code);
    }
    // 返回失败响应，带自定义消息
    public static <T> Response<T> failure(String msg) {
        return new Response<>(null, msg, "500");
    }

    // 返回失败响应，带自定义消息和数据
    public static <T> Response<T> failure(String msg, T data) {
        return new Response<>(data, msg, "500");
    }

    //注册时账户已经存在所以code设置为0,
    public static <T> Response<T> failure(String msg, String code) {
        return new Response<>(null,msg,code);
    }

    // 返回未授权响应
    public static <T> Response<T> unauthorized(String msg) {
        return new Response<>(null, msg, "401");
    }

    // 返回禁止访问响应
    public static <T> Response<T> forbidden(String msg) {
        return new Response<>(null, msg, "403");
    }

    // 返回无数据响应
    public static <T> Response<T> noContent() {
        return new Response<>(null, "无内容", "204");
    }

    // 返回请求错误响应
    public static <T> Response<T> badRequest(String msg) {
        return new Response<>(null, msg, "400");
    }
}
