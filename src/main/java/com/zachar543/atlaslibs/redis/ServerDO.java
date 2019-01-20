package com.zachar543.atlaslibs.redis;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;
import java.util.Map;

@Data
@AllArgsConstructor
public class ServerDO {
	
	public static ServerDO fromRedis(Map<String, String> data) {
		Long atlasId = Long.parseUnsignedLong(data.get("AtlasId"));
		Long serverId = Long.parseUnsignedLong(data.get("ServerId"));
		String ip = data.get("Ip");
		Integer port = Integer.parseUnsignedInt(data.get("Port"));
		String gameIp = data.get("GameIp");
		Integer gamePort = Integer.parseUnsignedInt(data.get("GamePort"));
		Integer numFreeRegularSlots = Integer.parseUnsignedInt(data.get("NumFreeRegularSlots"));
		Integer numFreeReservedSlots = Integer.parseUnsignedInt(data.get("NumFreeReservedSlots"));
		Date lastUpdated = new Date(Long.parseUnsignedLong(data.get("LastUpdated")) * 1000);
		
		return new ServerDO(atlasId, gameIp, gamePort, ip, lastUpdated, numFreeRegularSlots, numFreeReservedSlots, port, serverId);
	}
	
	private Long atlasId;
	private String gameIp;
	private Integer gamePort;
	private String ip;
	private Date lastUpdated;
	private Integer numFreeRegularSlots;
	private Integer numFreeReservedSlots;
	private Integer port;
	private Long serverId;

}
