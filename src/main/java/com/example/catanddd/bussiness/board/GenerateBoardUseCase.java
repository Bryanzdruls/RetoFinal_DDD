package com.example.catanddd.bussiness.board;

import com.example.catanddd.bussiness.generic.ICommandUseCase;
import com.example.catanddd.bussiness.generic.IEventsRepository;
import com.example.catanddd.domain.board.Board;
import com.example.catanddd.domain.board.commands.GenerateBoardCommand;
import com.example.catanddd.domain.generic.DomainEvent;

import java.util.List;

public class GenerateBoardUseCase implements ICommandUseCase<GenerateBoardCommand> {

    private final IEventsRepository eventsRepository;
    public GenerateBoardUseCase(IEventsRepository eventsRepository){
        this.eventsRepository = eventsRepository;
    }
    @Override
    public List<DomainEvent> apply(GenerateBoardCommand command) {
        Board board = new Board(
                command.boardId(),
                command.dice(),
                command.knightId(),
                command.turnId(),
                command.turnPlayerName(),
                command.playersIds(),
                command.playerInTurnId()
        );
        List<DomainEvent> domainEvents = board.getUncommittedChanges();
        domainEvents.stream().map(eventsRepository::save);

        board.markChangeAsCommitted();
        return domainEvents;
    }
}
