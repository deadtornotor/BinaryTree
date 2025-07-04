package cc.deadtornotor.binarytree;

public class Main {
    public static void main(String[] args) {
        BinaryTree tree = new BinaryTree();

        for (int i = 0; i < 20; ++i) {
            tree.insert((int) (Math.random() * 200));
        }

        tree.inOrder();
        tree.printTree();

        tree.balance();
        tree.printTree();
    }
}
