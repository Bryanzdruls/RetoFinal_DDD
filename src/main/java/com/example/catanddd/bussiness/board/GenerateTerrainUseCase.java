package com.example.catanddd.bussiness.board;

import com.example.catanddd.bussiness.generic.ICommandUseCase;
import com.example.catanddd.bussiness.generic.IEventsRepository;
import com.example.catanddd.domain.board.Board;
import com.example.catanddd.domain.board.commands.GenerateTerrainCommand;
import com.example.catanddd.domain.board.entities.terrain.Terrain;
import com.example.catanddd.domain.generic.DomainEvent;
import com.example.catanddd.domain.player.entities.structure.values.StructureId;

import java.util.List;
import java.util.Objects;

public class GenerateTerrainUseCase implements ICommandUseCase<GenerateTerrainCommand> {

    private final IEventsRepository eventsRepository;

    public GenerateTerrainUseCase(IEventsRepository eventsRepository) {
        this.eventsRepository = eventsRepository;
    }

    @Override
    public List<DomainEvent> apply(GenerateTerrainCommand command) {
        List<DomainEvent> result = eventsRepository.findAggregateRootId(command.boardId());
        Board board = Board.from(command.boardId(), result);

        //Objects.requireNonNull(StructureId.of(command.getStructureId()),"Terrain Id cannot be null");
        board.generateTerrain(
                command.boardId(),
                command.terrainId(),
                command.terrainNumber(),
                command.terrainType()
        );

        List<DomainEvent> domainEvents =  board.getUncommittedChanges();
        domainEvents.stream().map(eventsRepository::save);
        board.markChangeAsCommitted();

        return domainEvents;
    }
}
