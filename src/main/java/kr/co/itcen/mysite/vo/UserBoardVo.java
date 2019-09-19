package kr.co.itcen.mysite.vo;

public class UserBoardVo {

	
	private Long no;
	private String title;
	private String name;
	private int hit;
	private String registerDate;
	
	
	public Long getNo() {
		return no;
	}
	public void setNo(Long no) {
		this.no = no;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getHit() {
		return hit;
	}
	public void setHit(int hit) {
		this.hit = hit;
	}
	public String getRegisterDate() {
		return registerDate;
	}
	public void setRegisterDate(String registerDate) {
		this.registerDate = registerDate;
	}

	@Override
	public String toString() {
		return "User_BoardVo [no=" + no + ", title=" + title + ", name=" + name + ", hit=" + hit + ", registerDate="
				+ registerDate + "]";
	}
	
	
}
