package com.zachar543.atlas.atlaslibs.umap;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.file.Files;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class UMapReader {
	
	// private Pattern materialComponentPattern = Pattern.compile("^/Game/Atlas/AtlasCoreBP/HarvestComponents/.+?/.+$");
	// private Pattern materialExtractPattern = Pattern.compile("^(?<baseMat>.+)HarvestComponent_(?<subMat>.+)$");
	
	private Pattern materialPattern = Pattern.compile("^/Game/Atlas/AtlasCoreBP/HarvestComponents/.+/(?<type>.+)HarvestComponent_(?<variant>.+)$");
	private Pattern creaturePattern = Pattern.compile("^/Game/Atlas/Creatures/.+/(?<name>.+)_Character_BP$");
	
	public IslandSpawns readMaterialsFromUMapFile(File file) throws IOException {
		ByteBuffer buffer = ByteBuffer.wrap(Files.readAllBytes(file.toPath())).order(ByteOrder.LITTLE_ENDIAN);
		
		readHeader(buffer);
		List<String> components = readComponents(buffer);
		
		Set<Resource> resources = components.stream()
				.map(str -> materialPattern.matcher(str))
				.filter(Matcher::matches)
				.map(m -> new Resource(m.group("type"), m.group("variant")))
				.collect(Collectors.toSet());
		
		Set<Creature> creatures = components.stream()
				.map(str -> creaturePattern.matcher(str))
				.filter(Matcher::matches)
				.map(m -> new Creature(m.group("name")))
				.collect(Collectors.toSet());
		
		return new IslandSpawns(creatures, resources);
	}
	
	private void readHeader(ByteBuffer buffer) {
		// Skip 12 - UMap "Magic Number" - C1 83 2A 9E FD FF FF FF 60 03 00 00
		// Skip  8 - ??? - 98 01 00 00 0A 00 00 00
		buffer.position(20);
		
		int value1 = buffer.getInt();
		if (value1 == 0) {
			int int1 = buffer.getInt();
			String string1 = readString(buffer); // "None"
			int int2 = buffer.getInt(); // Always 00 00 02 80?
		}
		else if (value1 == 1) {
			int int1 = buffer.getInt();
			int int2 = buffer.getInt();
			int int3 = buffer.getInt();
			int int4 = buffer.getInt();
			int int5 = buffer.getInt();
			String string1 = readString(buffer); // "FoliageVer"
			int int6 = buffer.getInt(); // Value of "FoliageVer"?
			String string2 = readString(buffer); // "None"
			int int7 = buffer.getInt(); // Always 00 00 02 80?
		}
		else {
			assert(false);
		}
	}
	private List<String> readComponents(ByteBuffer buffer) {
		int componentCount = buffer.getInt();
		int componentListOffset = buffer.getInt();
		buffer.position(componentListOffset);
		
		List<String> components = new LinkedList<>();
		for (int i = 0; i < componentCount; i++) {
			components.add(readString(buffer));
		}
		
		return components;
	}
	
	private String readString(ByteBuffer buffer) {
		int length = buffer.getInt() - 1;
		assert(length < 512);
		
		byte[] bytes = new byte[length];
		for (int i = 0; i < length; i++) {
			bytes[i] = buffer.get();
		}
		
		String string = new String(bytes);
		assert(buffer.get() == '\0');
		
		return string;
	}
	
}
