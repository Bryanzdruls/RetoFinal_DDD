package com.example.catanddd.bussiness.board;

import com.example.catanddd.bussiness.generic.ICommandUseCase;
import com.example.catanddd.bussiness.generic.IEventsRepository;
import com.example.catanddd.domain.board.Board;
import com.example.catanddd.domain.board.commands.ChangeTurnCommand;
import com.example.catanddd.domain.generic.DomainEvent;

import java.util.List;

public class ChangeTurnUseCase implements ICommandUseCase<ChangeTurnCommand> {
    private final IEventsRepository eventsRepository;

    public ChangeTurnUseCase(IEventsRepository eventsRepository) {
        this.eventsRepository = eventsRepository;
    }

    @Override
    public List<DomainEvent> apply(ChangeTurnCommand command) throws IllegalStateException{
        List<DomainEvent> result = eventsRepository.findAggregateRootId(command.boardId());
        Board board = Board.from(command.boardId(),result);

        board.changeTurn(
                command.boardId(),
                command.turnId(),
                command.turnPlayerName(),
                command.playersInGame()
        );

        List<DomainEvent> domainEvents =  board.getUncommittedChanges();
        domainEvents.stream().map(eventsRepository::save);
        board.markChangeAsCommitted();

        return domainEvents;
    }
}
