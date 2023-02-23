package ru.fyodor.models;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class Account {
    private final byte[] publicKey;
    private final byte[] address;
}
