package com.example.catanddd.domain.board.entities.knight;

import com.example.catanddd.domain.board.entities.knight.values.KnightId;
import com.example.catanddd.domain.player.Player;
import com.example.catanddd.domain.board.entities.terrain.Terrain;
import com.example.catanddd.domain.generic.Entity;
import com.example.catanddd.domain.player.entities.structure.Structure;
import com.example.catanddd.domain.player.entities.structure.values.StructureId;
import com.example.catanddd.domain.player.values.PlayerId;

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

    //public boolean stealPlayer(StructureId structureId, PlayerId playerId) {}
}
