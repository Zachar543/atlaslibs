package com.zachar543.atlaslibs.redis;

import redis.clients.jedis.Jedis;

import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class RedisReader {
	private static Pattern clusterKeyPattern = Pattern.compile("cluster:(?<atlasId>\\d+):(?<serverId>\\d+)");
	
	private Jedis jedis;
	
	public RedisReader(Jedis jedis) {
		this.jedis = jedis;
	}
	
	public AllianceDO findAllianceByAllianceId(Long id) {
		Map<String, String> allianceData = jedis.hgetAll("alliance:" + id);
		List<Long> allianceAdmins = jedis.smembers("alliance.admins:" + id).stream().map(Long::parseUnsignedLong).collect(Collectors.toList());
		List<Long> allianceMembers = jedis.smembers("alliance.members:" + id).stream().map(Long::parseUnsignedLong).collect(Collectors.toList());
		
		return AllianceDO.fromRedis(allianceData, allianceAdmins, allianceMembers);
	}
	public List<AllianceDO> findAllAlliances() {
		return jedis.keys("alliance:*").stream()
				.map(key -> {
					Map<String, String> allianceData = jedis.hgetAll(key);
					String id = allianceData.get("AllianceID");
					List<Long> allianceAdmins = jedis.smembers("alliance.admins:" + id).stream().map(Long::parseUnsignedLong).collect(Collectors.toList());
					List<Long> allianceMembers = jedis.smembers("alliance.members:" + id).stream().map(Long::parseUnsignedLong).collect(Collectors.toList());
					
					return AllianceDO.fromRedis(allianceData, allianceAdmins, allianceMembers);
				})
				.collect(Collectors.toList());
	}
	
	public EntityDO findEntityByEntityId(Long id) {
		return EntityDO.fromRedis(jedis.hgetAll("entityinfo:" + id));
	}
	public List<EntityDO> findAllEntities() {
		return jedis.keys("entityinfo:*").stream()
				.map(key -> EntityDO.fromRedis(jedis.hgetAll(key)))
				.collect(Collectors.toList());
	}
	
	public List<Long> findAllAtlasIds() {
		return jedis.keys("cluster:*").stream()
				.map(key -> {
					Matcher matcher = clusterKeyPattern.matcher(key);
					if (matcher.matches()) {
						return Long.parseUnsignedLong(matcher.group("atlasId"));
					}
					return null;
				})
				.distinct()
				.collect(Collectors.toList());
	}
	
	public ServerDO findServer(Long atlasId, Long serverId) {
		Map<String, String> data = jedis.hgetAll("cluster:" + atlasId + ":" + serverId);
		return ServerDO.fromRedis(data);
	}
	public List<ServerDO> findAllServer(Long atlasId) {
		return jedis.keys("cluster:" + atlasId + ":*").stream()
				.map(key -> ServerDO.fromRedis(jedis.hgetAll(key)))
				.collect(Collectors.toList());
	}
	
	public List<TerritoryClaimDO> findTerritoryClaimsByServer(Long serverId) {
		return jedis.smembers("territorymapdata:" + serverId).stream()
				.map(entry -> TerritoryClaimDO.fromRedis(serverId, entry))
				.collect(Collectors.toList());
	}
	
}
