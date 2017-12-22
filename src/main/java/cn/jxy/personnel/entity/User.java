package cn.jxy.personnel.entity;



import javax.persistence.Column;
/**
 * 用户实体类
 */
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "tb_user")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(length=10)
	private String username;
	@Column(length=2)
	private String sex;
	@Column(length=3)
	private int age;
	@Column(length=5)
	private String nation;// 民族
	@Column(length=20)
	private String address;// 地址
	@Column(length=5)
	private String education;// 学历
	@Column(length=5)
	private String politicalStatus;// 政治面貌
	@Column(length=15)
	private String industry;// 行业
	@Column(length=11)
	private String phone;// 手机号
	@Column(length=30)
	private String email;
	@Column(length=15)
	private String password;
	@Column(length=1)
	private int type;// 0:管理员、1:普通用户
	//@Transient
	@Column(length=10)
	private int num;//暂用字段

	public User() {
		super();
	}
	
	
	
	public User(String sex, int num) {
		super();
		this.sex = sex;
		this.num = num;
	}



	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	
	public String getUsername() {
		return username;
	}



	public void setUsername(String username) {
		this.username = username;
	}



	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getNation() {
		return nation;
	}

	public void setNation(String nation) {
		this.nation = nation;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getEducation() {
		return education;
	}

	public void setEducation(String education) {
		this.education = education;
	}

	public String getPoliticalStatus() {
		return politicalStatus;
	}

	public void setPoliticalStatus(String politicalStatus) {
		this.politicalStatus = politicalStatus;
	}


	public String getIndustry() {
		return industry;
	}

	public void setIndustry(String industry) {
		this.industry = industry;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}
	
	
	
	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", sex=" + sex + ", age=" + age + ", nation=" + nation
				+ ", address=" + address + ", education=" + education + ", politicalStatus=" + politicalStatus
				+ ", industry=" + industry + ", phone=" + phone + ", email=" + email + ", password=" + password
				+ ", type=" + type + ", num=" + num + "]";
	}

}
