package Application;


import java.util.HashMap;
import java.util.Map;

/**
 * packageName    : Application
 * fileName       : Main
 * author         : lucas
 * date           : 2022-07-25
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022-07-25        lucas       최초 생성
 */
public class Main {

    public static void main(String args[]) throws InterruptedException {
//        BinaryTreeModel root = new BinaryTreeModel("root");
//
//        BinaryTreeModel node1 = new BinaryTreeModel("node1");
//        BinaryTreeModel node2 = new BinaryTreeModel("node2");
//        root.setLeft(node1);
//        root.setRight(node2);
//
//        BinaryTreeModel node3 = new BinaryTreeModel("node3");
//        BinaryTreeModel node4 = new BinaryTreeModel("node4");
//        node1.setLeft(node3);
//        node1.setRight(node4);
//
//        node2.setLeft(new BinaryTreeModel("node5"));
//        node2.setRight(new BinaryTreeModel("node6"));
//
//        BinaryTreeModel node7 = new BinaryTreeModel("node7");
//        node3.setLeft(node7);
//        node7.setLeft(new BinaryTreeModel("node8"));
//        node7.setRight(new BinaryTreeModel("node9"));
//        DefaultNode tree = new DefaultNode("root");
//        tree.addChild(new DefaultNode("child1"));
//        tree.addChild(new DefaultNode("child2"));
//
//        String rendered = TextTree.newInstance().render(tree);
//        System.out.println(rendered);
//        String[] index = {"read","readByte","reading","byteread","READ","ReaD"};
//        String name = "read";
//        for (String ind : index) {
//            if (ind.toLowerCase().contains(name)) {
//                System.out.println(ind);
//            }
//        }
//        String setence = "tree visit";
//        if (setence.startsWith("tree")) {
//            List<String> nameList = List.of(setence.split(" "));
//            System.out.println(nameList.get(nameList.size()-1));
//        }
//    }
// 객체 이름표시
//  함수 매개변수 표시
//    코드의 개선성

//        while(true) {
//            String str = new String("World");
//            String str2 = "Hello";
//            int intger = 2;
//            byte[] bytearray = new byte[10];
//            boolean flag = false;
//            String[] array = new String[5];
//            int result = Test(3,5);
//            for (String arr : array) {
//                arr = "Hello";
//            }
//            str = "";
//            str2 = "";
//            intger = 0;
//            array = null;
//            Map<String, Integer> map = new HashMap();
//            Thread.sleep(1000);
//        }
        String leftAlignFormat = "| %-15s | %-4d |%n";

        System.out.format("+-----------------+------+%n");
        System.out.format("| Column name     | ID   |%n");
        System.out.format("+-----------------+------+%n");
        for (int i = 0; i < 5; i++) {
            System.out.format(leftAlignFormat, "some data" + i, i * i);
        }
        System.out.format("+-----------------+------+%n");
    }

    private static int Test(int a, int b) {
        int c = a + b;
        return c*2;
    }

}
