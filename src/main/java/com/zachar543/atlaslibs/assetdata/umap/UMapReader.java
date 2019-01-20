package com.zachar543.atlaslibs.assetdata.umap;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.file.Files;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class UMapReader {
	private Pattern materialPattern = Pattern.compile("^/Game/Atlas/AtlasCoreBP/HarvestComponents/.+/(?<type>.+)HarvestComponent_(?<variant>.+)$");
	private Pattern creaturePattern = Pattern.compile("^/Game/Atlas/Creatures/.+/(?<name>.+)_Character_BP$");
	
	public IslandSpawnsDO readMaterialsFromUMapFile(File baseDir, File file) throws IOException {
		ByteBuffer buffer = ByteBuffer.wrap(Files.readAllBytes(file.toPath())).order(ByteOrder.LITTLE_ENDIAN);
		
		UMapDO uMapDO = new UMapDO();
		uMapDO.read(buffer);
		
		List<String> components = Arrays.asList(uMapDO.getComponents());
		
		Set<ResourceDO> resources = components.stream()
				.map(str -> materialPattern.matcher(str))
				.filter(Matcher::matches)
				.map(m -> CachedUAssetLoader.load(baseDir, m.group(0)))
				.filter(u -> u != null)
				.flatMap(u -> u.getItems().stream())
				.collect(Collectors.toSet());

		Set<CreatureDO> creatures = components.stream()
				.map(str -> creaturePattern.matcher(str))
				.filter(Matcher::matches)
				.map(m -> new CreatureDO(m.group("name")))
				.collect(Collectors.toSet());
		
		return new IslandSpawnsDO(creatures, resources);
	}
	
}
