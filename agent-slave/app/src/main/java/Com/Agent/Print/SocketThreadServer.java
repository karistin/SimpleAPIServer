package Com.Agent.Print;

import Com.Agent.MyBCIMethod;
import Com.Entity.MethodInstr;
import com.google.gson.Gson;
import org.json.JSONObject;
import org.json.JSONString;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.List;
import java.util.Map;

import static Com.Agent.App.LOG;

public class SocketThreadServer extends Thread{

    private Socket socket;
    public SocketThreadServer(Socket socket){
        this.socket = socket;
    }

    @Override
    public void run() {
        BufferedReader br = null;
        PrintWriter pw = null;
        String connIp = socket.getInetAddress().getHostAddress();
        System.out.println(connIp + " connecting ");

        try {
            while(true)
            {
                br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                pw = new PrintWriter(socket.getOutputStream());

                JSONObject jsonObject = new JSONObject();
                Map<String, MethodInstr> methosInstrList = MyBCIMethod.getMethodInstrList();


                jsonObject.put("data",methosInstrList);

                pw.println(new Gson().toJson(jsonObject));
                pw.flush();

            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }
}
