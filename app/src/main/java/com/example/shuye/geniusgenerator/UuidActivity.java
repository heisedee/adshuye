package com.example.shuye.geniusgenerator;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import com.example.shuye.R;

import java.util.UUID;

public class UuidActivity extends AppCompatActivity {

    // 定义日志标签
    private static final String TAG = "UuidActivity";
    // 定义用于保存UUID的键
    private static final String UUID_KEY = "uuid_key";
    // 定义UUID字符串变量
    private String uuidString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_uuid); // 设置Activity的布局

        // 如果Activity之前保存过状态，则从中恢复UUID
        if (savedInstanceState != null) {
            uuidString = savedInstanceState.getString(UUID_KEY);
        }

        // 检查uuidString是否为空，如果为空则生成新的UUID
        if (uuidString == null) {
            UUID uuid = UUID.randomUUID(); // 生成随机UUID
            uuidString = uuid.toString(); // 将UUID转换为字符串
            Log.d(TAG, "生成了新的UUID: " + uuidString); // 记录日志
        } else {
            Log.d(TAG, "从保存的状态中恢复了UUID: " + uuidString); // 记录日志
        }

        // 查找布局中的TextView组件，用于显示UUID
        TextView uuidTextView = findViewById(R.id.uuid_text);

        // 检查uuidTextView是否为空，如果不为空则设置其文本为UUID字符串
        if (uuidTextView != null) {
            uuidTextView.setText(uuidString); // 在TextView中显示UUID
        } else {
            Log.e(TAG, "UUID TextView为空"); // 记录错误日志
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        // 在Activity可能被销毁前保存UUID状态
        if (uuidString != null) {
            outState.putString(UUID_KEY, uuidString); // 将UUID字符串保存到Bundle中
        }
    }
}
