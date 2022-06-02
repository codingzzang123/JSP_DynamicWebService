package controller;
import java.util.*;
import java.io.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private HashMap<String,Object> map = new HashMap<>();
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		String props = config.getInitParameter("propertyConfig");
		
		Properties pr = new Properties();
		FileInputStream f = null;
		
		String path = config.getServletContext().getRealPath("/WEB-INF");
		
		try {
			f = new FileInputStream(new File(path, props));
			pr.load(f);
		}catch(IOException e) {
			e.printStackTrace();
		}finally {
			if(f!=null) {
				try {
					f.close();
				}catch(IOException e) {
					e.printStackTrace();
				}
			}
		}
		
		Iterator<Object> keyIter = pr.keySet().iterator();
		
		while(keyIter.hasNext()) {
			String command = (String)keyIter.next();
			String className = pr.getProperty(command);
			try {
				Class<?> commandClass = Class.forName(className);
				
				Object commandInstance = commandClass.newInstance();
				map.put(command,commandInstance);
			} catch(ClassNotFoundException e){
                throw new ServletException(e);
            } catch(InstantiationException e){
                throw new ServletException(e);
            } catch(IllegalAccessException e){
                throw new ServletException(e);
            }
		}
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		process(req,resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		process(req,resp);
	}

	public void process(HttpServletRequest request,HttpServletResponse response)throws ServletException, IOException{
        String view = null;
        Service service = null;	
        
        try {
        	String command = request.getRequestURI();
        	if(command.indexOf(request.getContextPath())==0) {
        		command = command.substring(request.getContextPath().length());
        	}
        	System.out.println("command = "+command);

        	service = (Service)map.get(command);
        	view = service.excute(request, response);
        }catch(Throwable e ) {
        	e.printStackTrace();
        }
        RequestDispatcher dispatcher = request.getRequestDispatcher(view);
        dispatcher.forward(request, response);
    }
            



}
