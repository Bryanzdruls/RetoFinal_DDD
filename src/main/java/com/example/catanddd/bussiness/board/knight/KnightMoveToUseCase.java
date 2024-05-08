package com.example.catanddd.bussiness.board.knight;

import com.example.catanddd.bussiness.generic.ICommandUseCase;
import com.example.catanddd.bussiness.generic.IEventsRepository;
import com.example.catanddd.domain.board.Board;
import com.example.catanddd.domain.board.commands.knight.KnightMovedToCommand;
import com.example.catanddd.domain.generic.DomainEvent;

import java.util.List;

public class KnightMoveToUseCase implements ICommandUseCase<KnightMovedToCommand> {
    private IEventsRepository eventsRepository;

    public KnightMoveToUseCase(IEventsRepository eventsRepository) {
        this.eventsRepository = eventsRepository;
    }
    @Override
    public List<DomainEvent> apply(KnightMovedToCommand command) {
        List<DomainEvent> result = eventsRepository.findAggregateRootId(command.boardId());
        Board board = Board.from(command.boardId(),result);

        board.knightMoveTo(
                command.boardId(),
                command.knightId(),
                command.terrainId()
        );

        List<DomainEvent> domainEvents =  board.getUncommittedChanges();
        domainEvents.stream().map(eventsRepository::save);
        board.markChangeAsCommitted();

        return domainEvents;
    }
}
