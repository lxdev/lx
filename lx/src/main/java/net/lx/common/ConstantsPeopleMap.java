package net.lx.common;

import java.util.HashMap;
import java.util.Map;

/**
 * 民族代码表
 * 
 */
public class ConstantsPeopleMap {

	private static Map<String, Integer> peopleCodeMap = null;// 民族代码表

	public static Integer getCode(String name) {
		if (peopleCodeMap == null)
			init();
		return peopleCodeMap.get(name) == null ? 0 : peopleCodeMap.get(name);
	}


	public static void init() {
		if (peopleCodeMap == null) {
			peopleCodeMap = new HashMap<String, Integer>();
			peopleCodeMap.put("汉族", 1);
			peopleCodeMap.put("蒙古族", 2);
			peopleCodeMap.put("回族", 3);
			peopleCodeMap.put("藏族", 4);
			peopleCodeMap.put("维吾尔族", 5);
			peopleCodeMap.put("苗族", 6);
			peopleCodeMap.put("彝族", 7);
			peopleCodeMap.put("壮族", 8);
			peopleCodeMap.put("布依族", 9);
			peopleCodeMap.put("朝鲜族", 10);
			peopleCodeMap.put("满族", 11);
			peopleCodeMap.put("侗族", 12);
			peopleCodeMap.put("瑶族", 13);
			peopleCodeMap.put("白族", 14);
			peopleCodeMap.put("土家族", 15);
			peopleCodeMap.put("哈尼族", 16);
			peopleCodeMap.put("哈萨克族", 17);
			peopleCodeMap.put("傣族", 18);
			peopleCodeMap.put("黎族", 19);
			peopleCodeMap.put("傈傈族", 20);

			peopleCodeMap.put("佤族", 21);
			peopleCodeMap.put("畲族", 22);
			peopleCodeMap.put("高山族", 23);
			peopleCodeMap.put("拉祜族", 24);
			peopleCodeMap.put("水族", 25);
			peopleCodeMap.put("东乡族", 26);
			peopleCodeMap.put("纳西族", 27);
			peopleCodeMap.put("景颇族", 28);
			peopleCodeMap.put("柯尔族", 29);
			peopleCodeMap.put("土族", 30);
			peopleCodeMap.put("达斡尔族", 31);
			peopleCodeMap.put("仫佬族", 32);
			peopleCodeMap.put("羌族", 33);
			peopleCodeMap.put("布朗族", 34);
			peopleCodeMap.put("撒拉族", 35);
			peopleCodeMap.put("毛难族", 36);
			peopleCodeMap.put("仡佬族", 37);
			peopleCodeMap.put("锡伯族", 38);
			peopleCodeMap.put("阿昌族", 39);
			peopleCodeMap.put("普米族", 40);

			peopleCodeMap.put("塔吉克族", 41);
			peopleCodeMap.put("怒族", 42);
			peopleCodeMap.put("乌孜别克族", 43);
			peopleCodeMap.put("俄罗斯族", 44);
			peopleCodeMap.put("鄂温克族", 45);
			peopleCodeMap.put("崩龙族", 46);
			peopleCodeMap.put("保安族", 47);
			peopleCodeMap.put("裕固族", 48);
			peopleCodeMap.put("京族", 49);
			peopleCodeMap.put("塔塔尔族", 50);
			peopleCodeMap.put("独龙族", 51);
			peopleCodeMap.put("鄂伦春族", 52);
			peopleCodeMap.put("赫哲族", 53);
			peopleCodeMap.put("门巴族", 54);
			peopleCodeMap.put("珞巴族", 55);
			peopleCodeMap.put("基诺族", 56);
			// peopleCodeMap.put("汉族", 57);
			// peopleCodeMap.put("汉族", 58);
			// peopleCodeMap.put("汉族", 59);
			// peopleCodeMap.put("汉族", 60);
			//
			// peopleCodeMap.put("汉族", 61);
			// peopleCodeMap.put("汉族", 62);
			// peopleCodeMap.put("汉族", 63);
			// peopleCodeMap.put("汉族", 64);
			// peopleCodeMap.put("汉族", 65);
			// peopleCodeMap.put("汉族", 66);
			// peopleCodeMap.put("汉族", 67);
			// peopleCodeMap.put("汉族", 68);
			// peopleCodeMap.put("汉族", 69);
			// peopleCodeMap.put("汉族", 70);
			// peopleCodeMap.put("汉族", 71);
			// peopleCodeMap.put("汉族", 72);
			// peopleCodeMap.put("汉族", 73);
			// peopleCodeMap.put("汉族", 74);
			// peopleCodeMap.put("汉族", 75);
			// peopleCodeMap.put("汉族", 76);
			// peopleCodeMap.put("汉族", 77);
			// peopleCodeMap.put("汉族", 78);
			// peopleCodeMap.put("汉族", 79);
			// peopleCodeMap.put("汉族", 80);
			//
			// peopleCodeMap.put("汉族", 81);
			// peopleCodeMap.put("汉族", 82);
			// peopleCodeMap.put("汉族", 83);
			// peopleCodeMap.put("汉族", 84);
			// peopleCodeMap.put("汉族", 85);
			// peopleCodeMap.put("汉族", 86);
			// peopleCodeMap.put("汉族", 87);
			// peopleCodeMap.put("汉族", 88);
			// peopleCodeMap.put("汉族", 89);
			// peopleCodeMap.put("汉族", 90);
			// peopleCodeMap.put("汉族", 91);
			// peopleCodeMap.put("汉族", 92);
			// peopleCodeMap.put("汉族", 93);
			// peopleCodeMap.put("汉族", 94);
			// peopleCodeMap.put("汉族", 95);
			// peopleCodeMap.put("", 96);
			peopleCodeMap.put("其他", 97);
			peopleCodeMap.put("外国血统中国籍人士", 98);
		}
	}

}
