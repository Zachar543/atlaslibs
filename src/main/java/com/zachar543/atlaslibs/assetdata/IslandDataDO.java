package com.zachar543.atlaslibs.assetdata;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class IslandDataDO {
	
	@JsonProperty("name")
	private String name;
	@JsonProperty("x")
	private BigDecimal sizeX;
	@JsonProperty("y")
	private BigDecimal sizeY;
	@JsonProperty("imagePath")
	private String imagePath;
	@JsonProperty("sublevelNames")
	private List<String> sublevelNames;
	
}
