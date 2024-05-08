package com.example.catanddd.domain.board.entities.corner;

import com.example.catanddd.domain.board.entities.corner.values.CornerId;
import com.example.catanddd.domain.board.entities.corner.values.IsCornerAvailable;
import com.example.catanddd.domain.board.entities.corner.values.Structure;
import com.example.catanddd.domain.board.entities.terrain.Terrain;
import com.example.catanddd.domain.board.entities.terrain.values.Resource;
import com.example.catanddd.domain.board.entities.terrain.values.TerrainId;
import com.example.catanddd.domain.board.entities.terrain.values.TerrainNumber;
import com.example.catanddd.domain.board.entities.terrain.values.enums.ResourceEnum;
import com.example.catanddd.domain.generic.Entity;
import com.example.catanddd.domain.generic.Identity;
import com.example.catanddd.domain.player.Player;
import com.example.catanddd.domain.player.entities.structure.values.enums.StructureTypeEnum;

import java.util.ArrayList;
import java.util.List;

public class Corner extends Entity<CornerId> {

    private IsCornerAvailable isCornerAvailable;

    private Structure structure;

    private List<Terrain> terrainsInContact;

    public Corner(CornerId id) {
        super(id);
    }

    private Corner(CornerId id, IsCornerAvailable isCornerAvailable, Structure structure, List<Terrain> terrainsInContact){
        super(id);
        this.isCornerAvailable = isCornerAvailable;
        this.structure = structure;
        this.terrainsInContact = terrainsInContact;
    }

    private Corner(CornerId id, IsCornerAvailable isCornerAvailable){
        super(id);
        this.isCornerAvailable = isCornerAvailable;
        this.structure = null;
        this.terrainsInContact = new ArrayList<>();
    }

    public static Corner from(IsCornerAvailable isCornerAvailable, Structure structure, List<Terrain> terrainsInContact){
        return new Corner(new CornerId(), isCornerAvailable, structure, terrainsInContact);
    }

    public static Corner from(CornerId id, IsCornerAvailable isCornerAvailable){
        return new Corner(id, isCornerAvailable);
    }

    public IsCornerAvailable isCornerAvailable(){
        return isCornerAvailable;
    }

    public Structure structure(){
        return structure;
    }

    public List<Terrain> terrainsInContact(){
        return terrainsInContact;
    }

    public Structure putStructure(Structure structure){
        if (!isCornerAvailable.value()) {
            return null;
        }
        if(this.structure == null){
            this.structure = structure;
        }else{
            if (this.structure.value().equals(StructureTypeEnum.SETTLEMENT) && structure.value().equals(StructureTypeEnum.CITY)){
                this.structure = structure;
                this.isCornerAvailable = IsCornerAvailable.of(false);
            }
        }
        return this.structure;
    }

    public List<Resource> addResourceToPlayer(Player player){
        List<Resource> resourcesToPlayer =new ArrayList<>();
        final String PLAYER_ID = "PLAYER_ID";
        final String STRUCTURE_TYPE = "STRUCTURE_TYPE";
        if (!player.identity().equals(this.structure.value().get(PLAYER_ID))){
            return null;
        }

        if (this.structure.value().get(STRUCTURE_TYPE).equals(StructureTypeEnum.SETTLEMENT)){

            final Integer ONE_RESOURCE = 1;

            for (Terrain terrain : this.terrainsInContact()){
                try{
                    resourcesToPlayer.add(Resource.of(
                            ONE_RESOURCE,
                            ResourceEnum.valueOf(terrain.resourceName())
                    ));
                }catch (IllegalArgumentException exception){
                    throw new IllegalArgumentException("ResourceName don't be part of the enum");
                }
            }
            return resourcesToPlayer;
        }else if(this.structure.value().get(STRUCTURE_TYPE).equals(StructureTypeEnum.CITY)){
            final Integer TWO_RESOURCE = 2;
            for (Terrain terrain : this.terrainsInContact()){
                try{
                    resourcesToPlayer.add(Resource.of(
                            TWO_RESOURCE,
                            ResourceEnum.valueOf(terrain.resourceName())
                    ));
                }catch (IllegalArgumentException exception){
                    throw new IllegalArgumentException("ResourceName don't be part of the enum");
                }
            }
        }
        return resourcesToPlayer;
    }
}
