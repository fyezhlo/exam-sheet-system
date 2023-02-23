package ru.fyodor.models;

import lombok.Getter;

import java.util.List;

@Getter
public class Collection {
    private Account collectionOwner;
    private byte[] collectionId;
    private List<Token> tokenList;
}
