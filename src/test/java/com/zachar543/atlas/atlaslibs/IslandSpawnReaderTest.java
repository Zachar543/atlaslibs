package com.zachar543.atlas.atlaslibs;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zachar543.atlas.atlaslibs.umap.IslandSpawns;
import org.apache.commons.io.FileUtils;
import org.junit.Test;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.nio.file.Paths;
import java.util.Map;

public class IslandSpawnReaderTest {
	
	@Test
	public void readAll() throws Exception {
		File baseDir = new File("F:/_Games/steamapps/common/ATLAS/ShooterGame/Content/Maps/SeamlessTest");
		
		Map<String, IslandSpawns> result = new IslandSpawnReader().readIslandSpawns(baseDir);
		FileUtils.writeStringToFile(Paths.get("output", "output.json").toFile(), new ObjectMapper().writeValueAsString(result), StandardCharsets.UTF_8, false);
	}
	
}