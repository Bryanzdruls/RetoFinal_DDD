package com.example.catanddd.domain.generic;

import java.util.Objects;

public class Entity <I extends Identity>{

    private final I id;

    public Entity(I id){
        this.id = id;
    }

    public I identity(){
        return id;
    }

    @Override
    public boolean equals(Object o) { return id.equals(o); }

    @Override
    public int hashCode() { return Objects.hash(id); }
}