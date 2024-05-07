package com.example.catanddd.bussiness.board;

import com.example.catanddd.bussiness.generic.IEventsRepository;
import com.example.catanddd.domain.board.commands.ChangeTurnCommand;
import com.example.catanddd.domain.board.commands.ChangeTurnReverseCommand;
import com.example.catanddd.domain.board.events.ChangedTurn;
import com.example.catanddd.domain.board.events.GeneratedBoard;
import com.example.catanddd.domain.generic.DomainEvent;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ChangeTurnReverseUseCaseTest {
    @Mock
    private IEventsRepository eventsRepository;

    @InjectMocks
    private ChangeTurnReverseUseCase changeTurnReverseUseCase;

    @Test
    void changeTurnReverseUseCase() {
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

        ChangeTurnReverseCommand changeTurnReverseCommand = new ChangeTurnReverseCommand(
                "testBoardId",
                "testTurnId",
                "brian",
                playersQueue
        );
        List<DomainEvent> domainEvents = new ArrayList<>();

        domainEvents.add(generatedBoard);

        Mockito.when(eventsRepository.findAggregateRootId("testBoardId")).thenReturn(domainEvents);

        List<DomainEvent> result = changeTurnReverseUseCase.apply(changeTurnReverseCommand);

        ChangedTurn changedTurnResult = (ChangedTurn) result.get(0);
        assertEquals("testBoardId",changedTurnResult.getBoardId());
        assertEquals();
        assertEquals();
    }
}