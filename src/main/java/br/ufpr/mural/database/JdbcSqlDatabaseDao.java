/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufpr.mural.database;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import br.ufpr.mural.core.Anuncio;
import br.ufpr.mural.core.Comentario;
import br.ufpr.mural.core.Evento;
import br.ufpr.mural.core.Lembrete;
import br.ufpr.mural.core.Mural;
import br.ufpr.mural.core.Post;
import br.ufpr.mural.core.Reacao;
import br.ufpr.mural.core.Sugestao;
import br.ufpr.mural.core.Usuario;

/**
 *
 * 
 */
class JdbcSqlDatabaseDao implements DatabaseDao {  // DAO = Data Access Object
        
	Connection conexao;
	
	public JdbcSqlDatabaseDao() {
		ConnectionFactory factory = new ConnectionFactory();
        conexao = factory.getConnection();
	}
	
	/*
	 * Esboço de método que insere usuário no banco. Necessário testar.
	 */
    public void inserirUsuario(Usuario usuario){
    	String sql = "INSERT INTO usuarios"
                + " (userName)"
                + " VALUES (?)";

        try {
            PreparedStatement stmt = conexao.prepareStatement(sql);

            stmt.setString(1, usuario.getUserName());
            
            stmt.execute();
            stmt.close();

        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }
    
    /*
	 * Esboço de método que recupera usuário do banco. Necessário testar.
	 */
    public Usuario getUsuario(String userName) {
    	String sql = "SELECT * FROM usuarios WHERE username='?'";

        try {
            PreparedStatement stmt = conexao.prepareStatement(sql);

            stmt.setString(1, userName);
            
            ResultSet rs = stmt.executeQuery();
            
            stmt.close();
            
            if (rs.next()) { // só haverá um resultado
            	Usuario usuario = new Usuario(rs.getString("username"));
            	return usuario;
            }

        } catch (SQLException ex) {
            System.out.println(ex);
        }
        
		return null;
    }
    
    public void inserirPost(Post post, Mural mural) {
    	
    }

	@Override
	public void inserirMural(Mural mural) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Mural recuperarMural(int idMural) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void recuperarPosts(Mural mural) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void inserirPost(Evento post, Mural mural) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void inserirPost(Anuncio post, Mural mural) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void inserirComentario(Comentario comentario, Post post) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Comentario> recuperarComentarios(Post post) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void inserirPostSalvo(Post post, Usuario usuario) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Post> recuperarPostsSalvos(Usuario usuario) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void inserirReacao(Reacao reacao, Post post) {
		//"INSERT ... INTO reacao_post"
		
	}

	@Override
	public List<Reacao> recuperarReacoes(Post post) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void removerReacao(Reacao reacao, Post post) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void inserirConfirmacaoEvento(Evento evento, Usuario usuario) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Evento> recuperarEventosConfirmados(Usuario usuario) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void removerConfirmacaoEvento(Evento evento, Usuario usuario) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void inserirLembrete(Lembrete lembrete, Usuario usuario) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Lembrete> recuperarLembretes(Usuario usuario) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void inserirSugestao(Sugestao sugestao) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Sugestao> recuperarSugestoesRecebidas(Usuario usuario) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Sugestao> recuperarSugestoesFeitas(Usuario usuario) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Sugestao> recuperarSugestoesPost(Post post) {
		// TODO Auto-generated method stub
		return null;
	}
    
    //...
    

	class ConnectionFactory {
	    
	    Connection getConnection() {
	        try {
	            //MUITO IMPORTANJTE ESSA LINHA ABAIXO!!!!
	            Class.forName("com.mysql.jdbc.Driver");
	            return DriverManager.getConnection(
	            		"jdbc:mysql://localhost/mural", "root", "root"
	            );
	        } catch (Exception e) {
	            throw new RuntimeException(e);
	        }
	    }
}

	@Override
	public void limparBase() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Mural getMural(String nomeMural) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<String, Mural> listMurais() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void limparBanco() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removerPost(int idPost) {
		// TODO Auto-generated method stub
		
	}
    
}

