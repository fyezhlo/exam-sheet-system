package ru.fyodor.services.MerkleTree;

import lombok.Getter;
import lombok.Setter;
import ru.fyodor.services.HashGenerator;

import java.util.ArrayList;
import java.util.List;

public class MerkleTree {

    private static final HashGenerator hashGenerator = new HashGenerator();

    public static Node generateTree(List<byte[]> dataList) {
        List<byte[]> leafsHashList = new ArrayList<>();

        for (byte[] data : dataList) {
            leafsHashList.add(hashGenerator.generateHash(data));
        }

        return calculateHead(leafsHashList);
    }

    private static Node calculateHead(List<byte[]> leafsHashList) {


        return null;
    }
}
