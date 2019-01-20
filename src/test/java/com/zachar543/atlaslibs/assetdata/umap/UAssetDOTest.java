package com.zachar543.atlaslibs.assetdata.umap;

import org.junit.Test;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.file.Files;
import java.nio.file.Paths;

public class UAssetDOTest {
	
	@Test
	public void testRead() throws Exception {
		UAssetDO uAssetDO = new UAssetDO();
		
		String path = "F:/_Games/steamapps/common/ATLAS/ShooterGame/Content/Atlas/AtlasCoreBP/HarvestComponents/07_Equator/StoneHarvestComponent_Coquina.uasset";
		ByteBuffer buffer = ByteBuffer.wrap(Files.readAllBytes(Paths.get(path))).order(ByteOrder.LITTLE_ENDIAN);
		uAssetDO.read(buffer);
		
		uAssetDO.getComponents();
	}
	
}