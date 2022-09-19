package Com.Agent;

import com.sun.tools.attach.*;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

/**
 * packageName    : Com.Agent
 * fileName       : AgentLoader
 * author         : lucas
 * date           : 2022-08-25
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022-08-25        lucas       최초 생성
 */
public class AgentLoader {
//    private static Logger LOGGER = LoggerFactory.getLogger(AgentLoader.class);

    public static void main(String[] args) throws IOException, AttachNotSupportedException, AgentLoadException, AgentInitializationException {
        String agentFilePath = System.getProperty("user.dir")+"/app/build/libs/app.jar";
        File agentFile = new File(agentFilePath);

//        Optional<String> jvmProcessOpt = Optional.ofNullable(VirtualMachine.list()
//                .stream()
//                .filter(jvm -> {
//                    System.out.println("jvm: "+ jvm.displayName());
//                    return jvm.displayName().contains("TestCase.jar");
//                })
//                .findFirst().get().id());


        System.out.println(VirtualMachine.list());
        String JvmPid = null;
        for(VirtualMachineDescriptor virtualMachine: VirtualMachine.list())
        {
            System.out.println(virtualMachine.displayName());
            if (virtualMachine.displayName().equals("TestCase.jar"))
            {
                JvmPid = virtualMachine.id();
            }
        }
        VirtualMachine vm = null;
        if (JvmPid != null) {
            vm = VirtualMachine.attach(JvmPid);
//            vm.loadAgent(System.getProperty("user.dir")+"/app/build/libs/app.jar");
            vm.loadAgent(agentFile.getAbsolutePath());
            vm.detach();
        }else{
            System.out.println("Not find TestCase!");
        }


//        if(!vm.isPresent()) {
//            System.out.println("Target Application not found");
//            return;
//        }
//        File agentFile = new File("./TestCase.jar");
//        try {
//            String jvmPid = jvmProcessOpt.get();
//            System.out.println("Attaching to target JVM with PID: " + jvmPid);
//            VirtualMachine jvm = VirtualMachine.attach(jvmPid);
//            jvm.loadAgent(agentFile.getAbsolutePath());
//            jvm.detach();
//            System.out.println("Attached to target JVM and loaded Java agent successfully");
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
    }
}

