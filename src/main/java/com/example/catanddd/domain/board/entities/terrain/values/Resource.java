package com.example.catanddd.domain.board.entities.terrain.values;

import com.example.catanddd.domain.board.entities.terrain.values.enums.ResourceEnum;
import com.example.catanddd.domain.generic.ValueObject;

import java.util.Map;

public class Resource implements ValueObject<Map<String,?>> {
    private final Integer quantity;
    private final ResourceEnum name;

    private Resource(Integer quantity, ResourceEnum name){
        if (quantity != null && quantity >=0 && validateEnum(name)) {
            this.quantity = quantity;
            this.name = name;
        } else {
            throw new IllegalArgumentException("Quantity must be more than zero and resource name must be one of those [WOOD,BRICK,SHEEP, WHEAT, ORE]" );
        }
    }
    public static Resource of(Integer terrainNumber,  ResourceEnum name){
        return new Resource(terrainNumber, name);
    }

    @Override
    public Map<String,?> value() {
        final String QUANTITY = "QUANTITY";
        final String NAME = "NAME";
        return Map.of(
                QUANTITY, this.quantity,
                NAME, this.name
        );
    }

    private boolean validateEnum(ResourceEnum name){
        return name.equals(ResourceEnum.BRICK) ||
                name.equals(ResourceEnum.WOOD) ||
                name.equals(ResourceEnum.SHEEP) ||
                name.equals(ResourceEnum.WHEAT) ||
                name.equals(ResourceEnum.ORE);
    }
}
