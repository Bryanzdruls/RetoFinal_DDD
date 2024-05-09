package com.example.catanddd.bussiness.board;

import com.example.catanddd.bussiness.generic.IEventsRepository;
import com.example.catanddd.domain.board.commands.GenerateBoardCommand;
import com.example.catanddd.domain.board.commands.GenerateTerrainCommand;
import com.example.catanddd.domain.board.entities.terrain.values.TerrainEnum;
import com.example.catanddd.domain.board.events.GeneratedBoard;
import com.example.catanddd.domain.board.events.GeneratedTerrain;
import com.example.catanddd.domain.generic.DomainEvent;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(MockitoExtension.class)
class GenerateTerrainUseCaseTest {

    @Mock
    private IEventsRepository eventsRepository;


    @InjectMocks
    private GenerateTerrainUseCase generateTerrainUseCase;



    @DisplayName("GenerateTerrainUseCase")
    @Test
    void generateTerrain() {
        List<String> playersIds = new ArrayList<>();


        playersIds.add("brian");
        playersIds.add("juan");
        playersIds.add("pedro");
        playersIds.add("valeria");

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


        List<Integer> numeros = generateTerrainNumbers();
        List<TerrainEnum> terrainList = new ArrayList<>();

        terrainList.add(TerrainEnum.DESERT);


        terrainList.add(TerrainEnum.FOREST);
        terrainList.add(TerrainEnum.FOREST);
        terrainList.add(TerrainEnum.FOREST);
        terrainList.add(TerrainEnum.FOREST);

        terrainList.add(TerrainEnum.HILLS);
        terrainList.add(TerrainEnum.HILLS);
        terrainList.add(TerrainEnum.HILLS);
        terrainList.add(TerrainEnum.HILLS);

        terrainList.add(TerrainEnum.PASTURE);
        terrainList.add(TerrainEnum.PASTURE);
        terrainList.add(TerrainEnum.PASTURE);
        terrainList.add(TerrainEnum.PASTURE);

        terrainList.add(TerrainEnum.FIELDS);
        terrainList.add(TerrainEnum.FIELDS);
        terrainList.add(TerrainEnum.FIELDS);

        terrainList.add(TerrainEnum.MOUNTAINS);
        terrainList.add(TerrainEnum.MOUNTAINS);
        terrainList.add(TerrainEnum.MOUNTAINS);

        for (int i=0; i< 19; i++){
            if (terrainList.get(i).equals(TerrainEnum.DESERT)){
                GenerateTerrainCommand generatedTerrainCommand = new GenerateTerrainCommand(
                        "testBoardId",
                        "terrain"+i,
                        0,
                        terrainList.get(i).toString()
                );

                List<DomainEvent> result = generateTerrainUseCase.apply(generatedTerrainCommand);
                domainEvents.addAll(result);
            }else{
                GenerateTerrainCommand generatedTerrainCommand = new GenerateTerrainCommand(
                        "boardId",
                        "terrain"+i,
                        numeros.get(i),
                        terrainList.get(i).toString()
                );
                List<DomainEvent> result = generateTerrainUseCase.apply(generatedTerrainCommand);
                domainEvents.addAll(result);
            }
        }
        final int DOMAIN_EVENTS_MINUS_CREATION_BOARD = 1;
        assertEquals(terrainList.size(),domainEvents.size() - DOMAIN_EVENTS_MINUS_CREATION_BOARD);
    }


    @DisplayName("GenerateTerrainNotEnoughTerrains")
    @Test
    void generateTerrainNotEnoughTerrains() {
        List<String> playersIds = new ArrayList<>();


        playersIds.add("brian");
        playersIds.add("juan");
        playersIds.add("pedro");
        playersIds.add("valeria");

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


        List<Integer> numeros = generateTerrainNumbers();
        List<TerrainEnum> terrainList = new ArrayList<>();

        terrainList.add(TerrainEnum.DESERT);


        terrainList.add(TerrainEnum.FOREST);
        terrainList.add(TerrainEnum.FOREST);
        terrainList.add(TerrainEnum.FOREST);
        terrainList.add(TerrainEnum.FOREST);

        terrainList.add(TerrainEnum.HILLS);
        terrainList.add(TerrainEnum.HILLS);
        terrainList.add(TerrainEnum.HILLS);
        terrainList.add(TerrainEnum.HILLS);

        terrainList.add(TerrainEnum.PASTURE);
        terrainList.add(TerrainEnum.PASTURE);
        terrainList.add(TerrainEnum.PASTURE);
        terrainList.add(TerrainEnum.PASTURE);

        terrainList.add(TerrainEnum.FIELDS);
        terrainList.add(TerrainEnum.FIELDS);
        terrainList.add(TerrainEnum.FIELDS);

        terrainList.add(TerrainEnum.MOUNTAINS);
        terrainList.add(TerrainEnum.MOUNTAINS);
        terrainList.add(TerrainEnum.MOUNTAINS);

        for (int i=0; i < 13; i++){
            if (terrainList.get(i).equals(TerrainEnum.DESERT)){
                GenerateTerrainCommand generatedTerrainCommand = new GenerateTerrainCommand(
                        "testBoardId",
                        "terrain"+i,
                        0,
                        terrainList.get(i).toString()
                );

                List<DomainEvent> result = generateTerrainUseCase.apply(generatedTerrainCommand);
                domainEvents.addAll(result);
            }else{
                GenerateTerrainCommand generatedTerrainCommand = new GenerateTerrainCommand(
                        "boardId",
                        "terrain"+i,
                        numeros.get(i),
                        terrainList.get(i).toString()
                );
                List<DomainEvent> result = generateTerrainUseCase.apply(generatedTerrainCommand);
                domainEvents.addAll(result);
            }
        }
        final int DOMAIN_EVENTS_MINUS_CREATION_BOARD = 1;
        assertNotEquals(terrainList.size(),domainEvents.size() - DOMAIN_EVENTS_MINUS_CREATION_BOARD);
    }

    @Test
    void generateTerrainNotProperType() {
        List<String> playersIds = new ArrayList<>();


        playersIds.add("brian");
        playersIds.add("juan");
        playersIds.add("pedro");
        playersIds.add("valeria");

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

        GenerateTerrainCommand generatedTerrainCommand = new GenerateTerrainCommand(
                "testBoardId",
                "terrainUnique",
                0,
                "EXAMPLEBOOMCASE"
        );

        assertThrows((IllegalArgumentException.class),()-> generateTerrainUseCase.apply(generatedTerrainCommand));
    }

    private static List<Integer> generateTerrainNumbers() {
        List<Integer> numerosTerrenos = new ArrayList<>();


        for (int i = 2; i <= 12; i++) {
            if (i != 7) {
                numerosTerrenos.add(i);
            }
        }


        numerosTerrenos.add(6);
        numerosTerrenos.add(8);

        while (numerosTerrenos.size() < 19) {
            numerosTerrenos.add(6);
            numerosTerrenos.add(8);
        }

        Collections.shuffle(numerosTerrenos);

        while (numerosTerrenos.size() > 19) {
            numerosTerrenos.remove(numerosTerrenos.size() - 1);
        }

        return numerosTerrenos;
    }


}