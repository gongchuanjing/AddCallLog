package tk.chuanjing.addcalllog.activity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;

/**
 * Activity的基类
 * @author ChuanJing
 */
public abstract class BaseActivity extends AppCompatActivity implements OnClickListener {
	
	@SuppressLint("ResourceAsColor")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(getLayoutResID());
		
		// 设置状态栏为蓝色
//		StatusBarUtils.setStatusBarColor(this, R.color.title_background);
		// 设置状态栏白色字体图标，适配4.4以上版本MIUIV、Flyme和6.0以上版本其他Android
//		StatusBarUtils.StatusBarDarkMode(this);
		
		initView();
		
		// 换字体，所有继承此activity的页面全部换字体了
//        TypefaceHelper.typeface(this);
		
		initListener();
		
		initData();
		
		registerCommonListener();
	}

	/**
     * 返回当前Activity的布局id
     * @return
     */
	public abstract int getLayoutResID();
	
	/**
     * 初始化控件
     * 子类查找控件,必须在initView方法中
     */
	public abstract void initView();
	
	/**
     * 初始化监听
     * 子类所有的监听设置,必须在initListener方法中
     */
	public abstract void initListener();
	
	/**
     * 初始化数据
     * 子类所有的初始化数据操作,必须在initData方法中
     */
	public abstract void initData();
	
	/**
     * 注册相同监听
     */
	private void registerCommonListener() {
		//给返回按钮设置监听
//		View back = findViewById(R.id.back);
//		if (back != null) {		//并不是所有的界面都有返回按钮
//			back.setOnClickListener(this);
//		}
		
//		View home = findViewById(R.id.home);
//		if (home != null) {
//			home.setOnClickListener(this);
//		}
	}
	
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
//		case R.id.back:			//返回按钮
//			finish();
//			break;
//			
//		case R.id.home:			//返回主页
//			startActivity(new Intent(LiveApplication.getInstance(), MainActivity.class));
//			finish();
//			break;

		default:
			onInnerClick(v);
			break;
		}
	}

	/**
     * 处理除了返回按钮的点击回调
     * 子类所有的控件点击回调,必须在这里处理
     * @param v
     */
	public void onInnerClick(View v) {
		
	}
}