package ru.fyodor.services.MerkleTree;

import lombok.Getter;
import lombok.Setter;
import ru.fyodor.services.HashGenerator;

import java.util.ArrayList;
import java.util.List;

public class MerkleTree {

    private static final HashGenerator hashGenerator = new HashGenerator();

    public static Node generateTree(List<byte[]> dataList) {
        List<Node> leafNodes = new ArrayList<>();

        for (byte[] data : dataList) {
            leafNodes.add(new Node(null, null, hashGenerator.generateHash(data)));
        }
        return calculateHead(leafNodes);
    }

    private static Node calculateHead(List<Node> leafNodes) {
        List<Node> roots = new ArrayList<>();

        while (leafNodes.size() != 1) {
            int i = 0, length = leafNodes.size();

            while (i < length) {
                Node leftLeaf = leafNodes.get(i);
                Node rigthLeaf = null;

                if (i + 1 < length) {
                    rigthLeaf = leafNodes.get(i + 1);
                } else {
                    rigthLeaf = new Node(null, null, leftLeaf.getHash());
                }
                roots.add(
                        new Node(
                                leftLeaf,
                                rigthLeaf,
                                hashGenerator.generateHash(
                                        concatArrays(
                                                leftLeaf.getHash(), rigthLeaf.getHash()
                                        )
                                )
                        )
                );
                i += 2;
            }
            leafNodes = roots;
            roots = new ArrayList<>();
        }
        return leafNodes.get(0);
    }

    private static byte[] concatArrays(byte[] a, byte[] b) {
        byte[] ab = new byte[a.length + b.length];
        System.arraycopy(a, 0, ab, 0, a.length);
        System.arraycopy(b, 0, ab, a.length, b.length);

        return ab;
    }
}
