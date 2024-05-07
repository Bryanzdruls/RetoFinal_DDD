package com.example.catanddd.bussiness.generic;

import com.example.catanddd.domain.generic.DomainEvent;

import java.util.List;

public interface IEventsRepository {
    DomainEvent save(DomainEvent event);

    List<DomainEvent> findAggregateRootId(String aggregateRootId);
}
