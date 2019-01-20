package com.zachar543.atlaslibs.redis;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TribeDO {
	
	private Boolean setGovernment;
	private List<Long> tribeAdmins;
	private Integer tribeGovernmentDinoOwnership;
	private Integer tribeGovernmentDinoTaming;
	private Integer tribeGovernmentDinoUnclaimAdminOnly;
	private Integer tribeGovernmentPinCode;
	private Integer tribeGovernmentStructureOwnership;
	private Long tribeId;
	private String tribeName;
	private Long tribeOwnerPlayerDataId;
	private List<Long> alliances;
	private List<Long> entities;
	private List<String> logs;
	private List<Long> members;
	
}
