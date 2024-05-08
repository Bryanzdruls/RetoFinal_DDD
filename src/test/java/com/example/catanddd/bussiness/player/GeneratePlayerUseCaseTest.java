package com.example.catanddd.bussiness.player;

import com.example.catanddd.bussiness.generic.IEventsRepository;
import com.example.catanddd.domain.board.entities.terrain.values.enums.ResourceEnum;
import com.example.catanddd.domain.board.events.GeneratedBoard;
import com.example.catanddd.domain.generic.DomainEvent;
import com.example.catanddd.domain.player.Player;
import com.example.catanddd.domain.player.commands.GeneratePlayerCommand;
import com.example.catanddd.domain.player.events.GeneratedPlayer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(MockitoExtension.class)
class GeneratePlayerUseCaseTest {

    @Mock
    private IEventsRepository eventsRepository;

    @InjectMocks
    private  GeneratePlayerUseCase generatePlayerUseCase;
    @Test
    void generatePlayerUseCase() {
        List<String> resources = new ArrayList<>();

        resources.add(ResourceEnum.WOOD.name());
        resources.add(ResourceEnum.BRICK.name());
        resources.add(ResourceEnum.SHEEP.name());
        resources.add(ResourceEnum.WHEAT.name());
        resources.add(ResourceEnum.ORE.name());


        GeneratePlayerCommand generatePlayerCommand = new GeneratePlayerCommand(
                "playerIdTest",
                "playerNameBrian",
                0,
                resources,
                new ArrayList<>()
        );

        GeneratedPlayer generatedPlayer = new GeneratedPlayer(
                "playerIdTest",
                "playerNameBrian",
                0,
                resources,
                new ArrayList<>()
        );

        generatedPlayer.setAggregateRootI("playerIdTest");

        List<DomainEvent> createPlayerEvents = generatePlayerUseCase.apply(generatePlayerCommand);

        DomainEvent player = createPlayerEvents.get(0);

        Assertions.assertTrue(player instanceof GeneratedPlayer);

        Assertions.assertEquals(generatedPlayer.aggregateRootId(), player.aggregateRootId());

    }

    @Test
    void generatePlayerUseCaseWithoutId() {
        List<String> resources = new ArrayList<>();

        resources.add(ResourceEnum.WOOD.name());
        resources.add(ResourceEnum.BRICK.name());
        resources.add(ResourceEnum.SHEEP.name());
        resources.add(ResourceEnum.WHEAT.name());
        resources.add(ResourceEnum.ORE.name());


        GeneratePlayerCommand generatePlayerCommand = new GeneratePlayerCommand(
                "playerIdTest",
                "playerNameBrian",
                0,
                resources,
                new ArrayList<>()
        );

        GeneratedPlayer generatedPlayer = new GeneratedPlayer(
                "playerIdTest",
                "playerNameBrian",
                0,
                resources,
                new ArrayList<>()
        );

        //generatedPlayer.setAggregateRootI("playerIdTest");

        List<DomainEvent> createPlayerEvents = generatePlayerUseCase.apply(generatePlayerCommand);

        DomainEvent player = createPlayerEvents.get(0);

        Assertions.assertTrue(player instanceof GeneratedPlayer);

        Assertions.assertNotSame(generatedPlayer.aggregateRootId(), player.aggregateRootId());

    }
}