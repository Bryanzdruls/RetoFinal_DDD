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
            player.playerName = PlayerName.of(event.getPlayerName());

            if (event.getPoints() == null) {
                final Integer ZERO_POINT =0;
                player.points = Points.of(ZERO_POINT);
            }else {
                player.points = Points.of(event.getPoints());
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
        });

        apply((GeneratedStructure events) -> {
            player.structures.add(Structure.from(
                    StructureId.of(events.structureId()),
                    StructureType.of(StructureTypeEnum.valueOf(events.structureType())),
                    events.terrainsRelated()
            ));
        });
    }
}