package com.example.catanddd.domain.player.entities.structure.values;

import com.example.catanddd.domain.board.entities.terrain.values.enums.ResourceEnum;
import com.example.catanddd.domain.generic.ValueObject;
import com.example.catanddd.domain.player.entities.structure.values.enums.StructureTypeEnum;

public class StructureType implements ValueObject<StructureTypeEnum> {
    private final StructureTypeEnum structureTypeEnum;

    private StructureType(StructureTypeEnum structureTypeEnum){
        if (structureTypeEnum != null && validateEnum(structureTypeEnum)) {
            this.structureTypeEnum = structureTypeEnum;
        } else {
            throw new IllegalArgumentException("StructureType is not valid");
        }
    }
    public static StructureType of(StructureTypeEnum playerName){
        return new StructureType(playerName);
    }
    @Override
    public StructureTypeEnum value() {
        return structureTypeEnum;
    }

    private boolean validateEnum(StructureTypeEnum name){
        return name.equals(StructureTypeEnum.CITY) ||
                name.equals(StructureTypeEnum.ROAD) ||
                name.equals(StructureTypeEnum.SETTLEMENT);
    }
}
