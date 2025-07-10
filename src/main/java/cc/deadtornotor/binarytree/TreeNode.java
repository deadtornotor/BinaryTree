package cc.deadtornotor.binarytree;

public class TreeNode<T> {
    T value;
    TreeNode<T> left;
    TreeNode<T> right;

    public TreeNode(T value) {
        this.value = value;
        left = null;
        right = null;
    }
}
