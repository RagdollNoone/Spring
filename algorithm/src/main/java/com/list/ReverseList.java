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

    public static Node reverseList(Node head) {
        Node currentNode = head; // 当前节点
        Node currentNodePrev = null; // 当前节点的上一个节点
        Node currentNodeNext = null; // 当前节点的下一个节点

        while (null != currentNode) {
            currentNodeNext = currentNode.next; // 保存下一个待遍历的节点信息
            currentNode.next = currentNodePrev; // 把当前节点的下一节点指向它的上一个节点
            currentNodePrev = currentNode; // 把当前节点的上一个节点设置成当前节点

            currentNode = currentNodeNext; // 更新当前节点 准备下次循环
        }

        return currentNodePrev;
    }

    public static Node reverseListRecursion(Node head) {
        if (null == head || null == head.next) {
            return head;
        }

        Node current = head;
        Node currentNodeNext = head.next;

        // 递归寻找最后一个节点
        Node newHead = reverseListRecursion(currentNodeNext);

        // 两两节点进行处理
        current.next.next = current; // 后一个节点指向前一个节点
        current.next = null; // 前一个节点的下一个节点指向空 避免循环链表

        return newHead; // 始终是反转后链表的头结点
    }

    public static Node constructList() {
        Node node5 = new Node(5, null);
        Node node4 = new Node(4, node5);
        Node node3 = new Node(3, node4);
        Node node2 = new Node(2, node3);
        Node node1 = new Node(1, node2);

        return node1;
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
