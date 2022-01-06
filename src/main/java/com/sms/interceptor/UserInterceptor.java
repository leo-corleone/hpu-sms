/**
 * 
 */
package com.sms.interceptor;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author ��
 * @data 2021-7-11
 */
public class UserInterceptor implements HandlerInterceptor {

	
	private static String URL[] = {"/login.action", "/login" , "/checkcode.action"};
	
	/**
	*
	*@see HandlerInterceptor#preHandle(HttpServletRequest, HttpServletResponse, Object)
	*/
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object object) throws Exception {
		// TODO Auto-generated method stub
		
		boolean isExist = true;
		
		String url = request.getServletPath();
		
		for (int i = 0; i < URL.length; i++) {
			if (url.endsWith(URL[i])) {
				isExist = false;
				break;
			}
		}
		
	
		if (isExist) {
			HttpSession session = request.getSession();
			Object obj = session.getAttribute("user");
			if (obj == null) {
				response.sendRedirect(request.getContextPath()+"/login");
				return false;
			}
		}
		
		return true;
	}
	
	
	/**
	*
	*@see HandlerInterceptor#afterCompletion(HttpServletRequest, HttpServletResponse, Object, Exception)
	*/
	@Override
	public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
		// TODO Auto-generated method stub

	}

	/**
	*
	*@see HandlerInterceptor#postHandle(HttpServletRequest, HttpServletResponse, Object, ModelAndView)
	*/
	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3)
			throws Exception {
		// TODO Auto-generated method stub

	}

	

}
