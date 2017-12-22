package cn.jxy.personnel.entity;

public class ViewUser {
	private String condition;
	private int num;
	
	
	public String getCondition() {
		return condition;
	}
	public void setCondition(String condition) {
		this.condition = condition;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	
	@Override
	public String toString() {
		return "ViewUser [condition=" + condition + ", num=" + num + "]";
	}
}
