package br.com.caelum.tarefas.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.caelum.dao.JdbcUsuarioDao;
import br.com.caelum.modelo.Usuario;

@Controller
public class LoginController {
	
	private JdbcUsuarioDao dao;
	
	public LoginController() {
		this.dao = new JdbcUsuarioDao();
	}

	@RequestMapping("loginForm")
	public String loginForm(){
		return "formulario-login";
	}
	
	@RequestMapping("efetuaLogin")
	public String efetuaLogin(Usuario usuario, HttpSession session){
		Usuario usuarioLogado = dao.buscaUsuario(usuario);		
		
		if(usuarioLogado != null){
			session.setAttribute("usuarioLogado", usuarioLogado);
			return "menu";
		}
		
		return "redirect:loginForm";		
	}
	
	@RequestMapping("logout")
	public String logout(HttpSession session){
		session.invalidate();
		return "redirect:loginForm";
	}
	
}
