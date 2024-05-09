package com.example.catanddd.domain.board.entities.knight;

import com.example.catanddd.domain.board.entities.knight.values.KnightId;
import com.example.catanddd.domain.board.entities.terrain.values.enums.ResourceEnum;
import com.example.catanddd.domain.player.Player;
import com.example.catanddd.domain.board.entities.terrain.Terrain;
import com.example.catanddd.domain.generic.Entity;
import com.example.catanddd.domain.player.entities.structure.Structure;
import com.example.catanddd.domain.player.entities.structure.values.StructureId;
import com.example.catanddd.domain.player.values.PlayerId;
import com.example.catanddd.domain.player.values.Resource;

public class Knight extends Entity<KnightId> {

    private Terrain terrainLocated;

    public Knight(KnightId id) {
        super(id);
    }

    private Knight(KnightId id, Terrain terrain) {
        super(id);
        this.terrainLocated = terrain;
    }

    public static Knight from(KnightId id, Terrain terrain) {
        return new Knight(id,terrain);
    }

    public Terrain terrainLocated() {
        return this.terrainLocated;
    }

    public Terrain moveTo(Terrain terrain) {
        if (terrainLocated == null) {
            terrain.setKnight(this);
            this.terrainLocated =terrain;
            return this.terrainLocated;
        }
        if (!terrainLocated().isKnightHere()) {
            terrain.setKnight(this);
            this.terrainLocated = terrain;
        }
        return this.terrainLocated;
    }

   /* public void stealPlayer(Terrain terrain, Structure structure, Player player) {
        if (terrain.isKnightHere()) {
            if (player.structures().contains(structure)){
                int countResources = 0;
                final String QUANTITY = "QUANTITY";
                final String NAME = "NAME";
                for (Resource resource:player.resources()){
                    countResources = countResources + (int) resource.value().get(QUANTITY);

                    if (countResources> 7 ){
                        final Integer MINUS_ONE = 1;
                        Resource newResource = Resource.of((Integer) resource.value().get(QUANTITY) - MINUS_ONE,(ResourceEnum) resource.value().get(NAME) );
                        player.resources().add(newResource);
                        player.resources().remove(resource);
                    }
                }
            }
        }
    }*/
}
