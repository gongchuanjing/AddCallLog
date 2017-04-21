package tk.chuanjing.addcalllog.utils;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.provider.Settings.Secure;
import android.util.Log;

import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * @author ChuanJing
 * @date 2015年5月15日
 */
public class APPUtils {
	
//----------------------------------log日志工具----------------------------------------	
	/** 控制log的开关 */
	private static final boolean IS_SHOW_LOG = true;
	public static void logCj(String msg){
		if (IS_SHOW_LOG) {
			Log.i("--ChuanJing--", msg);
		}
	}
	
	public static void logCj(String tag, String msg){
		if (IS_SHOW_LOG) {
			Log.i(tag, msg);
		}
	}
	
	/**
	 * 得到版本名称的方法
	 */
	public static String getVersionName(Context mContext) {
		// 拿到一个包的管理类，有了这个管理类能够方便我们进行应用包的信息获取
		PackageManager pm = mContext.getPackageManager();
		String versionName = null;
		try {
			// 填0代表获取包上的所有信息
			PackageInfo info = pm.getPackageInfo(mContext.getPackageName(), 0);

			versionName = info.versionName;	// 拿到应用版本名称
		} catch (NameNotFoundException e) {
			e.printStackTrace();
		}
		return versionName;
	}
	
	/**
	 * 得到版本号的方法
	 */
	public static int getVersionCode(Context mContext) {
		PackageManager pm = mContext.getPackageManager();
		int versionCode = 0;
		try {
			PackageInfo info = pm.getPackageInfo(mContext.getPackageName(), 0);
			versionCode = info.versionCode;
		} catch (NameNotFoundException e) {
			e.printStackTrace();
		}
		return versionCode;
	}
	
	/**
	 * 获取设备id
	 */
	public String getDeviceId(Context mContext) {
		return Secure.getString(mContext.getContentResolver(), Secure.ANDROID_ID);
	}
	
	/**
	 * 联网检查更新APP的工具类
	 * @param path 网络请求地址
	 * @param mContext
	 * @return 如果有更新可下载，返回下载地址
	 * @throws Exception 可能抛出的异常有（可以统一处理为：请检查网络，或稍后再试）：
	 * 			1.MalformedURLException
	 * 			2.IOException
	 * 			3.JSONException
	 * 			4.IllegalStateException
	 * 		如果调试异常 直接打开注释即可
	 */
	public static String checkUpdate(String path, Context mContext) throws Exception{
//		try {
			URL url = new URL(path);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setConnectTimeout(5000);	// 连接时长
			conn.setReadTimeout(3000);		// 读取时长
			if (conn.getResponseCode() == 200) { // 联网成功
				InputStream is = conn.getInputStream();
				String content = getStringFromStream(is);	// 将流转化成字符串,调用工具类
				
				// 解析JSON,获取到相应的版本号，描述信息，APK下载路径
				JSONObject jsonObj = new JSONObject(content);
				
				// 通过键值对直接获取内容，不同的项目这里 键名 不一样，要改 TODO
				int newCode = jsonObj.getInt("version");
//				String info = jsonObj.getString("info");
				String apkDownloadUrl = jsonObj.getString("url");
				if (getVersionCode(mContext) < newCode) {
					// 和本地的code版本比较，不是最新本，返回最新版的下载地址
					return apkDownloadUrl;
				} else { 
					return null;	// 不需要更新
				}
			} else { // 联网失败code !=200
				throw new IllegalStateException("联网失败，返回码不是200！");
//				logCj("-------------返回码不是200");
			}
//		} catch (MalformedURLException e) {
//			e.printStackTrace();
//			logCj("-------------MalformedURLException");
//		} catch (IOException e) {
//			e.printStackTrace();
//			logCj("-------------IOException");
//		} catch (JSONException e) {
//			e.printStackTrace();
//			logCj("-------------JSONException");
//		}
//		return null;
	}
	
	/**
	 * 把字节输入流里面的东西转换成字符串
	 * @param is 字节输入流
	 * @return content 字符串
	 */
	public static String getStringFromStream(InputStream is){
		String content = null;
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		
		byte[] bt = new byte[1024];
		int len = 0;
		
		try {
			while ( (len=is.read(bt)) != -1 ) {
				baos.write(bt, 0, len);
			}
			content = baos.toString();	//在这可以传入一个编码格式，从而改变返回字符串的编码格式
		
			baos.close();//关不关都行，因为写到内存中，和硬盘不相干
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return content;
	}
}
