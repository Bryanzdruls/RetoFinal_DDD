package com.example.catanddd.domain.generic;

import java.io.Serializable;

public interface ValueObject<G> extends Serializable {

    G value();
}
