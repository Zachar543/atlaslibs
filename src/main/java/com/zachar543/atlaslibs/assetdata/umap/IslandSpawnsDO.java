package com.zachar543.atlaslibs.assetdata.umap;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.LinkedHashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class IslandSpawnsDO {
	
	Set<CreatureDO> creatures = new LinkedHashSet<>();
	Set<ResourceDO> resources = new LinkedHashSet<>();
	
	@JsonIgnore
	public IslandSpawnsDO addAllFrom(IslandSpawnsDO islandSpawns) {
		creatures.addAll(islandSpawns.getCreatures());
		resources.addAll(islandSpawns.getResources());
		
		return this;
	}
	
}
