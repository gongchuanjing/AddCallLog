package tk.chuanjing.addcalllog;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.Date;

import tk.chuanjing.addcalllog.bean.CallLogBean;
import tk.chuanjing.addcalllog.core.CoreUtils;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        CallLogBean callLog = new CallLogBean("18888888888", "", 1, new Date().getTime(), 1000);
        CoreUtils.insertCallLog(getApplicationContext(), callLog);
    }
}
