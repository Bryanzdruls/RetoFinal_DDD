package com.example.catanddd.bussiness.board.corner;

import com.example.catanddd.bussiness.generic.IEventsRepository;
import com.example.catanddd.domain.board.Board;
import com.example.catanddd.domain.board.commands.AddResourceToPlayerCommand;
import com.example.catanddd.domain.board.commands.GenerateBoardCommand;
import com.example.catanddd.domain.board.commands.GenerateTerrainCommand;
import com.example.catanddd.domain.board.entities.terrain.values.TerrainEnum;
import com.example.catanddd.domain.board.events.AddedResourceToPlayer;
import com.example.catanddd.domain.board.events.GeneratedBoard;
import com.example.catanddd.domain.board.events.GeneratedTerrain;
import com.example.catanddd.domain.generic.DomainEvent;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class AddResourceToPlayerUseCaseTest {


    @Mock
    private IEventsRepository eventsRepository;

    @Mock
    private Board board;

    @InjectMocks
    private AddResourceToPlayerUseCase addResourceToPlayerUseCase;
    @Test
    void AddResourceToPlayer() {
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

        List<DomainEvent> domainEvents = new ArrayList<>();

        domainEvents.add(generatedBoard);

        Mockito.when(eventsRepository.findAggregateRootId("testBoardId")).thenReturn(domainEvents);


        GeneratedTerrain generatedTerrain = new GeneratedTerrain(
                "testBoardId",
                "terrainId",
                6,
                TerrainEnum.FOREST.name()
        );

        AddResourceToPlayerCommand addResourceToPlayerCommand = new AddResourceToPlayerCommand(
                "testBoardId",
                "cornerId",
                0,
                true,
                "testPlayerBrian",
                "testPlayerBrianName"
        );

        AddedResourceToPlayer addedResourceToPlayerExpected = new AddedResourceToPlayer(
                "testBoardId",
                "cornerId",
                0,
                true,
                "testPlayerBrian",
                "testPlayerBrianName"
        );
        List<DomainEvent> addResourceResponse = addResourceToPlayerUseCase.apply(addResourceToPlayerCommand);

        AddedResourceToPlayer addedResourceToPlayer = (AddedResourceToPlayer) addResourceResponse.get(0);


        assertEquals(addedResourceToPlayer.aggregateRootId(), addedResourceToPlayerExpected.boardId());
        assertEquals(addedResourceToPlayer.quantityResource(), addedResourceToPlayerExpected.quantityResource());
    }
    @Test
    void AddResourceToPlayerWithoutId() {
        List<String> playersIds = new ArrayList<>();

        playersIds.add("brian");
        playersIds.add("juan");
        playersIds.add("pedro");
        playersIds.add("valeria");

        GenerateBoardCommand generateBoardCommand =
                new GenerateBoardCommand(
                        null,
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

        //generatedBoard.setAggregateRootI("testBoardId");

        List<DomainEvent> domainEvents = new ArrayList<>();

        domainEvents.add(generatedBoard);

        //Mockito.when(eventsRepository.findAggregateRootId("testBoardId")).thenReturn(domainEvents);


        GeneratedTerrain generatedTerrain = new GeneratedTerrain(
                "testBoardId",
                "terrainId",
                6,
                TerrainEnum.FOREST.name()
        );

        AddResourceToPlayerCommand addResourceToPlayerCommand = new AddResourceToPlayerCommand(
                null,
                "cornerId",
                0,
                true,
                "testPlayerBrian",
                "testPlayerBrianName"
        );

        AddedResourceToPlayer addedResourceToPlayerExpected = new AddedResourceToPlayer(
                "testBoardId",
                "cornerId",
                0,
                true,
                "testPlayerBrian",
                "testPlayerBrianName"
        );
        List<DomainEvent> addResourceResponse = addResourceToPlayerUseCase.apply(addResourceToPlayerCommand);

        AddedResourceToPlayer addedResourceToPlayer = (AddedResourceToPlayer) addResourceResponse.get(0);


        assertNotEquals(addedResourceToPlayer.aggregateRootId(), addedResourceToPlayerExpected.boardId());

    }
    @Test
    void AddResourceToPlayerNullPlayer() {
        List<String> playersIds = new ArrayList<>();

        playersIds.add("brian");
        playersIds.add("juan");
        playersIds.add("pedro");
        playersIds.add("valeria");

        GenerateBoardCommand generateBoardCommand =
                new GenerateBoardCommand(
                        null,
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

        List<DomainEvent> domainEvents = new ArrayList<>();

        domainEvents.add(generatedBoard);

        Mockito.when(eventsRepository.findAggregateRootId("testBoardId")).thenReturn(domainEvents);


        GeneratedTerrain generatedTerrain = new GeneratedTerrain(
                "testBoardId",
                "terrainId",
                6,
                TerrainEnum.FOREST.name()
        );

        AddResourceToPlayerCommand addResourceToPlayerCommand = new AddResourceToPlayerCommand(
                "testBoardId",
                "cornerId",
                0,
                true,
                null,
                null
        );

        AddedResourceToPlayer addedResourceToPlayerExpected = new AddedResourceToPlayer(
                "testBoardId",
                "cornerId",
                0,
                true,
                "testPlayerBrian",
                "testPlayerBrianName"
        );


        assertThrows((IllegalArgumentException.class),()-> addResourceToPlayerUseCase.apply(addResourceToPlayerCommand));
    }

    @Test
    void AddResourceToPlayerNullCorner() {
        List<String> playersIds = new ArrayList<>();

        playersIds.add("brian");
        playersIds.add("juan");
        playersIds.add("pedro");
        playersIds.add("valeria");

        GenerateBoardCommand generateBoardCommand =
                new GenerateBoardCommand(
                        null,
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

        List<DomainEvent> domainEvents = new ArrayList<>();

        domainEvents.add(generatedBoard);

        Mockito.when(eventsRepository.findAggregateRootId("testBoardId")).thenReturn(domainEvents);


        GeneratedTerrain generatedTerrain = new GeneratedTerrain(
                "testBoardId",
                "terrainId",
                6,
                TerrainEnum.FOREST.name()
        );

        AddResourceToPlayerCommand addResourceToPlayerCommand = new AddResourceToPlayerCommand(
                "testBoardId",
                null,
                0,
                true,
                null,
                null
        );

        AddedResourceToPlayer addedResourceToPlayerExpected = new AddedResourceToPlayer(
                "testBoardId",
                "cornerId",
                0,
                true,
                "testPlayerBrian",
                "testPlayerBrianName"
        );


        assertThrows((IllegalArgumentException.class),()-> addResourceToPlayerUseCase.apply(addResourceToPlayerCommand));
    }
}