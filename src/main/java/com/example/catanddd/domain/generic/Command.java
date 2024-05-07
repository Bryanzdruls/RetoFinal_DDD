package com.example.catanddd.domain.generic;

import java.time.Instant;
import java.util.UUID;

public class Command {
    public final Instant when;
    private final String uuid;

    public Command() {
        this.uuid = UUID.randomUUID().toString();
        this.when = Instant.now();
    }

    public Instant when() {
        return when;
    }

    public String uuid() {
        return uuid;
    }
}
