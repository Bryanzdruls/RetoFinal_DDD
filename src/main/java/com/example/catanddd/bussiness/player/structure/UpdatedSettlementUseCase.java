package com.example.catanddd.bussiness.player.structure;

import com.example.catanddd.bussiness.generic.ICommandUseCase;
import com.example.catanddd.bussiness.generic.IEventsRepository;
import com.example.catanddd.domain.generic.DomainEvent;
import com.example.catanddd.domain.player.Player;
import com.example.catanddd.domain.player.commands.structure.UpdateSettlementCommand;

import java.util.List;

public class UpdatedSettlementUseCase implements ICommandUseCase<UpdateSettlementCommand> {

    private IEventsRepository eventsRepository;

    public UpdatedSettlementUseCase(IEventsRepository eventsRepository) {
        this.eventsRepository = eventsRepository;
    }
    @Override
    public List<DomainEvent> apply(UpdateSettlementCommand command) {
        List<DomainEvent> result = eventsRepository.findAggregateRootId(command.getPlayerId());
        Player player = Player.from(command.getPlayerId(), result);

        player.updateSettlement(
                command.getPlayerId(),
                command.getPlayerPoints(),
                command.getResourcesType(),
                command.getResourcesQuantity(),
                command.getStructureId(),
                command.getStructureType(),
                command.getTerrainsRelated()
        );

        List<DomainEvent> domainEvents =  player.getUncommittedChanges();
        domainEvents.stream().map(eventsRepository::save);
        player.markChangeAsCommitted();

        return domainEvents;
    }
}
