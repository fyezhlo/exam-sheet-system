package ru.fyodor.models;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import ru.fyodor.client.Client;

@Getter
@RequiredArgsConstructor
public abstract class Account implements Client {
    private final byte[] publicKey;
    private final byte[] address;
}
