package com.zachar543.atlaslibs.redis;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PlayerDO {
	
	// playerinfo
	private Date lastOnlineAt;
	private Long playerId;
	private String playerName;
	private Long tribeId;
	
	// PlayerDataId
	private Long steamId;
	
	// playerserverinfo
	private Long currentServerId;
	private Long firstHomeServerId;
	private Long logLineId;
	
}
