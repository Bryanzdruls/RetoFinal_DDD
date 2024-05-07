package com.example.catanddd.bussiness.player;

import com.example.catanddd.bussiness.generic.ICommandUseCase;
import com.example.catanddd.bussiness.generic.IEventsRepository;
import com.example.catanddd.domain.generic.DomainEvent;
import com.example.catanddd.domain.player.Player;
import com.example.catanddd.domain.player.commands.GenerateStructureCommand;
import com.example.catanddd.domain.player.entities.structure.values.StructureId;

import java.util.List;
import java.util.Objects;

public class GenerateStructureUseCase implements ICommandUseCase<GenerateStructureCommand> {

    private final IEventsRepository eventsRepository;

    public GenerateStructureUseCase(IEventsRepository eventsRepository) {
        this.eventsRepository = eventsRepository;
    }

    @Override
    public List<DomainEvent> apply(GenerateStructureCommand command) {
        List<DomainEvent> result = eventsRepository.findAggregateRootId(command.getPlayerId());
        Player player = Player.from(command.getPlayerId(), result);

        Objects.requireNonNull(StructureId.of(command.getStructureId()),"Structure Id cannot be null");

        player.generateStructure(
                command.getPlayerId(),
                command.getStructureId(),
                command.getStructureType(),
                command.getTerrainRelated()
        );

        List<DomainEvent> domainEvents =  player.getUncommittedChanges();
        domainEvents.stream().map(eventsRepository::save);
        player.markChangeAsCommitted();

        return domainEvents;
    }
}
