package com.example.catanddd.bussiness.player.structure;

import com.example.catanddd.bussiness.generic.IEventsRepository;
import com.example.catanddd.domain.board.entities.terrain.values.enums.ResourceEnum;
import com.example.catanddd.domain.generic.DomainEvent;
import com.example.catanddd.domain.player.commands.structure.UpdateSettlementCommand;
import com.example.catanddd.domain.player.events.GeneratedPlayer;
import com.example.catanddd.domain.player.events.structure.UpdatedSettlement;
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
class UpdatedSettlementUseCaseTest {

    @Mock
    private IEventsRepository eventsRepository;

    @InjectMocks
    private UpdatedSettlementUseCase updatedSettlementUseCase;
    @Test
    void updateSettlement() {
        List<String> resources = new ArrayList<>();

        resources.add(ResourceEnum.WOOD.name());
        resources.add(ResourceEnum.BRICK.name());
        resources.add(ResourceEnum.SHEEP.name());
        resources.add(ResourceEnum.WHEAT.name());
        resources.add(ResourceEnum.ORE.name());

        List<Integer> resourceQuantity= new ArrayList<>();

        resourceQuantity.add(0);
        resourceQuantity.add(0);
        resourceQuantity.add(0);
        resourceQuantity.add(2);
        resourceQuantity.add(3);

        List<String> terrainsRelated = new ArrayList<>();

        terrainsRelated.add("terrain1");
        terrainsRelated.add("terrain2");
        terrainsRelated.add("terrain3");

        UpdateSettlementCommand updateSettlementCommand = new UpdateSettlementCommand(
                "playerIdTest",
                0,
                resources,
                resourceQuantity,
                "structureIdTest",
                    "SETTLEMENT",
                terrainsRelated
        );

        UpdatedSettlement updatedSettlementExpected = new UpdatedSettlement(
                "playerIdTest",
                0,
                resources,
                resourceQuantity,
                "structureIdTest",
                "SETTLEMENT",
                terrainsRelated
        );

        GeneratedPlayer generatedPlayer = new GeneratedPlayer(
                "playerIdTest",
                "playerNameBrian",
                0,
                resources,
                new ArrayList<>()
        );

        List<DomainEvent> domainEvents = new ArrayList<>();

        domainEvents.add(generatedPlayer);

        Mockito.when(eventsRepository.findAggregateRootId("playerIdTest")).thenReturn(domainEvents);


        List<DomainEvent> result = updatedSettlementUseCase.apply(updateSettlementCommand);

        UpdatedSettlement updatedSettlementResult =(UpdatedSettlement) result.get(0);
        assertEquals(updatedSettlementResult.getTerrainsRelated(), updatedSettlementExpected.getTerrainsRelated());
        assertEquals(updatedSettlementResult.getPlayerId(), updatedSettlementExpected.getPlayerId());
    }

    @Test
    void updateSettlementNotEnoughResources() {
        List<String> resources = new ArrayList<>();

        resources.add(ResourceEnum.WOOD.name());
        resources.add(ResourceEnum.BRICK.name());
        resources.add(ResourceEnum.SHEEP.name());
        resources.add(ResourceEnum.WHEAT.name());
        resources.add(ResourceEnum.ORE.name());

        List<Integer> resourceQuantity= new ArrayList<>();

        resourceQuantity.add(0);
        resourceQuantity.add(0);
        resourceQuantity.add(0);
        resourceQuantity.add(0);
        resourceQuantity.add(0);

        List<String> terrainsRelated = new ArrayList<>();

        terrainsRelated.add("terrain1");
        terrainsRelated.add("terrain2");
        terrainsRelated.add("terrain3");

        UpdateSettlementCommand updateSettlementCommand = new UpdateSettlementCommand(
                "playerIdTest",
                0,
                resources,
                resourceQuantity,
                "structureIdTest",
                "SETTLEMENT",
                terrainsRelated
        );

        UpdatedSettlement updatedSettlementExpected = new UpdatedSettlement(
                "playerIdTest",
                0,
                resources,
                resourceQuantity,
                "structureIdTest",
                "SETTLEMENT",
                terrainsRelated
        );

        GeneratedPlayer generatedPlayer = new GeneratedPlayer(
                "playerIdTest",
                "playerNameBrian",
                0,
                resources,
                new ArrayList<>()
        );

        List<DomainEvent> domainEvents = new ArrayList<>();

        domainEvents.add(generatedPlayer);

        Mockito.when(eventsRepository.findAggregateRootId("playerIdTest")).thenReturn(domainEvents);

        assertThrows((IllegalArgumentException.class), () -> updatedSettlementUseCase.apply(updateSettlementCommand));
    }

    @Test
    void updateSettlementResourceNotSupported() {
        List<String> resources = new ArrayList<>();

        resources.add("MAL");
        resources.add("MAL");
        resources.add("MAL");
        resources.add("MAL");
        resources.add("MAL");

        List<Integer> resourceQuantity= new ArrayList<>();

        resourceQuantity.add(0);
        resourceQuantity.add(0);
        resourceQuantity.add(0);
        resourceQuantity.add(2);
        resourceQuantity.add(3);

        List<String> terrainsRelated = new ArrayList<>();

        terrainsRelated.add("terrain1");
        terrainsRelated.add("terrain2");
        terrainsRelated.add("terrain3");

        UpdateSettlementCommand updateSettlementCommand = new UpdateSettlementCommand(
                "playerIdTest",
                0,
                resources,
                resourceQuantity,
                "structureIdTest",
                "SETTLEMENT",
                terrainsRelated
        );

        UpdatedSettlement updatedSettlementExpected = new UpdatedSettlement(
                "playerIdTest",
                0,
                resources,
                resourceQuantity,
                "structureIdTest",
                "SETTLEMENT",
                terrainsRelated
        );

        GeneratedPlayer generatedPlayer = new GeneratedPlayer(
                "playerIdTest",
                "playerNameBrian",
                0,
                resources,
                new ArrayList<>()
        );

        List<DomainEvent> domainEvents = new ArrayList<>();

        domainEvents.add(generatedPlayer);

        Mockito.when(eventsRepository.findAggregateRootId("playerIdTest")).thenReturn(domainEvents);

        assertThrows((IllegalArgumentException.class), () -> updatedSettlementUseCase.apply(updateSettlementCommand));
    }
}