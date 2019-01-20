package com.zachar543.atlaslibs.assetdata.umap;

import java.nio.ByteBuffer;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class UAssetDO extends UObjectDO {
	private static Pattern itemPattern = Pattern.compile("^/Game/Atlas/AtlasCoreBP/Items/(Resources|Consumables)/PrimalItem(Resource|Consumable)_(?<type>[a-zA-Z]+)_(?<variant>[a-zA-Z_]+)$", Pattern.CASE_INSENSITIVE);
	
	private List<ResourceDO> resources = new LinkedList<>();
	
	@Override
	public void read(ByteBuffer buffer) {
		readHeader(buffer);
		readComponents(buffer);
	}
	
	@Override
	protected void readComponents(ByteBuffer buffer) {
		super.readComponents(buffer);
		
		resources = Arrays.stream(components)
				.map(str -> itemPattern.matcher(str))
				.filter(Matcher::matches)
				.map(m -> new ResourceDO(m.group("type"), m.group("variant")))
				.collect(Collectors.toList());
	}
	
	public List<ResourceDO> getItems() {
		return resources;
	}
}
