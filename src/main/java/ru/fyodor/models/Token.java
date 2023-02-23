package ru.fyodor.models;

import lombok.Getter;

@Getter
public class Token {
    private byte[] dataHash;
    private Collection collection;
}
