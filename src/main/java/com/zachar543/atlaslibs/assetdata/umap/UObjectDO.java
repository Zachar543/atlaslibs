package com.zachar543.atlaslibs.assetdata.umap;

import java.nio.ByteBuffer;

public abstract class UObjectDO {
	protected String[] components;
	protected int componentsCount;
	protected int componentsOffset;

	public abstract void read(ByteBuffer buffer);
	
	protected void readHeader(ByteBuffer buffer) {
		// Skip 12 - UMap "Magic Number" - C1 83 2A 9E FD FF FF FF 60 03 00 00
		// Skip  8 - ??? - 98 01 00 00 0A 00 00 00
		buffer.position(20);
		
		int value = buffer.getInt();
		assert(value == 0 || value == 1);
		
		if (value == 0) {
			buffer.getInt();
		}
		else if (value == 1) {
			buffer.getInt();
			buffer.getInt();
			buffer.getInt();
			buffer.getInt();
			buffer.getInt();
			UEFormatUtils.readString(buffer); // "FoliageVer"
			buffer.getInt(); // Value of "FoliageVer"?
		}
		UEFormatUtils.readString(buffer); // "None"
		buffer.getInt(); // Always 00 00 02 80?
		
		componentsCount = buffer.getInt();
		componentsOffset = buffer.getInt();
	}
	
	protected void readComponents(ByteBuffer buffer) {
		buffer.position(componentsOffset);
		
		components = new String[componentsCount];
		for (int i = 0; i < componentsCount; i++) {
			components[i] = UEFormatUtils.readString(buffer);
		}
	}
	
	public String[] getComponents() {
		return components;
	}
	
}
