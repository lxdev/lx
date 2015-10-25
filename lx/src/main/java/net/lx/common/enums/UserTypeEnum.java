package net.lx.common.enums;


/**
 * 用户枚举
 *
 */
public enum UserTypeEnum 
{
	STUDENT(1),							//验证码错误
	COUNSELOR(2),							//没有此用户
	MANAGER(3);						//密码错误
	
	public int nCode;
	
	private UserTypeEnum(int _nCode) {
		this.nCode = _nCode;
	}
	
	@Override
	public String toString(){
		return String.valueOf(this.nCode);
	}
	
}
