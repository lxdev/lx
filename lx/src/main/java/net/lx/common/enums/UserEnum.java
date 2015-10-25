package net.lx.common.enums;


/**
 * 用户枚举
 *
 */
public enum UserEnum 
{
	Root(-1),			//ROOT账号
	
	StatusStart(1),		//启用
	StatusStop(0),		//停用
	DeleteYes(1),		//已删除
	DeleteNo(0);		//未删除
	
	private final int value;
	
	UserEnum(int v){
		this.value = v;
	}
	
	public int value(){
		return value;
	}
}
