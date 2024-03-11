package com.example.shuye.numberhunter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ChinaPhoneNumberValidator {

    // 正则表达式匹配中国大陆手机号的基本格式
    private static final Pattern CHINA_PHONE_NUMBER_PATTERN =
            Pattern.compile("^1[3-9]\\d{9}$");

    /**
     * 验证手机号是否符合中国大陆的格式，并给出相应提示
     *
     * @param phoneNumber 要验证的手机号
     * @return 如果手机号符合格式，返回号码本身；否则返回相应的错误提示
     */
    public static String validateChinaPhoneNumber(String phoneNumber) {
        if (phoneNumber == null || phoneNumber.isEmpty()) {
            return "手机号不能为空";
        }

        // 去除可能存在的空格或其他非数字字符
        String cleanedNumber = phoneNumber.replaceAll("\\D+", "");

        // 检查是否只包含数字且长度正确
        if (!cleanedNumber.matches("\\d{11}")) {
            return "手机号只能包含11位数字";
        }

        // 使用正则表达式匹配手机号格式
        Matcher matcher = CHINA_PHONE_NUMBER_PATTERN.matcher(cleanedNumber);
        if (!matcher.matches()) {
            return "手机号格式不正确";
        }

        // 如果所有检查都通过，返回清理后的手机号
        return cleanedNumber;
    }
}
