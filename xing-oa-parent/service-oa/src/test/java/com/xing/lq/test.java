package com.xing.lq;

import org.springframework.boot.test.context.SpringBootTest;

/**
 * @Description:
 * @Author: Wang Xing
 * @Date: 11:02 2023/9/21
 */
@SpringBootTest
public class test {

    public static void main(String[] args) {
        String[] strs = {"flooooo", "floa", "flow"};
        String prefix = longestCommonPrefix(strs);
        System.out.println(prefix);
    }

    public static String longestCommonPrefix(String[] strs) {
        if (strs.length == 0) return "";
        String str = strs[0];
        for (int i = 1; i < strs.length; i++) {
            int j = 0;
            for (; j < strs[i].length() && j < str.length(); j++) {
                if (str.charAt(j) != strs[i].charAt(j)) break;
            }
            str = str.substring(0, j);
            if (str.equals("")) return str;
        }
        return str;
    }

    public static String longestCommonPrefix1(String[] strs) {
        if(strs.length == 0)
            return "";
        String str = strs[0];
        for (int i = 1; i < strs.length; i++){
            int j = 0;
            for (; j < str.length() && j < strs[i].length(); j++) {
                if (str.charAt(j) != strs[i].charAt(j)) break;
            }
            str = str.substring(0, j);
            if (str.equals(""))
                return str;
        }
        return str;
    }

}
