package com.mcy.mobile.http;
 /**
  * 请求参数类（键值对形式）
  * name:value
  * 支持序列化
  * @author Administrator
  *
  */
public class RequestParameter  implements java.io.Serializable, Comparable<Object> {

	private static final long serialVersionUID = 1274906854152052510L;
	private String name;
	private String value;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public RequestParameter(String name,String value){
		this.name = name;
		this.value = value;
	}
	
	@Override
	public boolean equals(Object o) {
		if(null == o){
			return false;
		}
		
		if(this == o){
			return true;
		}
		
		if(o instanceof  RequestParameter){
			RequestParameter parameter = (RequestParameter)o;
			return name.equals(parameter.name)&&value.equals(parameter.value);
		}
		
		return false;
	}
	
	@Override
	public int compareTo(Object another) {
		int compared;
		/**
		 * 值比较
		 */
		RequestParameter parameter = (RequestParameter)another;
		compared = name.compareTo(parameter.name);
		if(compared == 0){
			compared = value.compareTo(parameter.value);
		}
		return compared;
	}
}
