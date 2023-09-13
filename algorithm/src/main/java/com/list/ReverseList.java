package com.list;

public class ReverseList {
    public static void main(String[] args) {
        Node head = constructList();
        printList(head);

        // 翻转链表
//        Node newHead = reverseList(head);
//        printList(newHead);

        Node newHead = reverseListRecursion(head);
        printList(newHead);
    }

    public static Node constructList() {
        Node node5 = new Node(5, null);
        Node node4 = new Node(4, node5);
        Node node3 = new Node(3, node4);
        Node node2 = new Node(2, node3);
        Node node1 = new Node(1, node2);

        return node1;
    }

    public static Node reverseList(Node head) {
        Node currentNode = head;
        Node currentNodePrev = null;
        Node currentNodeNext = null;

        while (null != currentNode) {
            currentNodeNext = currentNode.next;
            currentNode.next = currentNodePrev;
            currentNodePrev = currentNode;

            currentNode = currentNodeNext;
        }

        return currentNodePrev;
    }

    public static Node reverseListRecursion(Node head) {
        if (null == head || null == head.next) {
            return head;
        }

        Node current = head;
        Node currentNodeNext = head.next;

        Node newHead = reverseListRecursion(currentNodeNext);

        current.next.next = current;
        current.next = null;

        return newHead;
    }

    public static void printList(Node head) {
        Node node = head;
        while (null != node) {
            System.out.print(node.value + " ");
            node = node.next;
        }

        System.out.println();
    }

    private static class Node {
        public int value;
        public Node next;

        public Node(int value, Node next) {
            this.value = value;
            this.next = next;
        }
    }
}
