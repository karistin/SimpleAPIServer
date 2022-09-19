package Application;

import java.io.PrintStream;

/**
 * packageName    : Application
 * fileName       : BinaryTreeModel
 * author         : lucas
 * date           : 2022-08-19
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022-08-19        lucas       최초 생성
 */
public class BinaryTreeModel {
    private Object value;
    private BinaryTreeModel left;
    private BinaryTreeModel right;

    public BinaryTreeModel(Object value) {
        this.value = value;
    }

    public BinaryTreeModel getLeft() {
        return left;
    }

    public void setLeft(BinaryTreeModel left) {
        this.left = left;
    }

    public BinaryTreeModel getRight() {
        return right;
    }

    public void setRight(BinaryTreeModel right) {
        this.right = right;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }
    public void traversePreOrder(StringBuilder sb, BinaryTreeModel node) {
        if (node != null) {
            sb.append(node.getValue());
            sb.append("\n");
            traversePreOrder(sb, node.getLeft());
            traversePreOrder(sb, node.getRight());
        }
    }
    public void print(PrintStream os) {
        StringBuilder sb = new StringBuilder();
        traversePreOrder(sb, this.right);
        os.print(sb.toString());
    }
}
