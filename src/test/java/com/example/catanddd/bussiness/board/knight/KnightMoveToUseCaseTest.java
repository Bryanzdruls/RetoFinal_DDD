package com.example.catanddd.bussiness.board.knight;

import com.example.catanddd.bussiness.generic.IEventsRepository;
import com.example.catanddd.domain.board.commands.ChangeTurnCommand;
import com.example.catanddd.domain.board.commands.knight.KnightMovedToCommand;
import com.example.catanddd.domain.board.events.GeneratedBoard;
import com.example.catanddd.domain.board.events.GeneratedTerrain;
import com.example.catanddd.domain.board.events.KnightMovedTo;
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
class KnightMoveToUseCaseTest {

    @Mock
    private IEventsRepository eventsRepository;

    @InjectMocks
    private KnightMoveToUseCase knightMoveToUseCase;

    @InjectMocks
    private GeneratedTerrain generatedTerrain;
    @Test
    void moveKnight() {
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


        List<DomainEvent> domainEvents = new ArrayList<>();

        domainEvents.add(generatedBoard);

        Mockito.when(eventsRepository.findAggregateRootId("testBoardId")).thenReturn(domainEvents);


        KnightMovedToCommand knightMovedToCommand = new KnightMovedToCommand(
                "testBoardId",
                "testKnightId",
                "terrainId"
        );

        List<DomainEvent> result = knightMoveToUseCase.apply(knightMovedToCommand);
    }

    void moveKnightButIsAlreadyThere() {
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


        List<DomainEvent> domainEvents = new ArrayList<>();

        domainEvents.add(generatedBoard);

        Mockito.when(eventsRepository.findAggregateRootId("testBoardId")).thenReturn(domainEvents);


        KnightMovedToCommand knightMovedToCommand = new KnightMovedToCommand(
                "testBoardId",
                "testKnightId",
                "terrainId"
        );

        List<DomainEvent> result = knightMoveToUseCase.apply(knightMovedToCommand);


    }

}