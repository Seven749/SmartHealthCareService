package com.sevenrecy.smarthealthcareservice.json;

import lombok.Getter;
import lombok.ToString;
@Getter
@ToString
public enum ResultCodeEnum {

    SUCCESS(true, 20000, "成功"),
    UNKNOWN_REASON(false, 20001, "未知错误"),

    BAD_SQL_GRAMMAR(false, 21001, "sql语法错误"),
    JSON_PARSE_ERROR(false, 21002, "json解析异常"),
    PARAM_ERROR(false, 21003, "参数不正确"),

    FILE_UPLOAD_ERROR(false, 21004, "文件上传错误"),
    FILE_DELETE_ERROR(false, 21005, "文件刪除错误"),
    EXCEL_DATA_IMPORT_ERROR(false, 21006, "Excel数据导入错误"),

    VIDEO_UPLOAD_ALIYUN_ERROR(false, 22001, "视频上传至阿里云失败"),
    VIDEO_UPLOAD_TOMCAT_ERROR(false, 22002, "视频上传至业务服务器失败"),
    VIDEO_DELETE_ALIYUN_ERROR(false, 22003, "阿里云视频文件删除失败"),
    FETCH_VIDEO_UPLOADAUTH_ERROR(false, 22004, "获取上传地址和凭证失败"),
    REFRESH_VIDEO_UPLOADAUTH_ERROR(false, 22005, "刷新上传地址和凭证失败"),
    FETCH_PLAYAUTH_ERROR(false, 22006, "获取播放凭证失败"),

    URL_ENCODE_ERROR(false, 23001, "URL编码失败"),
    ILLEGAL_CALLBACK_REQUEST_ERROR(false, 23002, "非法回调请求"),
    FETCH_ACCESSTOKEN_FAILD(false, 23003, "获取accessToken失败"),
    FETCH_USERINFO_ERROR(false, 23004, "获取用户信息失败"),
    LOGIN_ERROR(false, 23005, "登录失败"),

    COMMENT_EMPTY(false, 24006, "评论内容必须填写"),

    PAY_RUN(false, 25000, "支付中"),
    PAY_UNIFIEDORDER_ERROR(false, 25001, "统一下单错误"),
    PAY_ORDERQUERY_ERROR(false, 25002, "查询支付结果错误"),

    ORDER_EXIST_ERROR(false, 25003, "课程已购买"),

    GATEWAY_ERROR(false, 26000, "服务不能访问"),

    CODE_ERROR(false, 28000, "验证码错误"),

    LOGIN_PHONE_NULL_ERROR(false,28010, "手机号未注册用户"),
    LOGIN_PHONE_ERROR(false, 28009, "手机号码不正确"),
    LOGIN_ACCOUNT_ERROR(false, 28001, "账号不正确"),
    LOGIN_PASSWORD_ERROR(false, 28008, "密码不正确"),
    USER_NULL_ERROR(false, 28012, "用户不存在"),
    LOGIN_NULL_ERROR(false,28013, "账户或密码错误"),
    LOGIN_DISABLED_ERROR(false, 28002, "该用户已被禁用"),
    REGISTER_MOBILE_ERROR(false, 28003, "手机号已被注册"),
    REGISTER_IDCARD_ERROR(false, 28011, "身份证已被注册"),
    LOGIN_AUTH(false, 28004, "需要登录"),
    LOGIN_ACL(false, 28005, "没有权限"),
    SMS_SEND_ERROR(false, 28006, "短信发送失败"),
    SMS_SEND_ERROR_BUSINESS_LIMIT_CONTROL(false, 28007, "短信发送过于频繁"),
    DATABASE_ERROR(false, 28101, "数据库异常"),

    REGISTRATION_DT_ERROR(false, 28200, "时间已过期"),
    DEPT_NULL_ERROR(false, 28201, "科室不存在"),
    DOCTOR_NULL_ERROR(false,28202, "医生不存在"),
    REGISTRATION_TIME_ERROR(false, 28203, "时间已占用"),
    REGISTRATION_NULL_ERROR(false, 28204, "未查询到记录"),
    DRUG_NULL_ERROR(false, 28205, "药品不存在"),
    DRUG_EXIST_ERROR(false, 28205, "药品已存在"),
    PRESCRIPTION_NULL_ERROR(false, 28206, "处方不存在"),
    HISTORIES_NULL_ERROR(false ,28207, "病历不存在"),
    PRESCRIPTION_EXIST_ERROR(false, 28208, "处方已存在"),
    ITEM_EXIST_ERROR(false, 28209, "项目已存在"),
    ITEM_NULL_ERROR(false, 28210, "项目不存在"),
    CHECK_EXIST_ERROR(false, 28211, "检查项目已存在"),
    CHECK_NULL_ERROR(false, 28212, "检查项目不存在"),
    DRUG_BILL_CREATE_ERROR(false, 28213, "药品账单创建失败"),
    PANDB_EXIST_ERROR(false, 28214, "处方和账单已存在"),
    CANDB_EXIST_ERROR(false, 28215, "检查和项目已存在"),
    ITEM_BILL_CREATE_ERROR(false, 28213, "项目账单创建失败"),
    PCOUNT_UPDATE_ERROR(false, 28214, "处方数量更新失败"),
    CCOUNT_UPDATE_ERROR(false, 28215, "检查单数量更新失败"),
    REPORT_NULL_ERROR(false, 28216, "检查报告单不存在"),
    HOSPITAL_CASE_NULL_ERROR(false, 28217, "未获取到住院病案记录")
    ;



    private Boolean success;

    private Integer code;

    private String message;

    ResultCodeEnum(Boolean success, Integer code, String message) {
        this.success = success;
        this.code = code;
        this.message = message;
    }
}