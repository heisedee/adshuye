package com.example.shuye.numberhunter;

import java.util.ArrayList;
import java.util.List;

public class AssistanceValidation {

    private static final int MAX_LENGTH = 30;

    /**
     * 验证辅助信息是否符合规范，并返回结果。
     *
     * @param question 辅助问题
     * @param prompt   辅助提示
     * @param answer   辅助答案
     * @return 包含辅助问题、提示和答案的字符串数组，如果验证通过；否则抛出异常。
     * @throws IllegalArgumentException 如果输入不符合规范，则抛出具有详细信息的异常。
     */
    public static String[] validateAssistanceInfo(String question, String prompt, String answer) {
        // 创建一个列表来存储验证错误消息
        List<String> validationErrors = new ArrayList<>();

        // 检查所有字段是否都不为空，或者都为空
        if (!((question.isEmpty() && prompt.isEmpty() && answer.isEmpty()) ||
                (!question.isEmpty() && !prompt.isEmpty() && !answer.isEmpty()))) {
            validationErrors.add("所有字段必须同时为空或同时填写。");
        }

        // 检查字段长度是否超过最大限制
        if (question.length() > MAX_LENGTH) {
            validationErrors.add("辅助问题字数过长，最大长度：" + MAX_LENGTH + "，当前长度：" + question.length());
        }
        if (prompt.length() > MAX_LENGTH) {
            validationErrors.add("辅助提示字数过长，最大长度：" + MAX_LENGTH + "，当前长度：" + prompt.length());
        }
        if (answer.length() > MAX_LENGTH) {
            validationErrors.add("辅助答案字数过长，最大长度：" + MAX_LENGTH + "，当前长度：" + answer.length());
        }

        // 如果有验证错误，则抛出异常
        if (!validationErrors.isEmpty()) {
            throw new IllegalArgumentException(String.join("\n", validationErrors));
        }

        // 如果验证通过，则返回结果
        return new String[]{question, prompt, answer};
    }
}