package ru.fyodor.models;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@RequiredArgsConstructor
public class Token {
    @Setter
    private byte[] tokenId;
    private final byte[] dataHash;
    private final Collection collection;
}
