package com.ipartek.web.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalTime;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/holaweb")
public class HolaWebServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		out.printf("""
				<!DOCTYPE html>
				<html>
				<head>
					<title>Saludo</title>
				</head>
				<body>
					<h1>Hola mundo</h1>
					
					<p>El ejemplo inesquivable</p>
					
					<p>Son las %s</p>
				</body>
				</html>
				""", LocalTime.now());
	}

}
