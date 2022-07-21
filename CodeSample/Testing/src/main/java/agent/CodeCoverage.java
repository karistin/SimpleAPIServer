package agent;

/**
 * packageName    : agent
 * fileName       : CodeCoverage
 * author         : lucas
 * date           : 2022-07-21
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022-07-21        lucas       최초 생성
 */
public class CodeCoverage {
    private int totalLines;
    private int executedLines;
    private String className;
    private String packageName;
    private String codeCoverage;

    public CodeCoverage(int totalLines, int executedLines, String packageNameWithClassName) {
        this.totalLines = totalLines;
        this.executedLines = executedLines;
        double decimalCoverage=(double) executedLines/totalLines * 100;
        this.codeCoverage=String.format("%.2f",decimalCoverage);
        if(packageNameWithClassName.contains("/"))
        {
            this.className = packageNameWithClassName.substring(packageNameWithClassName.lastIndexOf("/") +1);
            this.packageName = packageNameWithClassName.substring(0, packageNameWithClassName.lastIndexOf("/"));
        }
        else{
            this.className=packageNameWithClassName;
            this.packageName="[defaultpackage]";
        }


    }

    public String getPackageName() {
        return packageName;
    }

    public int getTotalLines() {
        return totalLines;
    }

    public int getExecutedLines() {
        return executedLines;
    }

    public String getClassName() {
        return className;
    }

    public String getCodeCoverage() {
        return codeCoverage;
    }

    @Override
    public String toString() {
        return "CodeCoverage{" +
                "totalLines=" + totalLines +
                ", executedLines=" + executedLines +
                ", className='" + className + '\'' +
                ", packageName='" + packageName + '\'' +
                ", codeCoverage='" + codeCoverage + '\'' +
                '}';
    }
}
