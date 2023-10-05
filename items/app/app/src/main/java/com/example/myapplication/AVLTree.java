package com.example.myapplication;
import java.util.LinkedList;
import java.util.Queue;


public class AVLTree {
    public class AVLNode {
        public Game game;
        public AVLNode left;
        public AVLNode right;
        public int height;

        public AVLNode(Game game) {
            this.game = game;
            this.left = null;
            this.right = null;
            this.height = 1;
        }
    }

    private AVLNode root;

    public AVLTree() {
        root = null;
    }

    private int getHeight(AVLNode node) {
        if (node == null)
            return 0;
        return node.height;
    }

    private int getBalance(AVLNode node) {
        if (node == null)
            return 0;
        return getHeight(node.left) - getHeight(node.right);
    }

    private void updateHeight(AVLNode node) {
        if (node != null) {
            node.height = 1 + Math.max(getHeight(node.left), getHeight(node.right));
        }
    }

    private AVLNode rotateRight(AVLNode y) {

        //System.out.println("balence: " + getBalance(y));
        AVLNode x = y.left;
        AVLNode T2 = x.right;

        x.right = y;
        y.left = T2;

        updateHeight(y);
        updateHeight(x);

        return x;
    }

    private AVLNode rotateLeft(AVLNode x) {

        //System.out.println("balence: " + getBalance(x));
        AVLNode y = x.right;
        AVLNode T2 = y.left;
        //if(y == null) inorder(x);

        y.left = x;
        x.right = T2;

        updateHeight(x);
        updateHeight(y);

        return y;
    }

    public void insert(Game game) {
        root = insert(root, game);
    }

    private AVLNode insert(AVLNode node, Game game) {
        if (node == null)
            return new AVLNode(game);

        if (game.getPrice() < node.game.getPrice())
            node.left = insert(node.left, game);
        else
            node.right = insert(node.right, game);

        updateHeight(node);

        int balance = getBalance(node);

        if (balance > 1 && game.getPrice() < node.left.game.getPrice())
            return rotateRight(node);

        if (balance < -1 && game.getPrice() >= node.right.game.getPrice()){
            if(node.right == null) System.out.println("FFFFFFFFFFFFFFFFFF");
            return rotateLeft(node);
        }

        if (balance > 1 && game.getPrice() >= node.left.game.getPrice()) {
            node.left = rotateLeft(node.left);
            return rotateRight(node);
        }

        if (balance < -1 && game.getPrice() < node.right.game.getPrice()) {
            node.right = rotateRight(node.right);
            if(node.right == null) System.out.println("RRRRRRRRRRRRRRRRRR");
            return rotateLeft(node);
        }
        return node;
    }

    public void inorder() {
        inorder(root);
    }

    private void inorder(AVLNode node) {

        if (node == null)
            return;

        inorder(node.left);
        System.out.println("Name: " + node.game.getName() + ", Year: " + node.game.getYear() + ", Producer: " + node.game.getProducer()
                + ", Review: " + node.game.getReview() + ", Price: " + node.game.getPrice() + "height: " + node.height);
        inorder(node.right);
    }

    public void printTreeStructure() {
        if (root == null) {
            System.out.println("The tree is empty.");
            return;
        }

        Queue<AVLNode> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            for (int i = 0; i < levelSize; i++) {
                AVLNode node = queue.poll();
                System.out.print("price: " + node.game.getPrice() + " balance: " + getBalance(node) + ", ");
                if (node.left != null) {
                    queue.add(node.left);
                }
                if (node.right != null) {
                    queue.add(node.right);
                }
            }
            System.out.println();
        }
    }

    public AVLNode searchByName(String targetName) {
        return searchByName(root, targetName);
    }

    private AVLNode searchByName(AVLNode node, String targetName) {
        if (node == null || node.game.getName().equals(targetName))
            return node;

        if (targetName.compareTo(node.game.getName()) < 0)
            return searchByName(node.left, targetName);
        else
            return searchByName(node.right, targetName);
    }


}