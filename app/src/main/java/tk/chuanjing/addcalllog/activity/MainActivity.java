package tk.chuanjing.addcalllog.activity;

import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.bigkoo.pickerview.TimePickerView;

import java.util.Date;

import tk.chuanjing.addcalllog.R;
import tk.chuanjing.addcalllog.bean.CallLogBean;
import tk.chuanjing.addcalllog.core.CoreUtils;
import tk.chuanjing.addcalllog.utils.APPUtils;
import tk.chuanjing.addcalllog.utils.DateAndTimeUtils;

public class MainActivity extends BaseActivity {

    private TextView tv_start_time;
    private TextView tv_type;
    private Button btn_start_time;
    private Button btn_type;
    private Button btn_ok;
    private EditText et_phone;
    private EditText et_duration;

    private String phoneNumber;//电话号码
    private long callLogDate = new Date().getTime();// 通话记录日期
    private String name = ""; // 在通讯录中的联系人
    private int callLogType = 2; // 通话记录的状态 1:来电 2:去电 3:未接
    private int callLogDuration = 50; // 通话记录时长，单位：秒，不是毫秒

    @Override
    public int getLayoutResID() {
        return R.layout.activity_main;
    }

    @Override
    public void initView() {
        tv_start_time = (TextView) findViewById(R.id.tv_start_time);
        tv_type = (TextView) findViewById(R.id.tv_type);
        btn_start_time = (Button) findViewById(R.id.btn_start_time);
        btn_type = (Button) findViewById(R.id.btn_type);
        btn_ok = (Button) findViewById(R.id.btn_ok);
        et_phone = (EditText) findViewById(R.id.et_phone);
        et_duration = (EditText) findViewById(R.id.et_duration);
    }

    @Override
    public void initListener() {
        btn_start_time.setOnClickListener(this);
        btn_type.setOnClickListener(this);
        btn_ok.setOnClickListener(this);
    }

    @Override
    public void initData() {
        String path = "https://github.com/gongchuanjing/AddCallLog/blob/master/version.json";
        try {
            String apkDownloadUrl = APPUtils.checkUpdate(path, getApplicationContext());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onInnerClick(View v) {
        super.onInnerClick(v);
        switch (v.getId()) {
            case R.id.btn_start_time:
                selectStartTime();
                break;

            case R.id.btn_type:
                selectStartType();
                break;

            case R.id.btn_ok:
                addCallLog();
                break;
        }
    }

    /**
     * 添加通话记录
     */
    private void addCallLog() {
        phoneNumber = et_phone.getText().toString().trim();
        if (TextUtils.isEmpty(phoneNumber)) {
            Toast.makeText(getApplicationContext(), "请输入电话号码", Toast.LENGTH_SHORT).show();
            return;
        }

        String duration = et_duration.getText().toString().trim();
        if(TextUtils.isEmpty(duration)){
            Toast.makeText(getApplicationContext(), "请输入通话记录时长", Toast.LENGTH_SHORT).show();
            return;
        } else {
            callLogDuration = Integer.parseInt(duration) * 60;
        }

        CallLogBean callLog = new CallLogBean(phoneNumber, name, callLogType, callLogDate, callLogDuration);
        CoreUtils.insertCallLog(getApplicationContext(), callLog);
        Toast.makeText(getApplicationContext(), "添加成功，请到通话记录中查看", Toast.LENGTH_SHORT).show();
    }

    /**
     * 选择通话记录的状态 1:来电 2:去电 3:未接
     */
    private void selectStartType() {
        Toast.makeText(getApplicationContext(), "暂不可用", Toast.LENGTH_SHORT).show();
    }

    /**
     * 选择通话开始时间
     */
    private void selectStartTime() {
        TimePickerView pvTime = new TimePickerView.Builder(this, new TimePickerView.OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date,View v) {//选中事件回调
                callLogDate = date.getTime();
                tv_start_time.setText(DateAndTimeUtils.getTimeForDate("yyyy-MM-dd HH:mm", date));
            }
        })
                .setType(TimePickerView.Type.YEAR_MONTH_DAY_HOUR_MIN)
//                .setCancelText("Cancel")//取消按钮文字
//                .setSubmitText("Sure")//确认按钮文字
//                .setContentSize(18)//滚轮文字大小
//                .setTitleSize(20)//标题文字大小
//                .setTitleText("Title")//标题文字
                .setOutSideCancelable(false)//点击屏幕，点在控件外部范围时，是否取消显示
                .isCyclic(true)//是否循环滚动
//                .setTitleColor(Color.BLACK)//标题文字颜色
//                .setSubmitColor(Color.BLUE)//确定按钮文字颜色
//                .setCancelColor(Color.BLUE)//取消按钮文字颜色
//                .setTitleBgColor(0xFF666666)//标题背景颜色 Night mode
//                .setBgColor(0xFF333333)//滚轮背景颜色 Night mode
//                .setRange(calendar.get(Calendar.YEAR) - 20, calendar.get(Calendar.YEAR) + 20)//默认是1900-2100年
//                .setDate(selectedDate)// 如果不设置的话，默认是系统时间*/
//                .setRangDate(startDate,endDate)//起始终止年月日设定
//                .setLabel("年","月","日","时","分","秒")
//                .isCenterLabel(false) //是否只显示中间选中项的label文字，false则每项item全部都带有label。
//                .isDialog(true)//是否显示为对话框样式
                .build();
        pvTime.show();
    }
}
