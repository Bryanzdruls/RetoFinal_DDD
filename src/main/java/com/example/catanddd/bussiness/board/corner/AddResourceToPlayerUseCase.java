package com.example.catanddd.bussiness.board.corner;

import com.example.catanddd.bussiness.generic.ICommandUseCase;
import com.example.catanddd.bussiness.generic.IEventsRepository;
import com.example.catanddd.domain.board.Board;
import com.example.catanddd.domain.board.commands.AddResourceToPlayerCommand;
import com.example.catanddd.domain.generic.DomainEvent;

import java.util.List;

public class AddResourceToPlayerUseCase implements ICommandUseCase<AddResourceToPlayerCommand> {
    private final IEventsRepository eventsRepository;

    public AddResourceToPlayerUseCase(IEventsRepository eventsRepository) {
        this.eventsRepository = eventsRepository;
    }

    @Override
    public List<DomainEvent> apply(AddResourceToPlayerCommand command) {
        List<DomainEvent> result = eventsRepository.findAggregateRootId(command.boardId());
        Board board = Board.from(command.boardId(),result);

        board.addResourceToPlayer(
                command.boardId(),
                command.cornerId(),
                command.quantityResource(),
                command.isCornerAvailable(),
                command.playerId(),
                command.playerName()
        );

        List<DomainEvent> domainEvents =  board.getUncommittedChanges();
        domainEvents.stream().map(eventsRepository::save);
        board.markChangeAsCommitted();

        return domainEvents;
    }
}
