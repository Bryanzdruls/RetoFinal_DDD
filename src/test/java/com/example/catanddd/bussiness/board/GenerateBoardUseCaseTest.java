package com.example.catanddd.bussiness.board;

import com.example.catanddd.bussiness.generic.IEventsRepository;
import com.example.catanddd.domain.board.commands.GenerateBoardCommand;
import com.example.catanddd.domain.board.entities.knight.values.KnightId;
import com.example.catanddd.domain.board.entities.turn.values.PlayerName;
import com.example.catanddd.domain.board.entities.turn.values.TurnId;
import com.example.catanddd.domain.board.events.GeneratedBoard;
import com.example.catanddd.domain.board.values.BoardId;
import com.example.catanddd.domain.board.values.Dice;
import com.example.catanddd.domain.generic.DomainEvent;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatcher;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(MockitoExtension.class)
class GenerateBoardUseCaseTest {

    @Mock
    private IEventsRepository eventsRepository;

    private GenerateBoardUseCase generateBoardUseCase;

    @BeforeEach
    void setup() {
        generateBoardUseCase = new GenerateBoardUseCase(eventsRepository);
    }


    @Test
    void testCreateBoardHappy() {
        List<String> playersIds = new ArrayList<>();

        playersIds.add("brian");
        playersIds.add("juan");
        playersIds.add("pedro");
        playersIds.add("valeria");

        GenerateBoardCommand generateBoardCommand =
                new GenerateBoardCommand(
                        "testBoardId",
                        0,
                        "testKnightId",
                        "testTurnId",
                        "brian",
                        playersIds,
                        "brianInTurnId"
        );

        GeneratedBoard generatedBoard = new GeneratedBoard(
                0,
                "testKnightId",
                "testTurnId",
                "brian",
                playersIds,
                "brianInTurnId"
        );

        generatedBoard.setAggregateRootI("testBoardId");

        /*Mockito.when(eventsRepository.save(ArgumentMatchers.any(GeneratedBoard.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));*/

        List<DomainEvent> createBoardEvents = generateBoardUseCase.apply(generateBoardCommand);



        Assertions.assertEquals(1, createBoardEvents.size());

        DomainEvent board = createBoardEvents.get(0);

        Assertions.assertTrue(board instanceof GeneratedBoard);

        Assertions.assertEquals(generatedBoard.aggregateRootId(), board.aggregateRootId());
        Assertions.assertEquals(generatedBoard.dice(), ((GeneratedBoard) board).dice());
        Assertions.assertEquals(generatedBoard.playerInTurnId(), ((GeneratedBoard) board).playerInTurnId());
    }

    @Test
    void testCreateBoardPlayerNotEquals() {
        List<String> playersIds = new ArrayList<>();

        playersIds.add("brian");
        playersIds.add("juan");
        playersIds.add("pedro");
        playersIds.add("valeria");

        GenerateBoardCommand generateBoardCommand =
                new GenerateBoardCommand(
                        "testBoardId",
                        0,
                        "testKnightId",
                        "testTurnId",
                        "jose",
                        playersIds,
                        "joseInTurnId"
        );

        GeneratedBoard generatedBoard = new GeneratedBoard(
                0,
                "testKnightId",
                "testTurnId",
                "brian",
                playersIds,
                "brianInTurnId"
        );

        generatedBoard.setAggregateRootI("testBoardId");

        /*Mockito.when(eventsRepository.save(ArgumentMatchers.any(GeneratedBoard.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));*/

        List<DomainEvent> createBoardEvents = generateBoardUseCase.apply(generateBoardCommand);

        Assertions.assertEquals(1, createBoardEvents.size());

        DomainEvent board = createBoardEvents.get(0);

        Assertions.assertTrue(board instanceof GeneratedBoard);

        Assertions.assertEquals(generatedBoard.aggregateRootId(), board.aggregateRootId());
        Assertions.assertEquals(generatedBoard.dice(), ((GeneratedBoard) board).dice());
        Assertions.assertNotSame(generatedBoard.playerInTurnId(), ((GeneratedBoard) board).playerInTurnId());
    }

    @Test
    void testCreateBoardIdNull() {
        List<String> playersIds = new ArrayList<>();

        playersIds.add("brian");
        playersIds.add("juan");
        playersIds.add("pedro");
        playersIds.add("valeria");

        GenerateBoardCommand generateBoardCommand =
                new GenerateBoardCommand(
                        "testBoardId",
                        0,
                        "testKnightId",
                        "testTurnId",
                        "jose",
                        playersIds,
                        "joseInTurnId"
                );

        GeneratedBoard generatedBoard = new GeneratedBoard(
                0,
                "testKnightId",
                "testTurnId",
                "brian",
                playersIds,
                "brianInTurnId"
        );

        /*Mockito.when(eventsRepository.save(ArgumentMatchers.any(GeneratedBoard.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));*/

        List<DomainEvent> createBoardEvents = generateBoardUseCase.apply(generateBoardCommand);

        Assertions.assertEquals(1, createBoardEvents.size());

        DomainEvent board = createBoardEvents.get(0);

        Assertions.assertTrue(board instanceof GeneratedBoard);

        Assertions.assertNull(generatedBoard.aggregateRootId());
    }

}