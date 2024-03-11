package com.example.shuye.numberhunter;
import android.util.Log;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class AccountRegistrationValidator {

    // 日志标签
    private static final String TAG = "AccountValidator";
    // 账号验证的正则表达式（只允许大小写字母和数字，6到15位）
    private static final String ACCOUNT_VALIDATION_PATTERN = "^[A-Za-z0-9]{6,15}$";
    // 日期格式化，用于记录日志时间
    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());

    /**
     * 验证账号是否有效。
     *
     * @param account 要验证的账号
     * @return 如果账号有效，则返回表示成功的字符串；否则返回null。
     */
    public String validateAccount(String account) {
        // 检查账号是否为空
        if (account == null || account.trim().isEmpty()) {
            logError(TAG, "账号为空");
            return null;
        }

        // 使用正则表达式验证账号格式
        if (!account.matches(ACCOUNT_VALIDATION_PATTERN)) {
            logError(TAG, "账号格式不正确: " + account);
            return null;
        }

        // 检查账号是否包含连续重复的字符
        if (containsConsecutiveRepeatedChars(account, 4)) {
            logError(TAG, "账号包含连续重复字符: " + account);
            return null;
        }

        // 检查账号是否包含连续递增/递减数字
        if (containsConsecutiveSequentialNumbers(account, 4)) {
            logError(TAG, "账号包含连续递增/递减数字: " + account);
            return null;
        }

        // 检查账号是否包含连续超过5位的字母顺序号（大小写包括在内）
        if (containsConsecutiveAlphabeticalSequence(account, 5)) {
            logError(TAG, "账号包含连续字母顺序号: " + account);
            return null;
        }

        // 账号验证成功，返回成功字符串
        return "账号验证成功: " + account;
    }

    /**
     * 记录错误信息到日志。
     *
     * @param tag 日志标签
     * @param message 错误信息
     */
    private void logError(String tag, String message) {
        String timestamp = DATE_FORMAT.format(new Date());
        Log.e(tag, timestamp + " - " + message);
    }

    /**
     * 检查字符串是否包含连续重复的字符。
     *
     * @param str 要检查的字符串
     * @param maxRepeat 允许的最大重复次数
     * @return 如果包含连续重复的字符，则返回true；否则返回false。
     */
    private boolean containsConsecutiveRepeatedChars(String str, int maxRepeat) {
        for (int i = 0; i < str.length() - maxRepeat + 1; i++) {
            char repeatedChar = str.charAt(i);
            boolean isRepeated = true;
            for (int j = 1; j < maxRepeat; j++) {
                if (str.charAt(i + j) != repeatedChar) {
                    isRepeated = false;
                    break;
                }
            }
            if (isRepeated) {
                return true;
            }
        }
        return false;
    }

    /**
     * 检查字符串是否包含连续递增/递减数字。
     *
     * @param str 要检查的字符串
     * @param minSequence 最小连续序列长度
     * @return 如果包含连续递增/递减数字，则返回true；否则返回false。
     */
    private boolean containsConsecutiveSequentialNumbers(String str, int minSequence) {
        for (int i = 0; i < str.length() - minSequence + 1; i++) {
            int startValue = Character.getNumericValue(str.charAt(i));
            if (startValue == -1) continue; // 跳过非数字字符

            boolean isIncreasing = true;
            boolean isDecreasing = true;
            int currentValue = startValue;

            for (int j = 1; j < minSequence; j++) {
                int nextValue = Character.getNumericValue(str.charAt(i + j));
                if (nextValue == -1) break; // 遇到非数字字符，中断当前序列检查

                if (nextValue != currentValue + 1) {
                    isIncreasing = false;
                }
                if (nextValue != currentValue - 1) {
                    isDecreasing = false;
                }

                currentValue = nextValue;
            }

            if (isIncreasing || isDecreasing) {
                return true; // 找到了连续递增或递减的序列
            }
        }
        return false; // 没有找到符合条件的序列
    }

    /**
     * 检查字符串是否包含连续超过指定长度的字母顺序号（大小写包括在内）。
     *
     * @param str 要检查的字符串
     * @param minSequence 最小连续序列长度
     * @return 如果包含连续字母顺序号，则返回true；否则返回false。
     */
    private boolean containsConsecutiveAlphabeticalSequence(String str, int minSequence) {
        str = str.toLowerCase(); // 统一转换为小写以简化比较
        for (int i = 0; i < str.length() - minSequence + 1; i++) {
            char currentChar = str.charAt(i);
            for (int j = 1; j < minSequence; j++) {
                char nextChar = str.charAt(i + j);
                if (nextChar - currentChar != j) {
                    break;
                }
                if (j == minSequence - 1) {
                    return true;
                }
            }
        }
        return false;
    }
}