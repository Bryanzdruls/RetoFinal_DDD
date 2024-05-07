package com.example.catanddd.bussiness.generic;

import com.example.catanddd.domain.generic.Command;
import com.example.catanddd.domain.generic.DomainEvent;

import java.util.List;

public interface ICommandUseCase <C extends Command> {

    List<DomainEvent> apply(C command);
}
