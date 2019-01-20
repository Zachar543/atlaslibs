package com.zachar543.atlaslibs.redis;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TerritoryClaimDO {
	
	public static final BigDecimal MAX_COORD = new BigDecimal(Character.MAX_VALUE);
	
	public static TerritoryClaimDO fromRedis(Long serverId, String entry) {
		ByteBuffer buffer = ByteBuffer.wrap(entry.getBytes()).order(ByteOrder.LITTLE_ENDIAN);
		Long tribeId = buffer.getLong();
		char rawRelativeX = (char) buffer.getShort();
		BigDecimal relativeX = new BigDecimal(rawRelativeX).setScale(8, RoundingMode.HALF_UP).divide(MAX_COORD, RoundingMode.HALF_UP);
		char rawRelativeY = (char) buffer.getShort();
		BigDecimal relativeY = new BigDecimal(rawRelativeY).setScale(8, RoundingMode.HALF_UP).divide(MAX_COORD, RoundingMode.HALF_UP);
		Integer type = (int) buffer.getChar();
		
		return new TerritoryClaimDO(serverId, tribeId, relativeX, relativeY, type);
	}
	
	/*
	Format Details:
	- One entry in "territorymapdata" per cell, Redis set contains one entry per claim flag
    - 16 total bytes (little-endian)
    - Data:
      - 8 bytes (64bit int) => tribeId
      - 2 bytes (16bit int) => relativeX (cell relative; 0 = min cell coord, 65535 = max cell coord)
      - 2 bytes (16bit int) => relativeY (cell relative; 0 = min cell coord, 65535 = max cell coord)
      - 1 byte (8bit int) => type (0 = On Land, 1 = On Water)
      - 1 byte => unused?
      - 1 byte => unused?
      - 1 byte => unused?
    */
	
	private Long serverId;

	private Long tribeId;
	private BigDecimal relativeX; // mapped from 0-65535 to 0.0-1.0
	private BigDecimal relativeY; // mapped from 0-65535 to 0.0-1.0
	private Integer type;

}
