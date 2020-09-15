package ru.academits.balakina.tree;

import java.util.Objects;

public class TreeNode<T> {
    private TreeNode<T> left;   // левый сын
    private TreeNode<T> right;  // правый сын
    private T data;       // полезные данные

    public TreeNode(T data) {
        this.data = data;
        left = null;
        right = null;
    }

    public TreeNode<T> getLeft() {
        return left;
    }

    public TreeNode<T> getRight() {
        return right;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public void setLeft(TreeNode<T> left){
        this.left = left;
    }

    public void setRight(TreeNode<T> right){
        this.right = right;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TreeNode<?> treeNode = (TreeNode<?>) o;
        return data.equals(treeNode.data);
    }

    @Override
    public int hashCode() {
        return Objects.hash(data);
    }

    @Override
    public String toString() {
        return data.toString();
    }
}
