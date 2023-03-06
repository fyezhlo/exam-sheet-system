package ru.fyodor.services.MerkleTree;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Node {
    public Node left;
    public Node right;
    public byte[] hash;

    public Node(Node left, Node right, byte[] hash) {
        this.left = left;
        this.right = right;
        this.hash = hash;
    }
}
