package com.wanghao.hgshop.pojo;

import java.io.Serializable;
/**
 * 品牌实体类  
 * @author hp
 *所有的实体类都需要实现序列化
 */
public class Brand implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -99341747621861022L;
	private Integer id;
	private String name;
	private String firstChar;
	private int deleteFlag;
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getFirstChar() {
		return firstChar;
	}
	public void setFirstChar(String firstChar) {
		this.firstChar = firstChar;
	}
	public int getDeleteFlag() {
		return deleteFlag;
	}
	public void setDeleteFlag(int deleteFlag) {
		this.deleteFlag = deleteFlag;
	}
	public Brand(Integer id, String name, String firstChar, int deleteFlag) {
		super();
		this.id = id;
		this.name = name;
		this.firstChar = firstChar;
		this.deleteFlag = deleteFlag;
	}
	@Override
	public String toString() {
		return "Brand [id=" + id + ", name=" + name + ", firstChar=" + firstChar + ", deleteFlag=" + deleteFlag + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + deleteFlag;
		result = prime * result + ((firstChar == null) ? 0 : firstChar.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Brand other = (Brand) obj;
		if (deleteFlag != other.deleteFlag)
			return false;
		if (firstChar == null) {
			if (other.firstChar != null)
				return false;
		} else if (!firstChar.equals(other.firstChar))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
	public Brand() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
