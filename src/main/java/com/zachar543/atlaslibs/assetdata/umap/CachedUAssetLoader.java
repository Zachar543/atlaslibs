package com.zachar543.atlaslibs.assetdata.umap;

import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.Map;

public class CachedUAssetLoader {
	private static Map<String, UAssetDO> cache = new HashMap<>();
	
	public static UAssetDO load(File baseDir, String blueprintPath) {
		if (cache.containsKey(blueprintPath)) {
			return cache.get(blueprintPath);
		}
		
		String path = blueprintPath.replaceFirst("^/Game/", "") + ".uasset";
		try {
			ByteBuffer buffer = ByteBuffer.wrap(Files.readAllBytes(baseDir.toPath().resolve(path))).order(ByteOrder.LITTLE_ENDIAN);
			UAssetDO uAssetDO = new UAssetDO();
			uAssetDO.read(buffer);
			cache.put(blueprintPath, uAssetDO);
			
			return uAssetDO;
		}
		catch (IOException e) {
			return null;
		}
	}
	
	private CachedUAssetLoader() { }
	
}
