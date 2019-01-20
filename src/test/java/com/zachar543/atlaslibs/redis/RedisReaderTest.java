package com.zachar543.atlaslibs.redis;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import redis.clients.jedis.Jedis;

import java.util.List;

public class RedisReaderTest {
	
	private RedisReader redisReader;
	private Jedis jedis;
	
	@Before
	public void setUp() {
		String host = "";
		String password = "";
		
		jedis = new Jedis(host);
		jedis.connect();
		jedis.auth(password);
		
		redisReader = new RedisReader(jedis);
	}
	@After
	public void cleanUp() {
		jedis.disconnect();
	}
	
	@Test
	public void findAllianceByAllianceId() {
		long allianceId = 0L;
		AllianceDO allianceByAllianceId = redisReader.findAllianceByAllianceId(allianceId);
		System.out.println("allianceByAllianceId = " + allianceByAllianceId);
	}
	
	@Test
	public void findAllAlliances() {
		List<AllianceDO> allAlliances = redisReader.findAllAlliances();
		System.out.println("allAlliances = " + allAlliances);
	}
	
	@Test
	public void findEntityByEntityId() {
		long entityId = 0L;
		EntityDO entityByEntityId = redisReader.findEntityByEntityId(entityId);
		System.out.println("entityByEntityId = " + entityByEntityId);
	}
	
	@Test
	public void findAllEntities() {
		List<EntityDO> allEntities = redisReader.findAllEntities();
		System.out.println("allEntities = " + allEntities);
	}
	
	@Test
	public void findAllAtlasIds() {
		List<Long> allAtlasIds = redisReader.findAllAtlasIds();
		System.out.println("allAtlasIds = " + allAtlasIds);
	}
	
	@Test
	public void findServer() {
		long atlasId = 0L;
		long serverId = 1L;
		ServerDO server = redisReader.findServer(atlasId, serverId);
		System.out.println("server = " + server);
	}
	
	@Test
	public void findAllServer() {
		long atlasId = 0L;
		List<ServerDO> allServer = redisReader.findAllServer(atlasId);
		System.out.println("allServer = " + allServer);
	}
	
	@Test
	public void findTerritoryClaimsByServer() {
		long serverId = 1L;
		List<TerritoryClaimDO> claims = redisReader.findTerritoryClaimsByServer(serverId);
		System.out.println("claims = " + claims);
	}
}