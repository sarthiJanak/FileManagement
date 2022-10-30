package com.record.management.configuration;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

public class CustomAuthenticationErrorHandling implements AuthenticationEntryPoint {

	@Override
	public void commence(HttpServletRequest req, HttpServletResponse res, AuthenticationException authException)
			throws IOException, ServletException {

		JSONObject responseObj = new JSONObject();
		responseObj.put("status", HttpStatus.FORBIDDEN);
		responseObj.put("timestamp", System.currentTimeMillis());
		responseObj.put("message", "Access denied");
		res.setContentType("application/json;charset=UTF-8");
		res.setStatus(403);
		res.getWriter().write(responseObj.toString());
	}

}
