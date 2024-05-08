package com.example.catanddd.domain.board.entities.corner.values;

import com.example.catanddd.domain.board.entities.terrain.values.TerrainEnum;
import com.example.catanddd.domain.board.entities.terrain.values.TerrainName;
import com.example.catanddd.domain.generic.ValueObject;
import com.example.catanddd.domain.player.entities.structure.values.enums.StructureTypeEnum;
import com.example.catanddd.domain.player.values.PlayerId;

import java.util.Map;

public class Structure implements ValueObject<Map<String,?>> {
    private final StructureTypeEnum structureType;

    private final PlayerId playerId;

    private Structure(StructureTypeEnum structureType, PlayerId playerId) {
        if (structureType != null && validateEnum(structureType) && playerId != null){
            this.structureType = structureType;
            this.playerId = playerId;
        } else {
            throw new IllegalArgumentException("Structure type Cannot be Null");
        }
    }
    public static Structure of(StructureTypeEnum structureType, PlayerId playerId){
        return new Structure(structureType, playerId);
    }

    @Override
    public Map<String,?> value() {
        final String STRUCTURE_TYPE = "STRUCTURE_TYPE";
        final String PLAYER_ID = "PLAYER_ID";

        return Map.of(
                STRUCTURE_TYPE, structureType,
                PLAYER_ID, playerId
        );
    }


    private boolean validateEnum(StructureTypeEnum structureType){
        return structureType.equals(StructureTypeEnum.ROAD)|| structureType.equals(StructureTypeEnum.SETTLEMENT)
                || structureType.equals(StructureTypeEnum.CITY);

    }
}