package ru.academits.balakina.tree;

import java.util.LinkedList;
import java.util.Queue;

public class Tree<T extends Comparable<T>> {
    private TreeNode<T> root;
    private int size;

    public Tree(TreeNode<T> root) {
        this.root = root;
        size = 1;
    }

    public int getSize() {
        return size;
    }

    public void add(TreeNode<T> item) {
        TreeNode<T> current = root;

        while (true)
            if (current.getData().compareTo(item.getData()) > 0) {
                if (!(current.getLeft() == null)) {
                    current = current.getLeft();
                } else {
                    current.setLeft(item);
                    size++;
                    break;
                }
            } else {
                if (!(current.getRight() == null)) {
                    current = current.getRight();
                } else {
                    current.setRight(item);
                    size++;
                    break;
                }
            }
    }

    public boolean searchItemByData(T data) {
        TreeNode<T> current = root;

        while (true)
            if (current.getData().compareTo(data) == 0) {
                return true;
            } else {
                if (current.getData().compareTo(data) > 0) {
                    if (!(current.getLeft() == null)) {
                        current = current.getLeft();
                    } else {
                        return false;
                    }
                } else {
                    if (!(current.getRight() == null)) {
                        current = current.getRight();
                    } else {
                        return false;
                    }
                }
            }
    }

