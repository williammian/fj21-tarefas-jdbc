package br.com.caelum.tarefas.interceptor;

import java.sql.Connection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import br.com.caelum.jdbc.ConnectionFactory;

public class ConexaoInterceptor extends HandlerInterceptorAdapter{
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		System.out.println("preHandle Conexao");
				
		String uri = request.getRequestURI();
		
		if(uri.endsWith("loginForm") || uri.contains("resources")){
			return true;
		}
		
		System.out.println("Abrindo conexão");
		Connection connection = new ConnectionFactory().getConnection();
		
		System.out.println("Begin na transação");
		connection.setAutoCommit(false);
		
		request.setAttribute("connection", connection);
		
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
		super.postHandle(request, response, handler, modelAndView);
		System.out.println("postHandle Conexao");
		
		Connection connection = (Connection)request.getAttribute("connection");
		
		if(connection != null){
			System.out.println("Commit na transação");
			connection.commit();
			System.out.println("Fechando conexão");
			connection.close();
		}
	}


}
