package ru.fyodor.models;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Getter
@RequiredArgsConstructor
public class Collection {
    private final Account collectionOwner;
    private final byte[] collectionId;
    private List<Token> tokenList;
}
