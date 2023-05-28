package ru.fyodor.controller;

import org.assertj.core.util.Hexadecimals;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.fyodor.client.Account;
import ru.fyodor.client.AccountService;

import java.nio.charset.StandardCharsets;
import java.time.Instant;

@RestController
@RequestMapping("/chain")
public class ChainRestController {

    @Autowired
    AccountService accountService;

    @PostMapping("/create-account")
    public ResponseEntity<String> createAccount(
            @RequestBody String seed
    ) {

        Account account = accountService.createAccount(
                seed.concat(Instant.now().toString()).getBytes(StandardCharsets.UTF_8)
        );

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(Hexadecimals.toHexString(account.getPublicKey()));
    }

    @PostMapping("/push-data")
    public ResponseEntity<String> pushData(
            @RequestBody String token
    ) {
        accountService.pushData(token.getBytes(StandardCharsets.UTF_8));

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(
                        "vse ok"
                );
    }

}