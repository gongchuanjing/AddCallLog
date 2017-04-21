package tk.chuanjing.addcalllog.activity;

import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.airsaid.pickerviewlibrary.TimePickerView;

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
        TimePickerView mTimePickerView = new TimePickerView(this, TimePickerView.Type.ALL);
        // 设置是否循环
        mTimePickerView.setCyclic(true);
        // 设置滚轮文字大小
        // mTimePickerView.setTextSize(TimePickerView.TextSize.SMALL);
        // 设置时间可选范围(结合 setTime 方法使用,必须在)
        // Calendar calendar = Calendar.getInstance();
        // mTimePickerView.setRange(calendar.get(Calendar.YEAR) - 100, calendar.get(Calendar.YEAR));
        // 设置选中时间
        // mTimePickerView.setTime(new Date());
        mTimePickerView.setOnTimeSelectListener(new TimePickerView.OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date) {
                callLogDate = date.getTime();
                tv_start_time.setText(DateAndTimeUtils.getTimeForDate("yyyy-MM-dd HH:mm", date));
            }
        });
        mTimePickerView.show();
    }
}
