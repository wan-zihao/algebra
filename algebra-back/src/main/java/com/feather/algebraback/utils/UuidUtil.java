package com.feather.algebraback.utils;

import java.util.UUID;

/**
 * 产生UUID随机字符串工具类
 * @author 惊鸿之羽
 */
public final class UuidUtil {
	private UuidUtil(){}
	/**
	获得uuid
	 */
	public static String getUuid(){
		return UUID.randomUUID().toString().replace("-","");
	}
}
