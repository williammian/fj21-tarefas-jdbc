package br.com.caelum.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import br.com.caelum.modelo.Usuario;

public class JdbcUsuarioDao extends Dao{

	public Usuario buscaUsuario(Usuario usuario){
		try{
			PreparedStatement stmt = getConnection().prepareStatement("select * from usuarios where login=? and senha=?");
			stmt.setString(1, usuario.getLogin());
			stmt.setString(2, usuario.getSenha());
			stmt.setMaxRows(1);
			
			ResultSet rs = stmt.executeQuery();
						
			Usuario usuarioEncontrado = null;
			
			while(rs.next()){
				usuarioEncontrado = new Usuario();
				
				usuarioEncontrado.setId(rs.getLong("id"));
				usuarioEncontrado.setLogin(rs.getString("login"));
				usuarioEncontrado.setSenha(rs.getString("senha"));
			}
			
			rs.close();
			stmt.close();
			
			return usuarioEncontrado;
			
		}catch(SQLException err){
			throw new RuntimeException(err);
		}
	}
	
}
