package com.example.catanddd.bussiness.player;

import com.example.catanddd.bussiness.generic.IEventsRepository;
import com.example.catanddd.domain.board.entities.terrain.Terrain;
import com.example.catanddd.domain.board.entities.terrain.values.TerrainEnum;
import com.example.catanddd.domain.board.entities.terrain.values.TerrainId;
import com.example.catanddd.domain.board.entities.terrain.values.enums.ResourceEnum;
import com.example.catanddd.domain.generic.DomainEvent;
import com.example.catanddd.domain.player.commands.GeneratePlayerCommand;
import com.example.catanddd.domain.player.commands.GenerateStructureCommand;
import com.example.catanddd.domain.player.entities.structure.values.StructureType;
import com.example.catanddd.domain.player.entities.structure.values.enums.StructureTypeEnum;
import com.example.catanddd.domain.player.events.GeneratedPlayer;
import com.example.catanddd.domain.player.events.GeneratedStructure;
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
class GenerateStructureUseCaseTest {

    @Mock
    private IEventsRepository eventsRepository;
    @InjectMocks
    private GenerateStructureUseCase generateStructureUseCase;
    @Test
    void generateStructure() {

        List<String> resources = new ArrayList<>();

        resources.add(ResourceEnum.WOOD.name());
        resources.add(ResourceEnum.BRICK.name());
        resources.add(ResourceEnum.SHEEP.name());
        resources.add(ResourceEnum.WHEAT.name());
        resources.add(ResourceEnum.ORE.name());

        List<TerrainId> terrainIds = new ArrayList<>();

        terrainIds.add(TerrainId.of("terrain1"));
        terrainIds.add(TerrainId.of("terrain2"));
        terrainIds.add(TerrainId.of("terrain3"));
        GenerateStructureCommand generateStructureCommand = new GenerateStructureCommand(
                "playerIdTest",
                "structureId",
                StructureTypeEnum.SETTLEMENT.name(),
                terrainIds
        );

        GeneratedPlayer generatedPlayer = new GeneratedPlayer(
                "playerIdTest",
                "playerNameBrian",
                0,
                resources,
                new ArrayList<>()
        );
        generatedPlayer.setAggregateRootI("playerIdTest");

        List<DomainEvent> domainEvents = new ArrayList<>();

        domainEvents.add(generatedPlayer);

        Mockito.when(eventsRepository.findAggregateRootId("playerIdTest")).thenReturn(domainEvents);

        List<DomainEvent> result = generateStructureUseCase.apply(generateStructureCommand);

        GeneratedStructure generatedStructure = (GeneratedStructure) result.get(0);

        assertEquals(generatedStructure.playerId(), "playerIdTest");
        assertEquals(generatedStructure.structureId(), "structureId");
        assertEquals(generatedStructure.structureType(), StructureTypeEnum.SETTLEMENT.name());
        assertEquals(generatedStructure.terrainsRelated(), terrainIds);

    }

    @Test
    void generateStructureNullAggregateId() {

        List<String> resources = new ArrayList<>();

        resources.add(ResourceEnum.WOOD.name());
        resources.add(ResourceEnum.BRICK.name());
        resources.add(ResourceEnum.SHEEP.name());
        resources.add(ResourceEnum.WHEAT.name());
        resources.add(ResourceEnum.ORE.name());

        List<TerrainId> terrainIds = new ArrayList<>();

        terrainIds.add(TerrainId.of("terrain1"));
        terrainIds.add(TerrainId.of("terrain2"));
        terrainIds.add(TerrainId.of("terrain3"));
        GenerateStructureCommand generateStructureCommand = new GenerateStructureCommand(
                "playerIdTest",
                "structureId",
                StructureTypeEnum.SETTLEMENT.name(),
                terrainIds
        );

        GeneratedStructure generatedStructureExpected = new GeneratedStructure(
                "otherPlayerId",
                "differentStructure",
                StructureTypeEnum.ROAD.name(),
                terrainIds
        );
        GeneratedPlayer generatedPlayer = new GeneratedPlayer(
                "playerIdTest",
                "playerNameBrian",
                0,
                resources,
                new ArrayList<>()
        );
        generatedPlayer.setAggregateRootI("playerIdTest");

        List<DomainEvent> domainEvents = new ArrayList<>();

        domainEvents.add(generatedPlayer);

        Mockito.when(eventsRepository.findAggregateRootId("playerIdTest")).thenReturn(domainEvents);

        List<DomainEvent> result = generateStructureUseCase.apply(generateStructureCommand);

        GeneratedStructure generatedStructure = (GeneratedStructure) result.get(0);

        assertNotEquals(generatedStructure.playerId(), generatedStructureExpected.playerId());
        assertNotEquals(generatedStructure.structureId(), generatedStructureExpected.structureId());
        assertNotEquals(generatedStructure.structureType(), generatedStructureExpected.structureType());
    }
}