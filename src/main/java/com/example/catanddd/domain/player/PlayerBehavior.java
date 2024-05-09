package com.example.catanddd.domain.player;

import com.example.catanddd.domain.board.entities.terrain.Terrain;
import com.example.catanddd.domain.board.entities.terrain.values.TerrainEnum;
import com.example.catanddd.domain.board.entities.terrain.values.TerrainId;
import com.example.catanddd.domain.board.entities.terrain.values.enums.ResourceEnum;
import com.example.catanddd.domain.generic.EventChange;
import com.example.catanddd.domain.player.entities.structure.Structure;
import com.example.catanddd.domain.player.entities.structure.values.StructureId;
import com.example.catanddd.domain.player.entities.structure.values.StructureType;
import com.example.catanddd.domain.player.entities.structure.values.enums.StructureTypeEnum;
import com.example.catanddd.domain.player.events.GeneratedPlayer;
import com.example.catanddd.domain.player.events.GeneratedStructure;
import com.example.catanddd.domain.player.events.structure.UpdatedSettlement;
import com.example.catanddd.domain.player.values.PlayerName;
import com.example.catanddd.domain.player.values.Points;
import com.example.catanddd.domain.player.values.Resource;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class PlayerBehavior extends EventChange {

    public PlayerBehavior(Player player) {
        apply((GeneratedPlayer event) -> {
          generatePlayer(player,event);
        });

        apply((GeneratedStructure events) -> {
          generateStructure(player,events);
        });

        apply((UpdatedSettlement event)->{
            updateSettlement(player,event);
        });
    }

    private void generatePlayer(Player player, GeneratedPlayer event){
        player.playerName = PlayerName.of(event.getPlayerName());

        if (event.getPoints() == null) {
            final Integer ZERO_POINT =0;
            player.points = Points.of(ZERO_POINT);
        }

        final Integer ZERO = 0;
        final List<String> RESOURCES_LIST = Arrays.asList(
                ResourceEnum.WOOD.toString(),
                ResourceEnum.BRICK.toString(),
                ResourceEnum.SHEEP.toString(),
                ResourceEnum.WHEAT.toString(),
                ResourceEnum.ORE.toString()
        );
        player.resources = new ArrayList<>();
        for (int i = 0; i < 5; i++){
            player.resources.add(Resource.of(ZERO, ResourceEnum.valueOf(RESOURCES_LIST.get(i))));
        }
        player.structures = new ArrayList<>();
    }
    private void generateStructure(Player player, GeneratedStructure events){
        Structure structure =Structure.from(
                StructureId.of(events.structureId()),
                StructureType.of(StructureTypeEnum.valueOf(events.structureType())),
                events.terrainsRelated()
        );
        player.structures.add(structure);

        if (structure.structureType().equals(StructureTypeEnum.SETTLEMENT.name())){
            final int ONE_POINT= 1;
            player.addPoints(ONE_POINT);
        }else if (structure.structureType().equals(StructureTypeEnum.CITY.name())){
            final int TWO_POINTS=  1;
            player.addPoints(TWO_POINTS);
        }
    }
    private void updateSettlement(Player player, UpdatedSettlement event) {
            player.points = Points.of(event.getPlayerPoints());


            List<TerrainId> terrainIdsList = new ArrayList<>();
            for (String terrainId:event.getTerrainsRelated()) {
                terrainIdsList.add(TerrainId.of(terrainId));
            }
            List<Resource> resources = new ArrayList<>();
            for (int i = 0; i < event.getResourcesType().size(); i++) {
                try {
                    ResourceEnum resourceType =ResourceEnum.valueOf(event.getResourcesType().get(i));
                    Integer resourceQuantity = event.getResourcesQuantity().get(i);
                    Resource resource = Resource.of(resourceQuantity, resourceType);
                    resources.add(resource);
                }catch (IllegalArgumentException e) {
                    throw new IllegalArgumentException("Resource Type " + event.getResourcesType().get(i)+ " is not supported");
                }
            }

            player.resources = resources;

            Structure structure = Structure.from(
                    StructureId.of(event.getStructureId()),
                    StructureType.of(StructureTypeEnum.valueOf(event.getStructureType())),
                    terrainIdsList
            );


            structure.updateSettlement(player);



            player.structures.add(structure);

    }
}