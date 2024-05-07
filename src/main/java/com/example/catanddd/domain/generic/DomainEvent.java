package com.example.catanddd.domain.generic;

import java.io.Serializable;
import java.time.Instant;
import java.util.UUID;

public class DomainEvent implements Serializable {

    public final Instant when;

    public final UUID uuid;

    public final String type;

    private String aggregateRootId;
    private String aggregateName;

    private Long versionType;

    public DomainEvent(final String type){
        this.type = type;
        this.aggregateName = "default";
        this.when = Instant.now();
        this.uuid = UUID.randomUUID();
        this.versionType = 1L;
    }

    public Long versionType(){
        return versionType;
    }
    public void setVersionType(Long versionType){
        this.versionType = versionType;
    }

    public String aggregateRootId(){
        return aggregateRootId;
    }

    public void setAggregateRootI(String aggregateRootId){
        this.aggregateRootId = aggregateRootId;
    }

    public String aggregateName() {
        return aggregateName;
    }

    public void setAggregateName(String aggregateName) {
        this.aggregateName = aggregateName;
    }
}
