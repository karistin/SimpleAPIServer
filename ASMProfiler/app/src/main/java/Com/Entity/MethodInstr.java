package Com.Entity;

/**
 * The type Method instr.
 */
public class MethodInstr {

    private String methodName;
    private Long microSecond = 0L;
    private Long lastTime = 0l;
    private Long nanotime =0L;
    private Long cputime =0L;
    private Long calls = 1L;
    private Long totalTime = 0L;


    private Long totalNanoTime =0L;

    /**
     * Gets total nano time.
     *
     * @return the total nano time
     */
    public Long getTotalNanoTime() {
        return totalNanoTime;
    }

    /**
     * Sets total nano time.
     *
     * @param totalNanoTime the total nano time
     */
    public void setTotalNanoTime(Long totalNanoTime) {
        this.totalNanoTime = totalNanoTime;
    }

    private  StackTraceElement[] stacks;

    private String packageName;
    private String className;

    /**
     * Gets last time.
     *
     * @return the last time
     */
    public Long getLastTime() {
        return lastTime;
    }

    /**
     * Sets last time.
     *
     * @param lastTime the last time
     */
    public void setLastTime(Long lastTime) {
        this.lastTime = lastTime;
    }

    /**
     * Get stacks stack trace element [ ].
     *
     * @return the stack trace element [ ]
     */
    public StackTraceElement[] getStacks() {
        return stacks;
    }

    /**
     * Gets nanotime.
     *
     * @return the nanotime
     */
    public Long getNanotime() {
        return nanotime;
    }

    /**
     * Sets nanotime.
     *
     * @param nanotime the nanotime
     */
    public void setNanotime(Long nanotime) {
        this.nanotime = nanotime;
    }

    /**
     * Gets cputime.
     *
     * @return the cputime
     */
    public Long getCputime() {
        return cputime;
    }

    /**
     * Sets cputime.
     *
     * @param cputime the cputime
     */
    public void setCputime(Long cputime) {
        this.cputime = cputime;
    }

    /**
     * Sets stacks.
     *
     * @param stacks the stacks
     */
    public void setStacks(StackTraceElement[] stacks) {
        this.stacks = stacks;
    }

    /**
     * Gets method name.
     *
     * @return the method name
     */
    public String getMethodName() {
        return methodName;
    }

    /**
     * Gets package name.
     *
     * @return the package name
     */
    public String getPackageName() {
        return packageName;
    }

    /**
     * Sets package name.
     *
     * @param packageName the package name
     */
    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    /**
     * Gets class name.
     *
     * @return the class name
     */
    public String getClassName() {
        return className;
    }

    /**
     * Sets class name.
     *
     * @param className the class name
     */
    public void setClassName(String className) {
        this.className = className;
    }

    /**
     * Sets method name.
     *
     * @param methodName the method name
     */
    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    /**
     * Gets second.
     *
     * @return the second
     */
    public Long getSecond() {
        return microSecond;
    }

    /**
     * Sets second.
     *
     * @param second the second
     */
    public void setSecond(Long second) {
        this.microSecond = second;
    }

    /**
     * Gets calls.
     *
     * @return the calls
     */
    public Long getCalls() {
        return calls;
    }

    /**
     * Sets calls.
     *
     * @param calls the calls
     */
    public void setCalls(Long calls) {
        this.calls = calls;
    }

    /**
     * Gets total time.
     *
     * @return the total time
     */
    public Long getTotalTime() {
        return totalTime;
    }

    /**
     * Sets total time.
     *
     * @param totalTime the total time
     */
    public void setTotalTime(Long totalTime) {
        this.totalTime = totalTime;
    }

    /**
     * Plus calls.
     */
    public void plusCalls(){
        this.calls +=1;
    }





}
