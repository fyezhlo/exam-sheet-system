package ru.fyodor.controller;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class Response {
    public ResponseType type;
    public byte[] data;
}
