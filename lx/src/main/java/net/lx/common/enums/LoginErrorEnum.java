package net.lx.common.enums;

/**
 * 登录错误枚举
 *
 */
public enum LoginErrorEnum 
{
	CHECK_RAND,							//验证码错误
	NO_USER,							//没有此用户
	PASSWORD_ERROR,						//密码错误
	LOCKED,								//账号被锁定
	NOT_MANAGER;
}
