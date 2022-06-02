package jsp.user.action;

public class ActionForward { //페이지 이동을 처리하기 위해 필요한 클래스
	private boolean isRedirect = false; //sendRedirect와 forward중 어떤 것을 이용해서 페이지 이동할지를 결정하기 위한 boolean 변수
	private String nextPath = null; //이동할 다음 화면
	
	/*
	 * Redirect 사용여부, false이면 Forward사용 
	 * */
	public boolean isRedirect() {
		return isRedirect;
	}
	public void setRedirect(boolean isRedirect) {
		this.isRedirect = isRedirect;
	}
	public String getNextPath() {
		return nextPath;
	}
	public void setNextPath(String nextPath) {
		this.nextPath = nextPath;
	}
}
