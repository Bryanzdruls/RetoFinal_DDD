package com.example.catanddd.bussiness.player;

import com.example.catanddd.bussiness.generic.ICommandUseCase;
import com.example.catanddd.bussiness.generic.IEventsRepository;
import com.example.catanddd.domain.generic.DomainEvent;
import com.example.catanddd.domain.player.Player;
import com.example.catanddd.domain.player.commands.GeneratePlayerCommand;


import java.util.List;

public class GeneratePlayerUseCase implements ICommandUseCase<GeneratePlayerCommand> {

    private final IEventsRepository eventsRepository;

    public GeneratePlayerUseCase(IEventsRepository eventsRepository) {
        this.eventsRepository = eventsRepository;
    }
    @Override
    public List<DomainEvent> apply(GeneratePlayerCommand command) {
        Player player = new Player(
                command.playerId(),
                command.playerName(),
                command.points(),
                command.resources(),
                command.structures()
        );
        List<DomainEvent> domainEvents = player.getUncommittedChanges();
        domainEvents.stream().map(eventsRepository::save);

        player.markChangeAsCommitted();
        return domainEvents;
    }
}
