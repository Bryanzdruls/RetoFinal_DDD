package com.example.catanddd.domain.board.entities.terrain.values;

import com.example.catanddd.domain.generic.ValueObject;

public class TerrainNumber implements ValueObject<Integer> {
    private final Integer terrainNumber;

    private TerrainNumber(Integer terrainNumber){
        if (terrainNumber != null && terrainNumber >= 0 && terrainNumber <=12 && terrainNumber !=1) {
            this.terrainNumber = terrainNumber;
        } else {
            throw new IllegalArgumentException("Terrain Number has to be between 2 and 12 only 0 for Desert");
        }
    }
    public static TerrainNumber of(Integer terrainNumber){
        return new TerrainNumber(terrainNumber);
    }
    @Override
    public Integer value() {
        return terrainNumber;
    }
}
