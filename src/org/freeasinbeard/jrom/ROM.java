package org.freeasinbeard.jrom;

public class ROM {
	private String name;
	private String console;
	private String hash;
	private Integer romSize;
	private Integer ramSize;
	private String country;
	private String region;
	private String version;
	
	public ROM(String name, String console) {
		this.name = name;
        this.console = console;
        this.hash = null;
        this.romSize = null;
        this.ramSize = null;
        this.country = null;
        this.region = null;
        this.version = null;
	}
	
	public String getName() {
		return name;
	}

	public String getConsole() {
		return console;
	}

	public String getHash() {
		return hash;
	}

	public void setHash(String hash) {
		this.hash = hash;
	}

	public Integer getROMSize() {
		return romSize;
	}

	public void setROMSize(Integer romSize) {
		this.romSize = romSize;
	}

	public Integer getRAMSize() {
		return ramSize;
	}

	public void setRAMSize(Integer ramSize) {
		this.ramSize = ramSize;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}
}
