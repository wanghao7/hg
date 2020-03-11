package com.wanghao.hgshop.pojo;

import java.io.Serializable;
import java.util.List;

public class Spec implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3685212966282376080L;
	private Integer id;
	private String specName;
	private List<SpecOption> options;
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getSpecName() {
		return specName;
	}
	public void setSpecName(String specName) {
		this.specName = specName;
	}
	public List<SpecOption> getOptions() {
		return options;
	}
	public void setOptions(List<SpecOption> options) {
		this.options = options;
	}
	public Spec(Integer id, String specName, List<SpecOption> options) {
		super();
		this.id = id;
		this.specName = specName;
		this.options = options;
	}
	@Override
	public String toString() {
		return "Spec [id=" + id + ", specName=" + specName + ", options=" + options + "]";
	}
	public Spec() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
}
