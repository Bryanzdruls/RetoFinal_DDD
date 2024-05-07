package com.example.catanddd.domain.board.entities.terrain;

import com.example.catanddd.domain.board.entities.knight.Knight;
import com.example.catanddd.domain.player.Player;
import com.example.catanddd.domain.board.entities.terrain.values.*;
import com.example.catanddd.domain.board.entities.terrain.values.enums.ResourceEnum;
import com.example.catanddd.domain.generic.Entity;
import com.example.catanddd.domain.player.entities.structure.values.StructureId;

import java.util.ArrayList;
import java.util.List;

public class Terrain extends Entity<TerrainId> {

    private TerrainName terrainName;

    private TerrainNumber TerrainNumber;

    private Knight knight;

    private Resource resource;

    private List<StructureId> structures;

    public Terrain(TerrainId id) {
        super(id);
    }

    private Terrain(TerrainId terrainId, TerrainNumber terrainNumber, TerrainName terrainName) {
        super(terrainId);
        this.TerrainNumber = terrainNumber;
        this.knight = null;
        this.terrainName = terrainName;
        this.resource = generateResource(terrainName);
        this.structures = new ArrayList<>();
    }

    public static Terrain from(TerrainId terrainId, TerrainNumber terrainNumber, TerrainName terrainName){
        return new Terrain(terrainId, terrainNumber, terrainName);
    }

    public TerrainEnum terrainType (){return terrainName.value();}

    public Integer terrainNumber(){
        return this.TerrainNumber.value();
    }

    public Knight knight(){
        return this.knight;
    }

    public String resourceName() {
        final String NAME = "NAME";
        return this.resource.value().get(NAME).toString();
    }

    public Integer resourceQuantity(){
        final String QUANTITY = "QUANTITY";
        return (Integer) this.resource.value().get(QUANTITY);
    }

    public List<StructureId> structures(){
        return this.structures;
    }

    public void setKnight(Knight knight){
        this.knight = knight;
    }

    public boolean isKnightHere(){
        return this.knight() != null;
    }

    private Resource generateResource(TerrainName terrainName){
        final Integer ZERO = 0;

        if(terrainName.value().equals(TerrainEnum.DESERT)){
            return null;
        }

        if (validateEnum(terrainName.value())){
            throw new IllegalStateException("Isn't a valid Terrain "+terrainName.value());
        }
        TerrainEnum terrainNameVerified = terrainName.value();
        ResourceEnum resultToCreate = verifyTerrainCase(terrainNameVerified);

        if (resultToCreate == null) {
            throw new IllegalStateException("Error creating the resource");
        }

        return Resource.of(ZERO, resultToCreate);
    }

    //public Player addResourceToPlayer(){}

    private boolean validateEnum(TerrainEnum terrainName){
        return terrainName.equals(TerrainEnum.HILLS)|| terrainName.equals(TerrainEnum.FOREST)
                || terrainName.equals(TerrainEnum.MOUNTAINS) || terrainName.equals(TerrainEnum.FIELDS)
                || terrainName.equals(TerrainEnum.PASTURE);

    }

    private ResourceEnum verifyTerrainCase( TerrainEnum terrainNameVerified){
        ResourceEnum resourceEnum = null;
        switch (terrainNameVerified){
            case FOREST -> {
                resourceEnum = ResourceEnum.WOOD;
            }
            case HILLS -> {
                resourceEnum = ResourceEnum.BRICK;
            }
            case PASTURE -> {
                resourceEnum = ResourceEnum.SHEEP;
            }
            case FIELDS -> {
                resourceEnum = ResourceEnum.WHEAT;
            }
            case MOUNTAINS -> {
                resourceEnum = ResourceEnum.ORE;
            }
        }
        return resourceEnum;
    }
}
