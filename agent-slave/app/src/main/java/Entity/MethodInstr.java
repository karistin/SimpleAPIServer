package Entity;

public class MethodInstr {

    private String methodName;
    private Long callTime = 0L;
    private Long callCount = 1L;
    private Long cultivTime = 0L;

    private String packageName;
    private String className;

    public String getMethodName() {
        return methodName;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public Long getCallTime() {
        return callTime;
    }

    public void setCallTime(Long callTime) {
        this.callTime = callTime;
    }

    public Long getCallCount() {
        return callCount;
    }

    public void setCallCount(Long callCount) {
        this.callCount = callCount;
    }

    public Long getCultivTime() {
        return cultivTime;
    }

    public void setCultivTime(Long cultivTime) {
        this.cultivTime = cultivTime;
    }
    public void plusCallCount(){
        this.callCount +=1;
    }
    public void reset(){
        this.methodName = "";
        this.callCount = 0L;
        this.callTime = 0L;
        this.cultivTime = 0L;
    }

    @Override
    public String toString() {
        return "MethodInstr{" +
                "methodName='" + methodName + '\'' +
                ", callTime=" + callTime +
                ", callCount=" + callCount +
                ", cultivTime=" + cultivTime +
                '}';
    }


}
