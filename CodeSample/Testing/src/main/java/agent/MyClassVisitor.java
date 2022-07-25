package agent;

import agent.util.MultiColumnPrinter;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.List;

/**
 * packageName    : agent
 * fileName       : MyClassVisitor
 * author         : lucas
 * date           : 2022-07-21
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022-07-21        lucas       최초 생성
 */
public class MyClassVisitor extends ClassVisitor {

    String classname;

    public MyClassVisitor(final ClassVisitor cv) {
        super(Opcodes.ASM9, cv);
    }


    @Override
    public void visit(int version, int access, String name, String signature, String superName, String[] interfaces) {
        super.visit(version, access, name, signature, superName, interfaces);
        this.classname = name;
        System.out.println("LodingClass : " + name);
    }

    @Override
    public MethodVisitor visitMethod(int access, String name, String descriptor, String signature, String[] exceptions) {
        final MethodVisitor methodVisitor = super.visitMethod(access, name, descriptor, signature, exceptions);
//        System.out.println("\t method name: "+name);
        return  methodVisitor == null ? null : new MyMethodVisitor(methodVisitor, classname);
    }

    @Override
    public void visitEnd() {

        File report = new File(System.getProperty("user.dir") + "/statement_coverage_report.txt");
        PrintWriter writer = null;
        try {
            writer = new PrintWriter(report);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
//        System.out.println("Statement coverage information is written to report.txt in the root directory");
        writer.println("Statement Coverage is listed below for each class and package as well as the total:");
        writer.println();

        final PrintWriter finalWriter = writer;
        MultiColumnPrinter printer = new MultiColumnPrinter(5, 10, "*", 0, false) {
            @Override
            public void doPrint(String str) {
                finalWriter.append(str);
//                System.out.print(str);
            }

            @Override
            public void doPrintln(String str) {
                finalWriter.append("\n");
//                System.out.println();
            }
        };



        String titleRow[] = new String[5];
        titleRow[0] = "Package Name";
        titleRow[1] = "Class Name";
        titleRow[2] = "Executed Statements";
        titleRow[3]="Total statements";
        titleRow[4] = "Statement Coverage";
        printer.addTitle(titleRow);


        List<CodeCoverage> results = CodeCoverageAPI.getAllCodeCoverage();



        for(CodeCoverage codeCoverage:results){
            String row[] = new String[5];


            row[0] = codeCoverage.getPackageName();
            row[1] = codeCoverage.getClassName();
            row[2] = String.valueOf(codeCoverage.getExecutedLines());
            row[3]= String.valueOf(codeCoverage.getTotalLines());
            row[4] = codeCoverage.getCodeCoverage() + " %";
            printer.add(row);
        }

        printer.print();
        CodeCoverage totalCodeCoverage = CodeCoverageAPI.getTotalCoverage();

        writer.append("\nTotal number of Lines Executed: ").append( String.valueOf(totalCodeCoverage.getExecutedLines()));
        writer.append("\n\nTotal number of Lines: ").append( String.valueOf(totalCodeCoverage.getTotalLines()));
        writer.append("\n\nTotal Code Coverage: ").append( String.valueOf(totalCodeCoverage.getCodeCoverage())).append(" %");


//        System.out.println("Total number of Lines Executed in the Project: " + totalCodeCoverage.getExecutedLines());
//        System.out.println("Total number of Lines in the Project: " + totalCodeCoverage.getTotalLines());
//        System.out.println("Total Statement Coverage of the Project: " + totalCodeCoverage.getCodeCoverage() + " %");

        writer.close();
//        System.out.println("****************************************************************************************************************************************************************");
        super.visitEnd();
    }
}