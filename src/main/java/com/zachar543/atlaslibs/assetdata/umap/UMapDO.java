package com.zachar543.atlaslibs.assetdata.umap;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.nio.ByteBuffer;
import java.util.LinkedList;
import java.util.List;

public class UMapDO extends UObjectDO {

	private int section1End;
	private int section1Count;
	private int section1Start;
	// private List<Section1> section1Parts;
	
	private long section2Start;
	private long section3Start;
	
	@Override
	public void read(ByteBuffer buffer) {
		readHeader(buffer);
		readComponents(buffer);
	}
	
	@Override
	protected void readHeader(ByteBuffer buffer) {
		super.readHeader(buffer);
		
		buffer.getInt();
		section1End = buffer.getInt();
		section1Count = buffer.getInt();
		section1Start = buffer.getInt();
		section2Start = buffer.getLong();
		section3Start = buffer.getLong();
	}
	
	@Override
	protected void readComponents(ByteBuffer buffer) {
		super.readComponents(buffer);
	}
	
	/*
	private void readSection1(ByteBuffer buffer) {
		buffer.position(section1Start);
		
		section1Parts = new LinkedList<>();
		for (int i = 0; i < section1Count; i++) {
			long a = buffer.getLong(); // 8 bytes
			long b = buffer.getLong(); // 8 bytes
			int c = buffer.getInt(); // 4 bytes
			long d = buffer.getLong(); // 8 bytes
			
			section1Parts.add(new Section1(a, b, c, d));
		}
	}
	private void readSection2(ByteBuffer buffer) {
		buffer.position((int) section2Start);
		
		for (int i = 0; i < section1Count; i++) {
			long a = buffer.getLong(); // 8 bytes
			long b = buffer.getLong(); // 8 bytes
			int c = buffer.getInt(); // 4 bytes
			long d = buffer.getLong(); // 8 bytes
			
			section1Parts.add(new Section1(a, b, c, d));
		}
	}
	
	@Data
	@NoArgsConstructor
	@AllArgsConstructor
	private class Section1 {
		private Long a;
		private Long b;
		private Integer c;
		private Long d;
	}
	*/
	
}
