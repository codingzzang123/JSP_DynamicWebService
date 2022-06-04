package controller;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface Service {
	public ActionForward excute(HttpServletRequest request, 
            HttpServletResponse response) throws Throwable;
}
