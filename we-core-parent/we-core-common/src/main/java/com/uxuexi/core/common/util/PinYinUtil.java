/**
 * PinYinUtil.java
 * com.uxuexi.core.common.util
 * Copyright (c) 2014, 北京聚智未来科技有限公司版权所有.
*/

package com.uxuexi.core.common.util;

import static com.uxuexi.core.common.util.Util.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.HanyuPinyinVCharType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

/**
 * 拼音工具类
 *
 * @author   朱晓川
 * @Date	 Aug 26, 2014 	 
 */
public final class PinYinUtil {
	/**   
	   * 获取拼音集合   
	   * @param src   
	   * @return Set<String>   
	   */
	@SuppressWarnings("cast")
	private static Set<String> getPinyinSet(final String src) {
		if (src != null && !src.trim().equalsIgnoreCase("")) {
			char[] srcChar;
			srcChar = src.toCharArray();
			//汉语拼音格式输出类       
			HanyuPinyinOutputFormat hanYuPinOutputFormat = new HanyuPinyinOutputFormat();
			//输出设置，大小写，音标方式等       
			hanYuPinOutputFormat.setCaseType(HanyuPinyinCaseType.LOWERCASE);
			hanYuPinOutputFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
			hanYuPinOutputFormat.setVCharType(HanyuPinyinVCharType.WITH_V);
			String[][] temp = new String[src.length()][];
			for (int i = 0; i < srcChar.length; i++) {
				char c = srcChar[i];
				//是中文或者a-z或者A-Z转换拼音(我的需求，是保留中文或者a-z或者A-Z)       
				if (String.valueOf(c).matches("[\\u4E00-\\u9FA5]+")) {
					try {
						temp[i] = PinyinHelper.toHanyuPinyinStringArray(srcChar[i], hanYuPinOutputFormat);
					} catch (BadHanyuPinyinOutputFormatCombination e) {
						e.printStackTrace();
					}
				} else if (((int) c >= 65 && (int) c <= 90) || ((int) c >= 97 && (int) c <= 122)
						|| ((int) c >= 48 && (int) c <= 57)) {
					temp[i] = new String[] { String.valueOf(srcChar[i]) };
				} else {
					temp[i] = new String[] { "" };
				}
			}
			String[] pingyinArray = exchange(temp);
			Set<String> pinyinSet = new HashSet<String>();
			for (String element : pingyinArray) {
				pinyinSet.add(element);
			}
			return pinyinSet;
		}
		return null;
	}

	/**   
	   * 递归   
	   * @param strJaggedArray   
	   * @return 数组  
	   */
	private static String[] exchange(final String[][] strJaggedArray) {
		String[][] temp = doExchange(strJaggedArray);
		return temp[0];
	}

	/**   
	  * 递归   
	  * @param strJaggedArray   
	  * @return 数组  
	  */
	private static String[][] doExchange(final String[][] strJaggedArray) {
		int len = strJaggedArray.length;
		if (len >= 2) {
			int len1 = strJaggedArray[0].length;
			int len2 = strJaggedArray[1].length;
			int newlen = len1 * len2;
			String[] temp = new String[newlen];
			int Index = 0;
			for (int i = 0; i < len1; i++) {
				for (int j = 0; j < len2; j++) {
					temp[Index] = strJaggedArray[0][i] + strJaggedArray[1][j];
					Index++;
				}
			}
			String[][] newArray = new String[len - 1][];
			for (int i = 2; i < len; i++) {
				newArray[i - 1] = strJaggedArray[i];
			}
			newArray[0] = temp;
			return doExchange(newArray);
		}
		return strJaggedArray;
	}

	/**
	 * 获取汉字全拼
	 *
	 * @param src 汉字
	 * @return 全拼
	 */
	public static String getPinyin(final String src) {
		if (isEmpty(src)) {
			return "";
		}
		Set<String> stringSet = getPinyinSet(src);
		if (isEmpty(stringSet)) {
			return "";
		}
		return stringSet.iterator().next().toLowerCase();
	}

	/**
	 * 查询字母集合
	 * <p/>
	 * 每个字母都是大写
	 * 
	 * @return 返回英文字母集合
	 */
	public static List<String> listLetters() {
		String pinyin = "A,B,C,D,E,F,G,H,I,J,K,L,M,N,O,P,Q,R,S,T,U,V,W,X,Y,Z";
		return ArrayUtil.array2List(pinyin.split(","));
	}
}
