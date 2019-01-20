package com.zachar543.atlaslibs.redis;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EntityDO {
	
	public static EntityDO fromRedis(Map<String, String> data) {
		Boolean bInLandClaimedFlagRange = Boolean.valueOf(data.get("bInLandClaimedFlagRange"));
		String entityClass = data.get("EntityClass");
		Long entityID = Long.parseUnsignedLong(data.get("EntityID"));
		String entityName = data.get("EntityName");
		String entityType = data.get("EntityType");
		Date nextAllowedUseTime = new Date(Long.parseUnsignedLong(data.get("NextAllowedUseTime")) * 1000);
		Long parentEntityID = Long.parseUnsignedLong(data.get("ParentEntityID"));
		Long serverId = Long.parseUnsignedLong(data.get("ServerId"));
		BigDecimal serverXRelativeLocation = new BigDecimal(data.get("ServerXRelativeLocation"));
		BigDecimal serverYRelativeLocation = new BigDecimal(data.get("ServerYRelativeLocation"));
		String shipType = data.get("ShipType");
		Long tribeID = Long.parseUnsignedLong(data.get("TribeID"));
		
		return new EntityDO(bInLandClaimedFlagRange, entityClass, entityID, entityName, entityType, nextAllowedUseTime, parentEntityID, serverId, serverXRelativeLocation, serverYRelativeLocation, shipType, tribeID);
	}

	private Boolean inLandClaimedFlagRange;
	private String entityClass;
	private Long entityId;
	private String entityName;
	private String entityType;
	private Date nextAllowedUserTime;
	private Long parentEntityId;
	private Long serverId;
	private BigDecimal serverXRelativeLocation;
	private BigDecimal serverYRelativeLocation;
	private String shipType;
	private Long tribeId;
	
}
