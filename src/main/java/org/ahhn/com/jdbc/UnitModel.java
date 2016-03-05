package org.ahhn.com.jdbc;

/**
 * Created by XJX on 2016/3/4.
 */
public class UnitModel {
	private String id;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getCityId() {
		return cityId;
	}

	public void setCityId(int cityId) {
		this.cityId = cityId;
	}

	private int cityId;
}
