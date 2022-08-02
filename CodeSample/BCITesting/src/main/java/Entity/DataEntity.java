package Entity;

/**
 * packageName    : Entity
 * fileName       : DataEntity
 * author         : lucas
 * date           : 2022-07-25
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022-07-25        lucas       최초 생성
 */
public class DataEntity {
    private String packageName;
    private String className;
    private String methodName;
    private int totalCount;
    private int accumlateTime;

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

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public int getAccumlateTime() {
        return accumlateTime;
    }

    public void setAccumlateTime(int accumlateTime) {
        this.accumlateTime = accumlateTime;
    }

    @Override
    public String toString() {
        return "dataset{" +
                "packageName " + packageName + "\r\n"+
                " className " + className + "\r\n" +
                " methodName '" + methodName + "\r\n" +
                " totalCount" + totalCount + "\r\n" +
                " accumlateTime" + accumlateTime + "\r\n" +
                '}';
    }
}
