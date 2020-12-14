package com.papa.gradletest;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.widget.SwitchCompat;

import com.papa.library1.Library1Utils;
import com.papa.library2.Library2Utils;
import com.papa.library3.Library3Utils;

import java.util.ArrayList;
import java.util.List;




/**
 * ① 调试测试方法都在 {@link MainActivity#onClick(View)}
 * ② 断点管理区测试在 {@link MainActivity#managerArea()}
 * ③ 断点类型测试在 {@link MainActivity#breakpointTest()}
 */
public class MainActivity extends Activity implements View.OnClickListener, CompoundButton.OnCheckedChangeListener{

    private TextView textView;
    private Button add;
    private Button reduce;
    private Button reset;
    private SwitchCompat switchException;
    private SwitchCompat switchField;
    private List<SwitchCompat> switchList = new ArrayList<>();

    private Button channel;
    private Button developer;
    private Button service;

    // 属性断点测试：直接在声明成员变量处打断点就是属性断点；也可以在断点管理页面添加属性断点，选择具体的类和具体关注的成员变量
    private int fieldBreakPoint = 1;

    private boolean enableException = false;
    private boolean enableField = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        add = findViewById(R.id.btn_add);
        reduce = findViewById(R.id.btn_reduce);
        reset = findViewById(R.id.btn_reset);
        textView = findViewById(R.id.tv);
        textView.setText(String.valueOf(0));
        switchException = findViewById(R.id.sw_exception);
        switchField = findViewById(R.id.sw_field);

        channel = findViewById(R.id.btn_show_channel);
        developer = findViewById(R.id.btn_show_developer);
        service = findViewById(R.id.btn_show_service_api);

        add.setOnClickListener(this);
        reduce.setOnClickListener(this);
        reset.setOnClickListener(this);
        channel.setOnClickListener(this);
        developer.setOnClickListener(this);
        service.setOnClickListener(this);

        switchException.setOnCheckedChangeListener(this);
        switchField.setOnCheckedChangeListener(this);

        switchList.add(switchException);
        switchList.add(switchField);

    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
    }

    private void addAction() {
        int value = Integer.parseInt(textView.getText().toString());
        value++;
        textView.setText(String.valueOf(value));
        settingsTest();
    }

    private void reduceAction() { // 单步进入会进入到该方法中
        int value = Integer.parseInt(textView.getText().toString());
        value--;
        textView.setText(String.valueOf(value));
        settingsTest();
    }

    private void resetAction() {
        textView.setText(String.valueOf(0));
        settingsTest();
    }

    private void settingsTest() { // 代码执行到此处时，如果在settings选中了Show Values Inline，每一行代码会显示相关的变量值
        int i = 0;
        i = Math.min(i, 100);
        int a = i + 10;
        System.out.println(a); // 代码执行到此处时，如果在settings选中了Auto-Variables mode，就不会再显示变量i
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            System.out.println(Math.addExact(4, 7));
        } // 代码执行到此处时，如果在settings选中了Show Method Return Values，就会显示这个方法最后一行执行代码的返回值
    }

    private void showChannel() {
        try {
            ApplicationInfo info = getPackageManager().getApplicationInfo(getPackageName(), PackageManager.GET_META_DATA);
            Bundle bundle = info.metaData;
            String channel = bundle.getString("CHANNEL");
            Toast.makeText(this, channel, Toast.LENGTH_SHORT).show();
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void showDeveloper() {
        try {
            ActivityInfo info = getPackageManager().getActivityInfo(getComponentName(), PackageManager.GET_META_DATA);
            Bundle bundle = info.metaData;
            String developer = bundle.getString("DEVELOPER");
            Toast.makeText(this, developer, Toast.LENGTH_SHORT).show();
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void showGlobal() {
        TestUtil.show();
    }

    private void showService() {
        Toast.makeText(this, BuildConfig.service_api, Toast.LENGTH_SHORT).show();
        showGlobal(); // 不小心跳过了该方法，中断执行
    }

    private void useModule() {
        Library1Utils.showModuleName();
        Library2Utils.showModuleName();
        Library3Utils.showModuleName();
    }

    // 断点测试方法
    private void breakpointTest() {
        List<String> list = new ArrayList<>(4);
        list.add("我是1号");
        list.add("我是2号");
        list.add("我是3号");
        list.add("我是4号");

        for (String s : list) {
            // 条件断点测试：先打断点，选中suspend表示需要停住，点击More扩展对话框，在Condition填入表达式
            // 日志断点测试：先打断点，取消选中suspend，点击More扩展对话框，在Evaluate and log填入表达式
            int i = 0;
//            Log.d("ZTF", "breakpointTest: " + s);
        }


        if (enableException) {
            try {
                // 异常断点测试：在断点管理页面添加Java Exception Breakpoint，设置所关心的异常为IndexOutOfBoundsException
                System.out.println(list.get(5)); // 报IndexOutOfBoundsException越界异常
            } catch (IndexOutOfBoundsException e) {
                e.printStackTrace();
            }

        }

        if (enableField) {
            // 属性断点测试
            fieldBreakPoint = 2;
        }
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.btn_add:
                addAction(); // 单步跳过演示，会直接到下一行代码，不会进入方法中
                break;
            case R.id.btn_reduce:
                reduceAction(); // 单步进入演示，会进入方法内
                break;
            case R.id.btn_reset:
                resetAction(); // 单步强制进入演示， 会进入jdk方法中，普通单步进入没有办法
                break;
            case R.id.btn_show_channel:
                showChannel(); // 单步跳出演示，会跳出方法
                break;
            case R.id.btn_show_developer:
                showDeveloper(); // 执行到光标处演示
                break;
            case R.id.btn_show_service_api:
                showService(); // 中断执行演示，会回归调用方法之前
                break;
        }
        managerArea();
        breakpointTest();
    }

    private void managerArea() {
        int j = 0; // Mute Breakpoints演示区，暂时禁用断点
        j++;
        j--; // Mute Breakpoints演示区，暂时禁用断点
        j = 3 + 7; // Pause Program | Resume Program演示区，没有暂停的时候跳到下一个断点，有暂停的时候继续执行

        for (int i = 0; i < 2000000; i++) {
            j = i;
        }

        int k = 1 + 3; // 光标处|求值表达式演示|Resume Program演示区
    }

    private void setAllSwitchOff() {
        for (SwitchCompat s : switchList){
            s.setChecked(false);
        }
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        int id = buttonView.getId();
        switch (id) {
            case R.id.sw_exception:
                if (isChecked) {
                    Toast.makeText(this, "点击reset会报异常", Toast.LENGTH_SHORT).show();
                }
                setAllSwitchOff();
                buttonView.setChecked(isChecked);
                enableException = isChecked;
                break;
            case R.id.sw_field:
                setAllSwitchOff();
                buttonView.setChecked(isChecked);
                enableField = isChecked;
                break;
        }
    }
}