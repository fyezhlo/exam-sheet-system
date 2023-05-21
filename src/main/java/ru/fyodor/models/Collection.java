package ru.fyodor.models;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import ru.fyodor.client.AccountService;

import java.util.List;

@Getter
@RequiredArgsConstructor
public class Collection {
    private final AccountService collectionOwner;
    private final byte[] collectionId;
    private List<Token> tokenList;
}
