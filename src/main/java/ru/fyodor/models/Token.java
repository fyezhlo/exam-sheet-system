package ru.fyodor.models;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class Token {
    private final byte[] dataHash;
    private final Collection collection;
}
