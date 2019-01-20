package com.zachar543.atlas.atlaslibs;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zachar543.atlas.atlaslibs.umap.IslandSpawns;
import com.zachar543.atlas.atlaslibs.umap.UMapReader;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class IslandSpawnReader {
	private UMapReader uMapReader = new UMapReader();
	
	public Map<String, IslandSpawns> readIslandSpawns(File baseDir) throws Exception {
		Map<String, IslandSpawns> result = new HashMap<>();

		HashMap<String, IslandData> islandData = loadIslandData();
		for (Map.Entry<String, IslandData> islandDataEntry : islandData.entrySet()) {
			String islandName = islandDataEntry.getKey();
			IslandData island = islandDataEntry.getValue();
			
			result.put(islandName, processIsland(baseDir, islandName, island));
		}
		
		return result;
	}
	
	private IslandSpawns processIsland(File baseDir, String islandName, IslandData island) throws IOException {
		System.out.println(" - Processing Island \"" + islandName + "\"...");
		
		IslandSpawns result = new IslandSpawns();
		for (String subLevelName : island.getSublevelNames()) {
			System.out.println("   - Processing subLevel \"" + subLevelName + "\"...");
			File subLevelFolder = new File(baseDir, findSubLevelFolder(subLevelName));
			File subLevelFile = new File(subLevelFolder, subLevelName + ".umap");
			result.addAllFrom(uMapReader.readMaterialsFromUMapFile(subLevelFile));
		}
		
		return result;
	}
	
	private HashMap<String, IslandData> loadIslandData() throws IOException {
		ObjectMapper mapper = new ObjectMapper();
		TypeReference<HashMap<String, IslandData>> typeRef = new TypeReference<HashMap<String, IslandData>>() {};
		System.out.println(" - Loading islands.json...");
		
		return mapper.readValue(new File("src/main/resources/islands.json"), typeRef);
	}
	
	private String findSubLevelFolder(String subLevelName) {
		String prefix = subLevelName.substring(0, subLevelName.indexOf('_'));
		if (prefix.equalsIgnoreCase("cay")) {
			return "Cays";
		}
		else if (prefix.equalsIgnoreCase("ice")) {
			return "Iceberg";
		}
		
		return prefix;
	}

}
