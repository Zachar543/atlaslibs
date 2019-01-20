package com.zachar543.atlaslibs.assetdata.umap;

import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.Assert.*;

public class UMapDOTest {
	
	@Test
	public void read() throws Exception {
		UMapDO uMapDO = new UMapDO();
		
		String path = "F:/_Games/steamapps/common/ATLAS/ShooterGame/Content/Maps/SeamlessTest/Mnt/Mnt_X_Farthest_PVE.umap";
		ByteBuffer buffer = ByteBuffer.wrap(Files.readAllBytes(Paths.get(path))).order(ByteOrder.LITTLE_ENDIAN);
		uMapDO.read(buffer);
		
	}
	
	
}