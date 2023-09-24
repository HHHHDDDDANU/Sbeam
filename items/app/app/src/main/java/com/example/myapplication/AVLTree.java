package com.example.myapplication;

public class AVLTree {
    private class AVLNode {
        public String name;
        public int year;
        public String producer;
        public int review;
        public int price;
        public AVLNode left;
        public AVLNode right;
        public int height;

        public AVLNode(String name, int year, String producer, int review, int price) {
            this.name = name;
            this.year = year;
            this.producer = producer;
            this.review = review;
            this.price = price;
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
        AVLNode x = y.left;
        AVLNode T2 = x.right;

        x.right = y;
        y.left = T2;

        updateHeight(y);
        updateHeight(x);

        return x;
    }

    private AVLNode rotateLeft(AVLNode x) {
        AVLNode y = x.right;
        AVLNode T2 = y.left;

        y.left = x;
        x.right = T2;

        updateHeight(x);
        updateHeight(y);

        return y;
    }

    public void insert(String name, int year, String producer, int review, int price) {
        root = insert(root, name, year, producer, review, price);
    }

    private AVLNode insert(AVLNode node, String name, int year, String producer, int review, int price) {
        if (node == null)
            return new AVLNode(name, year, producer, review, price);

        if (price < node.price)
            node.left = insert(node.left, name, year, producer, review, price);
        else
            node.right = insert(node.right, name, year, producer, review, price);

        updateHeight(node);

        int balance = getBalance(node);

        if (balance > 1 && price < node.left.price)
            return rotateRight(node);

        if (balance < -1 && price > node.right.price)
            return rotateLeft(node);

        if (balance > 1 && price > node.left.price) {
            node.left = rotateLeft(node.left);
            return rotateRight(node);
        }

        if (balance < -1 && price < node.right.price) {
            node.right = rotateRight(node.right);
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
        System.out.println("Name: " + node.name + ", Year: " + node.year + ", Producer: " + node.producer
                + ", Review: " + node.review + ", Price: " + node.price);
        inorder(node.right);
    }

    public AVLNode searchByName(String targetName) {
        return searchByName(root, targetName);
    }

    private AVLNode searchByName(AVLNode node, String targetName) {
        if (node == null || node.name.equals(targetName))
            return node;

        if (targetName.compareTo(node.name) < 0)
            return searchByName(node.left, targetName);
        else
            return searchByName(node.right, targetName);
    }

    public static void main(String[] args) {
        AVLTree tree = new AVLTree();
        tree.insert("Mass Effect: Legendary Edition", 2021, "BioWare", 90, 60);
        tree.insert("The Witcher 3: Wild Hunt", 2015, "CD Projekt Red", 95, 40);
        tree.insert("Red Dead Redemption 2", 2018, "Rockstar Games", 97, 50);

        System.out.println("Inorder traversal:");
        tree.inorder();

        String targetName = "The Witcher 3: Wild Hunt";
        AVLNode result = tree.searchByName(targetName);
        if (result != null) {
            System.out.println("Found: Name: " + result.name + ", Year: " + result.year + ", Producer: " + result.producer
                    + ", Review: " + result.review + ", Price: " + result.price);
        } else {
            System.out.println("Not found: " + targetName);
        }
    }
}