package ru.academits.balakina.tree_main;

import ru.academits.balakina.tree.Tree;
import ru.academits.balakina.tree.TreeNode;

public class Main {
    public static void main(String[] args) {
        TreeNode<Integer> root = new TreeNode<>(100);
        Tree<Integer> tree = new Tree<>(root);

        System.out.println("root = " + root);
        System.out.println("tree: " + tree);
        System.out.println("size = " + tree.getSize());

        tree.add(new TreeNode<>(50));
        tree.add(new TreeNode<>(150));
        tree.add(new TreeNode<>(130));
        tree.add(new TreeNode<>(160));
        tree.add(new TreeNode<>(140));
        System.out.println();
        System.out.println("tree: " + tree);
        System.out.println("size = " + tree.getSize());
        System.out.println();

        System.out.println("10 есть в дереве? - " + tree.searchItemByData(10));
        System.out.println("15 есть в дереве? - " + tree.searchItemByData(15));
        System.out.println("5 есть в дереве? - " + tree.searchItemByData(5));

        System.out.println();
        System.out.println("Обход в ширину с печатью узлов");
        tree.iterateInWidthAndPrint();
        System.out.println();
        System.out.println("size = " + tree.getSize());
        System.out.println();

//        System.out.println();
//        System.out.println("Обход в глубину с печатью узлов");
//        tree.iterateInDepthAndPrint();

        System.out.println();
        System.out.println("Удалим корень: " + tree.removeRoot());
        tree.iterateInWidthAndPrint();
        System.out.println();
        System.out.println("size = " + tree.getSize());
        System.out.println();

        System.out.println();
        System.out.println("Удалим узел: " + tree.remove(150));
        tree.iterateInWidthAndPrint();
        System.out.println();
        System.out.println("size = " + tree.getSize());
        System.out.println();
    }
}
