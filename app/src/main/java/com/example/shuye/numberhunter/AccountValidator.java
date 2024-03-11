package com.example.shuye.numberhunter;
import java.util.regex.Pattern;

public class AccountValidator {
    // 定义密码验证相关的常量
    private static final String ALLOWED_SPECIAL_CHARACTERS = "-_\\.$"; // 允许的特殊字符
    private static final int MIN_PASSWORD_LENGTH = 8; // 密码最小长度
    private static final int MAX_PASSWORD_LENGTH = 20; // 密码最大长度
    private static final int MAX_CONSECUTIVE_REPEATS = 4; // 密码中连续重复字符的最大个数
    private static final int MAX_SEQUENTIAL_CHARS_OR_DIGITS = 4; // 密码中顺序字符或数字的最大个数

    // 正则表达式模式字符串，用于检查密码是否只包含允许的字符
    private static final String PASSWORD_PATTERN_STRING =
            "^[a-zA-Z0-9" + Pattern.quote(ALLOWED_SPECIAL_CHARACTERS) + "]+$";

    // 编译好的正则表达式模式
    private static final Pattern PASSWORD_PATTERN =
            Pattern.compile(PASSWORD_PATTERN_STRING);

    // 检查密码是否包含连续重复的字符
    private static boolean containsConsecutiveRepeats(String password) {
        for (int i = 0; i < password.length() - MAX_CONSECUTIVE_REPEATS + 1; i++) {
            char currentChar = password.charAt(i);
            int repeatCount = 1;
            for (int j = i + 1; j < password.length() && repeatCount < MAX_CONSECUTIVE_REPEATS; j++) {
                if (password.charAt(j) == currentChar) {
                    repeatCount++;
                } else {
                    break;
                }
            }
            if (repeatCount == MAX_CONSECUTIVE_REPEATS) {
                return true; // 发现连续重复
            }
        }
        return false;
    }

    // 检查密码是否包含顺序的字符或数字（不区分大小写）
    private static boolean containsSequentialCharsOrDigits(String password) {
        for (int i = 0; i < password.length() - MAX_SEQUENTIAL_CHARS_OR_DIGITS + 1; i++) {
            char baseChar = Character.toLowerCase(password.charAt(i));
            for (int j = 1; j < MAX_SEQUENTIAL_CHARS_OR_DIGITS; j++) {
                if (i + j >= password.length()) {
                    break;
                }
                char nextExpectedChar = (char) (baseChar + j);
                char nextActualChar = Character.toLowerCase(password.charAt(i + j));
                if (nextActualChar != nextExpectedChar) {
                    break;
                }
                if (j == MAX_SEQUENTIAL_CHARS_OR_DIGITS - 1) {
                    return true; // 发现顺序字符或数字
                }
            }
        }
        return false;
    }

    // 主要的密码验证方法
    public static String validatePassword(String password) {
        // 检查密码是否为空
        if (password == null || password.isEmpty()) {
            return "密码验证失败：密码不能为空";
        }

        // 检查密码是否匹配允许的字符模式
        if (!PASSWORD_PATTERN.matcher(password).matches()) {
            return "密码验证失败：密码只能包含大小写字母、数字以及特定符号（-_.$）";
        }

        // 检查密码长度是否过短
        if (password.length() < MIN_PASSWORD_LENGTH) {
            return "密码验证失败：密码过短，必须至少包含 " + MIN_PASSWORD_LENGTH + " 个字符";
        }

        // 检查密码长度是否过长
        if (password.length() > MAX_PASSWORD_LENGTH) {
            return "密码验证失败：密码过长，不能超过 " + MAX_PASSWORD_LENGTH + " 个字符";
        }

        // 检查密码是否包含连续重复的字符
        if (containsConsecutiveRepeats(password)) {
            return "密码验证失败：密码不能包含连续超过 " + MAX_CONSECUTIVE_REPEATS + " 位的相同字符";
        }

        // 检查密码是否包含顺序的字符或数字（不区分大小写）
        if (containsSequentialCharsOrDigits(password)) {
            return "密码验证失败：密码不能包含连续超过 " + MAX_SEQUENTIAL_CHARS_OR_DIGITS + " 位的顺序字符或数字";
        }

        // 密码验证通过
        return "密码验证成功"; // 返回明确的验证成功消息
    }
}