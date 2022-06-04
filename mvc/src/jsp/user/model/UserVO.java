package jsp.user.model;

public class UserVO {
	String id;
	String name;
	String pw;
	String age;
	String comment;
	String img;
	String code;
	
	public UserVO() {}
	public UserVO(String id, String name, String pw, String age, String comment, String img, String code) {
		super();
		this.id = id;
		this.name = name;
		this.pw = pw;
		this.age = age;
		this.comment = comment;
		this.img = img;
		this.code = code;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPw() {
		return pw;
	}
	public void setPw(String pw) {
		this.pw = pw;
	}
	public String getAge() {
		return age;
	}
	public void setAge(String age) {
		this.age = age;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	@Override
	public String toString() {
		return "****";
	}
	
	
}
