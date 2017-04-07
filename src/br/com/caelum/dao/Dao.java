package br.com.caelum.dao;

import java.sql.Connection;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

public class Dao {

	public Connection getConnection(){
		//Obtendo a request
		HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
		//Obtendo a conexão que foi adicionada na request
		return (Connection)request.getAttribute("connection");
	}
	
}
