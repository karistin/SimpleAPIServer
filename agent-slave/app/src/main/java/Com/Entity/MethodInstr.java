package Com.Entity;

public class MethodInstr {

    private String methodName;
    private Long microSecond = 0L;
    private Long lastTime = 0l;
    private Long nanotime =0L;
    private Long cputime =0L;
    private Long calls = 1L;
    private Long totalTime = 0L;
    private Long totalNanoTime =0L;

    public Long getTotalNanoTime() {
        return totalNanoTime;
    }

    public void setTotalNanoTime(Long totalNanoTime) {
        this.totalNanoTime = totalNanoTime;
    }

    private  StackTraceElement[] stacks;

    private String packageName;
    private String className;

    public Long getLastTime() {
        return lastTime;
    }

    public void setLastTime(Long lastTime) {
        this.lastTime = lastTime;
    }

    public StackTraceElement[] getStacks() {
        return stacks;
    }

    public Long getNanotime() {
        return nanotime;
    }

    public void setNanotime(Long nanotime) {
        this.nanotime = nanotime;
    }

    public Long getCputime() {
        return cputime;
    }

    public void setCputime(Long cputime) {
        this.cputime = cputime;
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
        return microSecond;
    }

    public void setSecond(Long second) {
        this.microSecond = second;
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
