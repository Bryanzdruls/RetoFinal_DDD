package com.example.catanddd.domain.player.entities.structure;

import com.example.catanddd.domain.board.entities.terrain.values.TerrainId;
import com.example.catanddd.domain.board.entities.terrain.values.enums.ResourceEnum;
import com.example.catanddd.domain.generic.Entity;
import com.example.catanddd.domain.player.Player;
import com.example.catanddd.domain.player.entities.structure.values.StructureId;
import com.example.catanddd.domain.player.entities.structure.values.StructureType;
import com.example.catanddd.domain.player.entities.structure.values.enums.StructureTypeEnum;
import com.example.catanddd.domain.player.values.Resource;

import java.util.ArrayList;
import java.util.List;

public class Structure extends Entity<StructureId> {
    private StructureType structureType;

    private List<TerrainId> terrainsRelated;

    public Structure(StructureId id) {
        super(id);
    }

    private Structure(StructureId structureId, StructureType structureType, List<TerrainId> terrainsRelated) {
        super(structureId);
        this.structureType = structureType;
        this.terrainsRelated = terrainsRelated;
    }

    public static Structure from(StructureId structureId, StructureType structureType, List<TerrainId> terrainsRelated){
        return new Structure(structureId, structureType, terrainsRelated);
    }

    public String structureType() {
        return structureType.value().toString();
    }
    public List<TerrainId> terrainsRelated() {
        return terrainsRelated;
    }


    public void updateSettlement(Player player){
        List<Resource> resourcesNecessaryToUpgradeSettlementToCity = new ArrayList<>();

        resourcesNecessaryToUpgradeSettlementToCity.add(Resource.of(2,ResourceEnum.WHEAT));
        resourcesNecessaryToUpgradeSettlementToCity.add(Resource.of(3,ResourceEnum.ORE));

        final String QUANTITY = "QUANTITY";
        final String NAME = "NAME";

        if(this.structureType.value().equals(StructureTypeEnum.SETTLEMENT)){
            for (Resource resource : resourcesNecessaryToUpgradeSettlementToCity) {
                int cantidadNecesaria =(Integer) resource.value().get(QUANTITY);
                ResourceEnum tipoRecurso =(ResourceEnum) resource.value().get(NAME);


                boolean hasEnough = false;
                for (Resource playerResourceFor : player.resources()) {
                    ResourceEnum playerResource = (ResourceEnum) playerResourceFor.value().get(NAME);
                    int playerResourceQuantity = (Integer) playerResourceFor.value().get(QUANTITY);
                    if (playerResource == tipoRecurso && playerResourceQuantity >= cantidadNecesaria) {
                        hasEnough = true;
                        this.structureType = StructureType.of(StructureTypeEnum.CITY);
                        break;
                    }
                }

                if (!hasEnough) {
                    throw new IllegalArgumentException("Is not possible to update to city without enough resources");
                }
            }
        }
    }
}

