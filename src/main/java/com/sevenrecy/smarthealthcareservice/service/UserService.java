package com.sevenrecy.smarthealthcareservice.service;

import com.sevenrecy.smarthealthcareservice.entity.User;
import org.apache.ibatis.annotations.Param;

public interface UserService {
    
    /**
     * 调用此接口可通过id获取用户信息
     * @param user_id 用户id
     * @return
     */
    User selectSysUserById(int user_id);

    /**
     * 调用此接口可通过手机号获取用户信息
     * @param tel 手机号
     * @return 用户信息
     */
    User selectSysUserByTel(String tel);

    /**
     * 调用此接口可通过身份证获取用户信息
     * @param IDCard 身份证
     * @return 用户信息
     */
    User selectSysUserByIDCard(String IDCard);

    /**
     * 注册时调用此接口进行注册
     * @param user 用户
     * @return
     */
    int insertSysUser(User user);

    /**
     * 修改密码时先查询信息是否正确
     * @param IDCard 身份证
     * @param tel 手机号码
     * @return
     */
    User selectSysUserByIDCardAndTel(@Param("IDCard") String IDCard, @Param("tel") String tel);

    /**
     * 找回密码时调用此接口进行修改密码
     * @param user_id 用户id
     * @param pwd 用户新密码
     * @return
     */
    int updateSysUserPwd(int user_id, String tel , String pwd);

    /**
     * 住院预交费调用此接口
     * @param user_id 用户id
     * @param balance 用户余额
     * @return
     */
    int updateBalance(@Param("user_id") int user_id, @Param("balance") double balance);

    /**
     * 更新用户就诊次数
     * @param user_id 用户id
     * @return
     */
    int updateCount(@Param("user_id") int user_id);
}
