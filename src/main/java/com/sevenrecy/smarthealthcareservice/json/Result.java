package com.sevenrecy.smarthealthcareservice.json;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class Result {

//    @ApiModelProperty(value = "是否成功")
    private Boolean success;

//    @ApiModelProperty(value = "返回码")
    private Integer code;

//    @ApiModelProperty(value = "返回消息")
    private String message;

//    @ApiModelProperty(value = "返回数据")
    private Map<String, Object> data = new HashMap<String, Object>();

    public Result(){}

    /**
     * 功能描述：成功返回
     *
     * @return Result 成功返回结果
     * @description:
     */
    public static Result ok(){
        Result r = new Result();
        r.setSuccess(ResultCodeEnum.SUCCESS.getSuccess());
        r.setCode(ResultCodeEnum.SUCCESS.getCode());
        r.setMessage(ResultCodeEnum.SUCCESS.getMessage());
        return r;
    }

    /**
     * 功能描述：失败返回
     *
     * @return Result 失败返回结果
     */
    public static Result error(){
        Result r = new Result();
        r.setSuccess(ResultCodeEnum.UNKNOWN_REASON.getSuccess());
        r.setCode(ResultCodeEnum.UNKNOWN_REASON.getCode());
        r.setMessage(ResultCodeEnum.UNKNOWN_REASON.getMessage());
        return r;
    }

    /**
     * 功能描述：设置返回结果
     *
     * @param resultCodeEnum 结果枚举
     * @return Result 返回给前端的结果
     */
    public static Result setResult(ResultCodeEnum resultCodeEnum){
        Result r = new Result();
        r.setSuccess(resultCodeEnum.getSuccess());
        r.setCode(resultCodeEnum.getCode());
        r.setMessage(resultCodeEnum.getMessage());
        return r;
    }

    /**
     * 功能描述：设置success状态
     *
     * @param success 值为true或false
     * @return Result 返回给前端的数据
     */
    public Result success(Boolean success){
        this.setSuccess(success);
        return this;
    }

    /**
     * 功能描述：设置message内容
     *
     * @param message 消息内容
     * @return Result 返回给前端的数据
     */
    public Result message(String message){
        this.setMessage(message);
        return this;
    }

    /**
     * 功能描述：设置code内容
     *
     * @param code 返回码
     * @return Result 返回给前端的数据
     */
    public Result code(Integer code){
        this.setCode(code);
        return this;
    }

    /**
     * 功能描述：单值设置数据
     *
     * @param key 键
     * @param value 值
     * @return Result 返回给前端的数据
     */
    public Result data(String key, Object value){
        this.data.put(key, value);
        return this;
    }

    /**
     * 功能描述：多值设置数据
     *
     * @param map 集合
     * @return Result 返回给前端的数据
     */
    public Result data(Map<String, Object> map){
        this.setData(map);
        return this;
    }
}