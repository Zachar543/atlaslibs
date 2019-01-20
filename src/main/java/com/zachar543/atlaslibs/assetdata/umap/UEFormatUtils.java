package com.zachar543.atlaslibs.assetdata.umap;

import java.nio.ByteBuffer;

public class UEFormatUtils {
	
	private UEFormatUtils() { }
	
	public static String readString(ByteBuffer buffer) {
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