    // Обход в ширину с печатью узлов
    public void iterateInWidthAndPrint() {
        if (root == null) {
            return;
        }

        Queue<TreeNode<T>> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            TreeNode<T> currentNode = queue.remove();
            System.out.print(currentNode + " ");

            if (currentNode.getLeft() != null) {
                queue.add(currentNode.getLeft());
            }

            if (currentNode.getRight() != null) {
                queue.add(currentNode.getRight());
            }
        }
    }

    // TODO: Обход в глубину с печатью узлов
    public void iterateInDepthAndPrint() {

    }

    // Удаление корня
    public boolean removeRoot() {
        // Если у корня нет детей
        if (root.getRight() == null && root.getLeft() == null) {
            root.setData(null);
            size = 0;
            return true;
        }

        // Если у корня 1 левый ребенок - он становится новым корнем
        if (root.getLeft() != null && root.getRight() == null) {
            root = root.getLeft();
            size--;
            return true;
        }

        // Если у корня 1 правый ребенок - он становится новым корнем
        if (root.getRight() != null && root.getLeft() == null) {
            root = root.getRight();
            size--;
            return true;
        }

        // Если у корня 2 ребенка
        if (root.getRight() != null && root.getLeft() != null) {
            TreeNode<T> currentNode = root;
            TreeNode<T> minNode = currentNode.getRight();
            TreeNode<T> previousMinNode = currentNode;

            while (minNode.getLeft() != null) {
                TreeNode<T> temp = minNode;
                previousMinNode = minNode;
                minNode = temp.getLeft();
            }

            System.out.println("Найденный Min = " + minNode.getData());
            System.out.println("Previous for Min = " + previousMinNode.getData());

            // если минимальный - это сын удаляемого
            if (minNode.getData().compareTo(currentNode.getRight().getData()) == 0) {
                minNode.setLeft(currentNode.getLeft());
                this.root = minNode;
                size--;
                return true;
            }

            // если у минимального нет правого сына - просто ставим его на место корня
            if (minNode.getRight() == null) {
                previousMinNode.setLeft(null);

                minNode.setLeft(currentNode.getLeft());
                minNode.setRight(currentNode.getRight());
                this.root = minNode;
                size--;
                return true;
            }

            // если у минимального есть правый сын
            if (minNode.getRight() != null) {
                previousMinNode.setLeft(minNode.getRight());

                minNode.setLeft(currentNode.getLeft());
                minNode.setRight(currentNode.getRight());
                this.root = minNode;
                size--;
                return true;
            }
        }

        return false;
    }

    public boolean remove(T data) {
        if (root == null || !searchItemByData(data)) {
            return false;
        }

        if (root.getData().compareTo(data) == 0) {
            removeRoot();
            return true;
        }

        TreeNode<T> currentNode = root;
        TreeNode<T> previousNode = null;
        boolean isNodeFound;
        boolean isLeft = false;
        boolean isRight = false;

        // Нахождение узла и его родителя (previous) и определение является узел левым или правым для своего родителя
        while (true) {
            if (currentNode.getData().compareTo(data) == 0) {
                isNodeFound = true;
                System.out.println("Найденный элемент = " + currentNode.getData());
                System.out.println("Previous = " + previousNode.getData());

                if (previousNode.getLeft().getData().compareTo(data) == 0) {
                    isLeft = true;
                } else if (previousNode.getRight().getData().compareTo(data) == 0) {
                    isRight = true;
                }

                break;
            }

            if (currentNode.getData().compareTo(data) < 0) { // если data > root --> элемент в правом поддереве
                previousNode = currentNode;
                currentNode = currentNode.getRight();
            } else if (currentNode.getData().compareTo(data) > 0) { // если data < root --> элемент в левом поддереве
                previousNode = currentNode;
                currentNode = currentNode.getLeft();
            }
        }

        // Удаление листа
        if (currentNode.getLeft() == null && currentNode.getRight() == null) {
            if (isLeft) {
                System.out.println("Левый " + previousNode.getLeft().getData());
                previousNode.setLeft(null);
            } else if (isRight) {
                System.out.println("Правый " + previousNode.getRight().getData());
                previousNode.setRight(null);
            }
        }

        // Удаление узла с одним ребенком
        if (currentNode.getLeft() == null || currentNode.getRight() == null) {
            if (currentNode.getLeft() != null) {
                if (isLeft) {
                    previousNode.setLeft(currentNode.getLeft());
                } else {
                    previousNode.setRight(currentNode.getLeft());
                }
            } else if (currentNode.getRight() != null) {
                if (isLeft) {
                    previousNode.setLeft(currentNode.getRight());
                } else {
                    previousNode.setRight(currentNode.getRight());
                }
            }
        }

        // удаление узла с двумя детьми
        if (currentNode.getLeft() != null && currentNode.getRight() != null) {
            TreeNode<T> minNode = currentNode.getRight();
            TreeNode<T> previousMinNode = currentNode;

            while (minNode.getLeft() != null) {
                TreeNode<T> temp = minNode;
                previousMinNode = minNode;
                minNode = temp.getLeft();
            }

            System.out.println("Найденный Min = " + minNode.getData());
            System.out.println("Previous for Min = " + previousMinNode.getData());

            // если минимальный - это сын удаляемого
            if (minNode.getData().compareTo(currentNode.getRight().getData()) == 0) {
                minNode.setLeft(currentNode.getLeft());

                if (isRight) {
                    previousNode.setRight(minNode);
                } else {
                    previousNode.setLeft(minNode);
                }

                size--;
                return true;
            }

            // если у минимального нет правого сына - просто ставим его на место удаляемого узла
            if (minNode.getRight() == null) {
                minNode.setRight(currentNode.getRight());
                previousMinNode.setLeft(null);

                if (isRight) {
                    previousNode.setRight(minNode);
                } else {
                    previousNode.setLeft(minNode);
                }

                minNode.setLeft(currentNode.getLeft());
            }

            // если у минимального есть правый сын
            if (minNode.getRight() != null) {
                previousMinNode.setLeft(minNode.getRight());
                minNode.setRight(currentNode.getRight());


                if (isRight) {
                    previousNode.setRight(minNode);
                } else {
                    previousNode.setLeft(minNode);
                }

                minNode.setLeft(currentNode.getLeft());
            }
        }

        size--;
        return true;
    }

    // toString() реализован с помощью обхода в ширину
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        if (root == null) {
            return "";
        }

        Queue<TreeNode<T>> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            TreeNode<T> currentNode = queue.remove();
            sb.append(currentNode).append(", ");

            if (currentNode.getLeft() != null) {
                queue.add(currentNode.getLeft());
            }

            if (currentNode.getRight() != null) {
                queue.add(currentNode.getRight());
            }
        }

        sb.delete(sb.length() - 2, sb.length());
        return sb.toString();
    }
}
