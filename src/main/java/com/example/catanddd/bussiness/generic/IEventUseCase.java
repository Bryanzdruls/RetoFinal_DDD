package com.example.catanddd.bussiness.generic;

import com.example.catanddd.domain.generic.DomainEvent;

import java.util.List;

public interface IEventUseCase <T extends DomainEvent> {

    List<DomainEvent> apply(T event);
}
