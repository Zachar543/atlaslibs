package com.zachar543.atlas.atlaslibs.umap;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.LinkedHashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class IslandSpawns {
	
	Set<Creature> creatures = new LinkedHashSet<>();
	Set<Resource> resources = new LinkedHashSet<>();
	
	@JsonIgnore
	public IslandSpawns addAllFrom(IslandSpawns islandSpawns) {
		creatures.addAll(islandSpawns.getCreatures());
		resources.addAll(islandSpawns.getResources());
		
		return this;
	}
	
}
