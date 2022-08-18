package Com.Entity;

public class MethodInstr {

    private String methodName;
    private Long second = 0L;
    private Long calls = 1L;
    private Long totalTime = 0L;
    private  StackTraceElement[] stacks;

    private String packageName;
    private String className;

    public StackTraceElement[] getStacks() {
        return stacks;
    }

    public void setStacks(StackTraceElement[] stacks) {
        this.stacks = stacks;
    }

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

    public Long getSecond() {
        return second;
    }

    public void setSecond(Long second) {
        this.second = second;
    }

    public Long getCalls() {
        return calls;
    }

    public void setCalls(Long calls) {
        this.calls = calls;
    }

    public Long getTotalTime() {
        return totalTime;
    }

    public void setTotalTime(Long totalTime) {
        this.totalTime = totalTime;
    }
    public void plusCalls(){
        this.calls +=1;
    }





}
