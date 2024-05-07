package com.example.catanddd.domain.generic;

import java.util.List;

public class AggregateRoot<I extends  Identity> extends Entity<I>{
    private final ChangeEventSubscriber changeEventSubscriber;
    public AggregateRoot(I id) {
        super(id);
        changeEventSubscriber =new ChangeEventSubscriber();
    }

    protected ChangeApply appendChangeEvent(DomainEvent event){
        var nameClass = identity().getClass().getName();
        var aggregate = nameClass.replaceAll("Identity|Id|ID|", "").toLowerCase();
        event.setAggregateName(aggregate);
        event.setAggregateRootI(identity().value());
        return changeEventSubscriber.appendChange(event);
    }

    public List<DomainEvent> getUncommittedChanges(){
        return List.copyOf(changeEventSubscriber.changes());
    }

    public final void subscribe(EventChange eventChange){
        changeEventSubscriber.subscribe(eventChange);
    }

    protected void applyEvent (DomainEvent event){
        changeEventSubscriber.applyEvent(event);
    }

    public void markChangeAsCommitted(){
        changeEventSubscriber.changes().clear();
    }
}
