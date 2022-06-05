package jsp.board.model;

public class BoardVO {
	private int num;
	private String subject;
	private String content;
	private String pubdate;
	private String maker;
	private int clicks;
	private int replys;
    private String upload;
    private String pass;
    
	public int getReplys() {
		return replys;
	}

	public void setReplys(int replys) {
		this.replys = replys;
	}

	public BoardVO() {}
	
	public BoardVO(int num, String subject, String content, String pubdate, String maker, int clicks,int replys,String upload, String pass) {
		super();
		this.num = num;
		this.subject = subject;
		this.content = content;
		this.pubdate = pubdate;
		this.maker = maker;
		this.clicks = clicks;
		this.replys = replys;
		this.upload = upload;
		this.pass = pass;
	}
	
	
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getPubdate() {
		return pubdate;
	}
	public void setPubdate(String pubdate) {
		this.pubdate = pubdate;
	}
	public String getMaker() {
		return maker;
	}
	public void setMaker(String maker) {
		this.maker = maker;
	}
	public int getClicks() {
		return clicks;
	}
	public void setClicks(int clicks) {
		this.clicks = clicks;
	}

	public String getUpload() {
		return upload;
	}

	public void setUpload(String upload) {
		this.upload = upload;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}
	
}
