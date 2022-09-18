package lucas.exporter;


import com.google.protobuf.ByteString;

import java.util.List;
import java.util.Random;

public class Testing {
    public static void main(String[] args) {


        Random r = new Random();
        System.out.println(ByteString.copyFromUtf8(String.valueOf(r.nextInt(65536))));
    }
}
