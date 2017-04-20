package tk.chuanjing.addcalllog.core;

import android.content.ContentValues;
import android.content.Context;
import android.provider.CallLog;

import tk.chuanjing.addcalllog.bean.CallLogBean;

/**
 * author ChuanJing
 * date 2017/4/21 1:08
 * version 1.0
 */
public class CoreUtils {

    /**
     * 往数据库中新增通话记录
       呼叫记录有三种类型：
            来电：CallLog.Calls.INCOMING_TYPE (常量值：1)
            已拨：CallLog.Calls.OUTGOING_TYPE (常量值：2)
            未接：CallLog.Calls.MISSED_TYPE (常量值：3)
     */
    public static void insertCallLog(Context mContext, CallLogBean calllog) {
        ContentValues values = new ContentValues();
        values.clear();
        values.put(CallLog.Calls.CACHED_NAME, calllog.getName());
        values.put(CallLog.Calls.NUMBER, calllog.getNumber());
        values.put(CallLog.Calls.TYPE, calllog.getCallLogType());
        values.put(CallLog.Calls.DATE, calllog.getCallLogDate());
        values.put(CallLog.Calls.DURATION, calllog.getCallLogDuration());
        values.put(CallLog.Calls.NEW, "0");// 0已看1未看 ,由于没有获取默认全为已读
        mContext.getContentResolver().insert(CallLog.Calls.CONTENT_URI, values);
    }

    /*
    /**
     * 批量操作
     *
     * 为了使批量更新、插入、删除数据更加方便，Android系统引入了 ContentProviderOperation类，使用ContentProviderOperation的理由
        1.所有的操作都在一个事务中执行，这样可以保证数据完整性
        2.由于批量操作在一个事务中执行，只需要打开和关闭一个事务，比多次打开关闭多个事务性能要好些
        3.使用批量操作和多次单个操作相比，减少了应用和content provider之间的上下文切换，这样也会提升应用的性能，并且减少占用CPU的时间，当然也会减少电量的消耗。
     * 传入list遍历所有的list后，添加到ContentProviderOperation最后才调用applyBatch效率会高很多。

    public static void insertCallLogs(List<CallLogBean> list)
            throws RemoteException, OperationApplicationException {
        GlobalConstants.PrintLog_D("[GlobalVariables->]BatchAddCallLogs begin");
        ArrayList<ContentProviderOperation> ops = new ArrayList<ContentProviderOperation>();
        ContentValues values = new ContentValues();

        for (CallLogBean calllog : list) {
            values.clear();
            values.put(CallLog.Calls.CACHED_NAME, calllog.getName());
            values.put(CallLog.Calls.NUMBER, calllog.getNumber());
            values.put(CallLog.Calls.TYPE, calllog.getCallLogType());
            values.put(CallLog.Calls.DATE, calllog.getCallLogDate());
            values.put(CallLog.Calls.DURATION, calllog.getCallLogDuration());
            values.put(CallLog.Calls.NEW, "0");// 0已看1未看 ,由于没有获取默认全为已读
            ops.add(ContentProviderOperation
                    .newInsert(CallLog.Calls.CONTENT_URI).withValues(values)
                    .withYieldAllowed(true).build());
        }
        if (ops != null) {
            // 真正添加
            ContentProviderResult[] results = mContext.getContentResolver()
                    .applyBatch(CallLog.AUTHORITY, ops);

            // for (ContentProviderResult result : results) {
            // GlobalConstants
            // .PrintLog_D("[GlobalVariables->]BatchAddCallLogs "
            // + result.uri.toString());
            // }
        }
    }
    */
}
