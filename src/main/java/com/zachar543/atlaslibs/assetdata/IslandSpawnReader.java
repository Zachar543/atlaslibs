package com.zachar543.atlaslibs.assetdata;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zachar543.atlaslibs.assetdata.umap.IslandSpawnsDO;
import com.zachar543.atlaslibs.assetdata.umap.UMapReader;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class IslandSpawnReader {
	private UMapReader uMapReader = new UMapReader();
	
	public Map<String, IslandSpawnsDO> readIslandSpawns(File baseDir) throws Exception {
		Map<String, IslandSpawnsDO> result = new HashMap<>();

		HashMap<String, IslandDataDO> islandData = loadIslandData();
		for (Map.Entry<String, IslandDataDO> islandDataEntry : islandData.entrySet()) {
			String islandName = islandDataEntry.getKey();
			IslandDataDO island = islandDataEntry.getValue();
			
			result.put(islandName, processIsland(baseDir, islandName, island));
		}
		
		return result;
	}
	
	private IslandSpawnsDO processIsland(File baseDir, String islandName, IslandDataDO island) throws IOException {
		System.out.println(" - Processing Island \"" + islandName + "\"...");
		
		IslandSpawnsDO result = new IslandSpawnsDO();
		for (String subLevelName : island.getSublevelNames()) {
			System.out.println("   - Processing subLevel \"" + subLevelName + "\"...");
			File subLevelFolder = new File(baseDir, "Maps/SeamlessTest/" + findSubLevelFolder(subLevelName));
			File subLevelFile = new File(subLevelFolder, subLevelName + ".umap");
			result.addAllFrom(uMapReader.readMaterialsFromUMapFile(baseDir, subLevelFile));
		}
		
		return result;
	}
	
	private HashMap<String, IslandDataDO> loadIslandData() throws IOException {
		ObjectMapper mapper = new ObjectMapper();
		TypeReference<HashMap<String, IslandDataDO>> typeRef = new TypeReference<HashMap<String, IslandDataDO>>() {};
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
