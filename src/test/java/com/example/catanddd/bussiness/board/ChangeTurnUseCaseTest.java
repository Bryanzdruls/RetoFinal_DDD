package com.example.catanddd.bussiness.board;

import com.example.catanddd.bussiness.generic.IEventsRepository;
import com.example.catanddd.domain.board.commands.ChangeTurnCommand;
import com.example.catanddd.domain.board.commands.GenerateBoardCommand;
import com.example.catanddd.domain.board.events.ChangedTurn;
import com.example.catanddd.domain.board.events.GeneratedBoard;
import com.example.catanddd.domain.generic.DomainEvent;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(MockitoExtension.class)
class ChangeTurnUseCaseTest {

    @Mock
    private IEventsRepository eventsRepository;

    @InjectMocks
    private ChangeTurnUseCase changeTurnUseCase;

    @Test
    void changeTurnHappy() {
        List<String> playersIds = new LinkedList<>();

        playersIds.add("brian");
        playersIds.add("juan");
        playersIds.add("pedro");
        playersIds.add("valeria");

        Queue<String> playersQueue = new LinkedList<>(playersIds);

        GeneratedBoard generatedBoard = new GeneratedBoard(
                0,
                "testKnightId",
                "testTurnId",
                "brian",
                playersIds,
                "brianInTurnId"
        );

        generatedBoard.setAggregateRootI("testBoardId");

        ChangeTurnCommand changeTurnCommand = new ChangeTurnCommand(
                "testBoardId",
                "testTurnId",
                "brian",
                playersQueue
        );
        List<DomainEvent> domainEvents = new ArrayList<>();

        domainEvents.add(generatedBoard);

        Mockito.when(eventsRepository.findAggregateRootId("testBoardId")).thenReturn(domainEvents);

        List<DomainEvent> result = changeTurnUseCase.apply(changeTurnCommand);

        ChangedTurn changedTurnResult = (ChangedTurn) result.get(0);
        Assertions.assertEquals(changedTurnResult.turnId(), generatedBoard.turnId());
        Assertions.assertEquals(changedTurnResult.turnPlayerName(), generatedBoard.turnPlayerName());
        Assertions.assertEquals(changedTurnResult.boardId(), generatedBoard.aggregateRootId());
        Assertions.assertEquals(changedTurnResult.playersInGame(), generatedBoard.playersIds());
    }

    @Test
    void changeTurnWithoutBoard() {
        List<String> playersIds = new LinkedList<>();

        playersIds.add("brian");
        playersIds.add("juan");
        playersIds.add("pedro");
        playersIds.add("valeria");

        Queue<String> playersQueue = new LinkedList<>(playersIds);

        GeneratedBoard generatedBoard = new GeneratedBoard(
                0,
                "testKnightId",
                "testTurnId",
                "brian",
                playersIds,
                "brianInTurnId"
        );

        generatedBoard.setAggregateRootI("testBoardId");

        ChangeTurnCommand changeTurnCommand = new ChangeTurnCommand(
                "testBoardId",
                "testTurnId",
                "brian",
                playersQueue
        );
        List<DomainEvent> domainEvents = new ArrayList<>();

        domainEvents.add(generatedBoard);

        //Mockito.when(eventsRepository.findAggregateRootId("testBoardId")).thenReturn(domainEvents);


        Assertions.assertThrows((NullPointerException.class),() ->changeTurnUseCase.apply(changeTurnCommand));
    }

    @Test
    void changeTurnDifferentId() {
        List<String> playersIds = new LinkedList<>();

        playersIds.add("brian");
        playersIds.add("juan");
        playersIds.add("pedro");
        playersIds.add("valeria");

        Queue<String> playersQueue = new LinkedList<>(playersIds);

        GeneratedBoard generatedBoard = new GeneratedBoard(
                0,
                "testKnightId",
                "testTurnId1",
                "juan",
                playersIds,
                "juanInTurnId"
        );

        generatedBoard.setAggregateRootI("testBoardId");

        ChangeTurnCommand changeTurnCommand = new ChangeTurnCommand(
                "testBoardId",
                "testTurnId",
                "brian",
                playersQueue
        );
        List<DomainEvent> domainEvents = new ArrayList<>();

        domainEvents.add(generatedBoard);

        Mockito.when(eventsRepository.findAggregateRootId("testBoardId")).thenReturn(domainEvents);

        List<DomainEvent> result = changeTurnUseCase.apply(changeTurnCommand);

        ChangedTurn changedTurnResult = (ChangedTurn) result.get(0);

        Assertions.assertNotSame(changedTurnResult.turnId(), generatedBoard.turnId());
        Assertions.assertNotSame(changedTurnResult.turnPlayerName(), generatedBoard.turnPlayerName());

        playersIds.remove("valeria");
        Assertions.assertNotSame(changedTurnResult.playersInGame(), generatedBoard.playersIds());
    }
}