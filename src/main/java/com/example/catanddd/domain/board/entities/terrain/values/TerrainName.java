package com.example.catanddd.domain.board.entities.terrain.values;

import com.example.catanddd.domain.generic.ValueObject;

public class TerrainName implements ValueObject<TerrainEnum> {
    private final TerrainEnum terrainName;

    private TerrainName(TerrainEnum terrainName){
        if (terrainName != null && validateEnum(terrainName)){
            this.terrainName = terrainName;
        } else {
            throw new IllegalArgumentException("Terrain name Cannot be Null");
        }
    }
    public static TerrainName of(TerrainEnum terrainName){
        return new TerrainName(terrainName);
    }

    @Override
    public TerrainEnum value() {
        return terrainName;
    }


    private boolean validateEnum(TerrainEnum terrainName){
        return terrainName.equals(TerrainEnum.HILLS)|| terrainName.equals(TerrainEnum.FOREST)
                || terrainName.equals(TerrainEnum.MOUNTAINS) || terrainName.equals(TerrainEnum.FIELDS)
                || terrainName.equals(TerrainEnum.PASTURE)  || terrainName.equals(TerrainEnum.DESERT);

    }
}
