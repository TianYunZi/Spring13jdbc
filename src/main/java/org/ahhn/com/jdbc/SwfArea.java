package org.ahhn.com.jdbc;

/**
 * Created by XJX on 2016/3/4.
 */
public class SwfArea {
	private int id;

	private int parentid;

	private String name;

	private int provincecode;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getParentid() {
		return parentid;
	}

	public void setParentid(int parentid) {
		this.parentid = parentid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getProvincecode() {
		return provincecode;
	}

	public void setProvincecode(int provincecode) {
		this.provincecode = provincecode;
	}

	@Override
	public String toString() {
		return "SwfArea{" +
				"id=" + id +
				", parentid=" + parentid +
				", name='" + name + '\'' +
				", provincecode=" + provincecode +
				'}';
	}
}
