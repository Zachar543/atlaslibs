package com.zachar543.atlaslibs.redis;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AllianceDO {
	
	public static AllianceDO fromRedis(Map<String, String> data, List<Long> admins, List<Long> members) {
		Long allianceId = Long.parseUnsignedLong(data.get("AllianceID"));
		String name = data.get("Name");
		
		return new AllianceDO(allianceId, name, admins, members);
	}
	
	private Long allianceId;
	private String name;
	private List<Long> tribeAdmins;
	private List<Long> tribeMembers;
	
}
