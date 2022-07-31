package Entity;

public class MethodInstr {
    private String methodName;
    private Long callTime = 1L;
    private Long callCount = 0L;
    private Long cultivTime = 0L;


    public String getMethodName() {
        return methodName;
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
}
