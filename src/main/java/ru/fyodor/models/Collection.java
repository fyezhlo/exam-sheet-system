package ru.fyodor.models;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import ru.fyodor.p2p.Peer;

import java.util.List;

@Getter
@RequiredArgsConstructor
public class Collection {
    private final Peer collectionOwner;
    private final byte[] collectionId;
    private List<Token> tokenList;
}
