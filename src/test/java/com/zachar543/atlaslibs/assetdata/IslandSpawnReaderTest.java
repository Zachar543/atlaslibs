package com.zachar543.atlaslibs.assetdata;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zachar543.atlaslibs.assetdata.umap.IslandSpawnsDO;
import org.apache.commons.io.FileUtils;
import org.junit.Test;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.nio.file.Paths;
import java.util.Map;

public class IslandSpawnReaderTest {
	
	@Test
	public void readAll() throws Exception {
		String path = "F:/_Games/steamapps/common/ATLAS/ShooterGame/Content/";
		File baseDir = new File(path);
		
		Map<String, IslandSpawnsDO> result = new IslandSpawnReader().readIslandSpawns(baseDir);
		FileUtils.writeStringToFile(Paths.get("output", "output.json").toFile(), new ObjectMapper().writeValueAsString(result), StandardCharsets.UTF_8, false);
	}
	
}