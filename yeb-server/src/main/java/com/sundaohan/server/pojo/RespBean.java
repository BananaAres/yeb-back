package com.sundaohan.server.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Auther sundaohan
 * @Package com.sundaohan.server.pojo
 * @Title RespBean
 * @Description TODO
 * @Date 2021/7/23 下午11:52
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RespBean {
    private long code;
    private String message;
    private Object obj;

    /**
     * @Title success
     * @Description 成功返回结果
     * @Author sundaohan
     * @Params [message]
     * @return com.sundaohan.server.pojo.RespBean
     */
    public static RespBean success(String message){
        return new RespBean(200, message, null);
    }
    /**
     * @Title success
     * @Description 成功返回结果
     * @Author sundaohan
     * @Params [message, obj]
     * @return com.sundaohan.server.pojo.RespBean
     */
    public static RespBean success(String message, Object obj){
        return new RespBean(200, message, obj);
    }
    /**
     * @Title error
     * @Description 失败返回信息
     * @Author sundaohan
     * @Params [message]
     * @return com.sundaohan.server.pojo.RespBean
     */
    public static RespBean error(String message){
        return new RespBean(500,message,null);
    }
    /**
     * @Title error
     * @Description 失败返回结果
     * @Author sundaohan
     * @Params [message, obj]
     * @return com.sundaohan.server.pojo.RespBean
     */
    public static RespBean error(String message,Object obj){
        return new RespBean(500,message,obj);
    }
}
