package tk.chuanjing.addcalllog.bean;

/**
 * author ChuanJing
 * date 2017/4/21 1:16
 * version 1.0
 */
public class CallLogBean {

    private String number; // 号码
    private String name; // 在通讯录中的联系人
    private int callLogType; // 通话记录的状态 1:来电 2:去电 3:未接
    private Long callLogDate; // 通话记录日期
    private int callLogDuration; // 通话记录时长，单位：秒，不是毫秒

    public CallLogBean() {

    }

    public CallLogBean(String number, String name, int callLogType, Long callLogDate, int callLogDuration) {
        this.number = number;
        this.name = name;
        this.callLogType = callLogType;
        this.callLogDate = callLogDate;
        this.callLogDuration = callLogDuration;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCallLogType() {
        return callLogType;
    }

    public void setCallLogType(int callLogType) {
        this.callLogType = callLogType;
    }

    public Long getCallLogDate() {
        return callLogDate;
    }

    public void setCallLogDate(Long callLogDate) {
        this.callLogDate = callLogDate;
    }

    public int getCallLogDuration() {
        return callLogDuration;
    }

    public void setCallLogDuration(int callLogDuration) {
        this.callLogDuration = callLogDuration;
    }

    @Override
    public String toString() {
        return "CallLogBean{" +
                "number='" + number + '\'' +
                ", name='" + name + '\'' +
                ", callLogType=" + callLogType +
                ", callLogDate=" + callLogDate +
                ", callLogDuration=" + callLogDuration +
                '}';
    }
}
