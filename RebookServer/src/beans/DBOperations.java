package beans;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import utils.Autor;
import utils.Livro;
import utils.LivroAutorConn;
import utils.LivroUserSee;

public class DBOperations {
	
	public int doLogin(String username, String password) {
		Context initContext = null;
		PreparedStatement prepared = null;
		Connection conn = null;
		ResultSet result = null;
		int id = -1;

		try {
			initContext = new InitialContext();

			DataSource ds = (DataSource) initContext.lookup("java:/comp/env/jdbc/ReBook");

			conn = ds.getConnection();

			prepared = conn.prepareStatement("SELECT idUser FROM "
					+ "UTILIZADOR WHERE username like ? AND password like ?;");

			prepared.setString(1, username);
			prepared.setString(2, password);

			result = prepared.executeQuery();

			if (result.next()) {
				id = result.getInt(1);
			} else {
				id = -1;
			}

			result.close();
			prepared.close();
			conn.close();
			initContext.close();
		} catch (NamingException | SQLException e) {
			// erro na base de dados
			e.printStackTrace();
		}

		return id;
	}
	
	public int doRegist (String username, String password){
		Context initContext = null;
		PreparedStatement prepared = null;
		Connection conn = null;
		int sucess = -1;
		
		try {
			initContext = new InitialContext();

			DataSource ds = (DataSource) initContext.lookup("java:/comp/env/jdbc/ReBook");

			conn = ds.getConnection();

			prepared = conn.prepareStatement("INSERT INTO UTILIZADOR (username, password) " + 
			 " VALUES (?, ?);");

			prepared.setString(1, username);
			prepared.setString(2, password);

			prepared.executeUpdate();

			sucess = 1;
			
			prepared.close();
			conn.close();
			initContext.close();
		} catch (NamingException | SQLException e) {
			// erro na base de dados
			e.printStackTrace();
		}
		
		return sucess;
	}

	public ArrayList<LivroUserSee> doBuscaLivros(){
		
		Context initContext = null;
		PreparedStatement preparedLivros = null, preparedCon = null, preparedAutor = null;
		Connection conn = null;
		ResultSet resultLivros = null, resultCon = null, resultAutor = null;
		
		ArrayList<Livro> livros = new ArrayList<Livro>();
		ArrayList<LivroAutorConn> ligacao = new ArrayList<LivroAutorConn>();
		ArrayList<Autor> autores = new ArrayList<Autor>();
		ArrayList<LivroUserSee> livrosUser = new ArrayList<LivroUserSee>();
		Livro tempLivro;
		LivroAutorConn tempConn;
		Autor tempAutor;
		LivroUserSee livroUser;
		int tempIdLivro, tempIdAutor;
		
		try {
			initContext = new InitialContext();

			DataSource ds = (DataSource) initContext.lookup("java:/comp/env/jdbc/ReBook");

			conn = ds.getConnection();

			preparedLivros = conn.prepareStatement("SELECT * FROM LIVRO;");
			preparedCon = conn.prepareStatement("SELECT * FROM LIVRO_AUTOR;");
			preparedAutor = conn.prepareStatement("SELECT * FROM AUTOR;");

			resultLivros = preparedLivros.executeQuery();
			resultCon = preparedCon.executeQuery();
			resultAutor = preparedAutor.executeQuery();
			
			while(resultLivros.next()){
				tempLivro = new Livro(resultLivros.getInt(1), resultLivros.getString(2), resultLivros.getString(3), resultLivros.getInt(4));
				livros.add(tempLivro);
			}
			
			while(resultCon.next()){
				tempConn = new LivroAutorConn(resultCon.getInt(1), resultCon.getInt(2));
				ligacao.add(tempConn);
			}
			
			while(resultAutor.next()){
				tempAutor = new Autor(resultAutor.getInt(1), resultAutor.getString(2), resultAutor.getString(3));
				autores.add(tempAutor);
			}
			
			ArrayList<Integer> idLivros = new ArrayList<Integer>();
			String tempAutores;
			
			for(int i=0; i<ligacao.size(); i++){
				tempIdLivro = ligacao.get(i).getIdLivro();
				tempIdAutor = ligacao.get(i).getIdAutor();
				
				if(!idLivros.contains(tempIdLivro)){
				
					livroUser = new LivroUserSee(tempIdLivro-1, livros.get(tempIdLivro-1).getNome(),
							autores.get(tempIdAutor-1).getNome(), livros.get(tempIdLivro-1).getNomeFicheiro(),
							livros.get(livros.get(tempIdLivro-1).getDerivado()-1).getNome());
					
					livrosUser.add(livroUser);
					idLivros.add(tempIdLivro);
				}else{ 
					
					tempAutores = livrosUser.get(idLivros.indexOf(tempIdLivro)).getAutor()
								+ ", " + autores.get(tempIdAutor-1).getNome();
					
					livrosUser.get(idLivros.indexOf(tempIdLivro)).setAutor(tempAutores);
				}
			}
			
			preparedLivros.close();
			preparedCon.close();
			preparedAutor.close();
			resultLivros.close();
			resultCon.close();
			resultAutor.close();
			conn.close();
			initContext.close();
		} catch (NamingException | SQLException e) {
			// erro na base de dados
			e.printStackTrace();
		}
		
		return livrosUser;
	}
	
	public ArrayList<LivroUserSee> doBuscaLivrosFavoritos(int idUser){
		
		Context initContext = null;
		PreparedStatement preparedLivros = null, preparedCon = null, preparedAutor = null, preparedFavoritos = null;
		Connection conn = null;
		ResultSet resultLivros = null, resultCon = null, resultAutor = null, resultFavoritos = null;
		
		ArrayList<Livro> livros = new ArrayList<Livro>();
		ArrayList<LivroAutorConn> ligacao = new ArrayList<LivroAutorConn>();
		ArrayList<Autor> autores = new ArrayList<Autor>();
		ArrayList<LivroUserSee> livrosUser = new ArrayList<LivroUserSee>();
		Livro tempLivro;
		LivroAutorConn tempConn;
		Autor tempAutor;
		LivroUserSee livroUser;
		int tempIdLivro, tempIdAutor;
		
		try {
			initContext = new InitialContext();

			DataSource ds = (DataSource) initContext.lookup("java:/comp/env/jdbc/ReBook");

			conn = ds.getConnection();

			preparedFavoritos = conn.prepareStatement("SELECT idLivro FROM FAVORITO WHERE idUser = ?");
					
			preparedFavoritos.setInt(1, idUser);
			
			resultFavoritos = preparedFavoritos.executeQuery();
			
			ArrayList<Integer> idLivrosFavoritos = new ArrayList<Integer>();
			
			while(resultFavoritos.next()){
				idLivrosFavoritos.add(resultFavoritos.getInt(1));
			}
			
			preparedLivros = conn.prepareStatement("SELECT * FROM LIVRO;");
			preparedCon = conn.prepareStatement("SELECT * FROM LIVRO_AUTOR;");
			preparedAutor = conn.prepareStatement("SELECT * FROM AUTOR;");
			
			resultLivros = preparedLivros.executeQuery();
			resultCon = preparedCon.executeQuery();
			resultAutor = preparedAutor.executeQuery();
			
			while(resultLivros.next()){
				tempLivro = new Livro(resultLivros.getInt(1), resultLivros.getString(2), resultLivros.getString(3), resultLivros.getInt(4));
				livros.add(tempLivro);
			}
			
			while(resultCon.next()){
				tempConn = new LivroAutorConn(resultCon.getInt(1), resultCon.getInt(2));
				ligacao.add(tempConn);
			}
			
			while(resultAutor.next()){
				tempAutor = new Autor(resultAutor.getInt(1), resultAutor.getString(2), resultAutor.getString(3));
				autores.add(tempAutor);
			}
			
			ArrayList<Integer> idLivros = new ArrayList<Integer>();
			String tempAutores;
			
			for(int i=0; i<ligacao.size(); i++){
				tempIdLivro = ligacao.get(i).getIdLivro();
				tempIdAutor = ligacao.get(i).getIdAutor();
				
				if(idLivrosFavoritos.contains(tempIdLivro)){
					if(!idLivros.contains(tempIdLivro)){
					
						livroUser = new LivroUserSee(tempIdLivro-1, livros.get(tempIdLivro-1).getNome(),
								autores.get(tempIdAutor-1).getNome(), livros.get(tempIdLivro-1).getNomeFicheiro(),
								livros.get(livros.get(tempIdLivro-1).getDerivado()-1).getNome());
						
						livrosUser.add(livroUser);
						idLivros.add(tempIdLivro);
					}else{ 
						
						tempAutores = livrosUser.get(idLivros.indexOf(tempIdLivro)).getAutor()
									+ ", " + autores.get(tempIdAutor-1).getNome();
						
						livrosUser.get(idLivros.indexOf(tempIdLivro)).setAutor(tempAutores);
					}
				}
			}
			
			preparedLivros.close();
			preparedCon.close();
			preparedAutor.close();
			resultLivros.close();
			resultCon.close();
			resultAutor.close();
			conn.close();
			initContext.close();
		} catch (NamingException | SQLException e) {
			// erro na base de dados
			e.printStackTrace();
		}
		
		return livrosUser;
	}

	public int doAddFavorito(int idUser, int idLivro){
		Context initContext = null;
		PreparedStatement prepared = null;
		Connection conn = null;
		int sucess = -1;
		
		try {
			initContext = new InitialContext();

			DataSource ds = (DataSource) initContext.lookup("java:/comp/env/jdbc/ReBook");

			conn = ds.getConnection();

			prepared = conn.prepareStatement("INSERT INTO FAVORITO (idUser, idLivro) " + 
			 " VALUES (?, ?);");

			prepared.setInt(1, idUser);
			prepared.setInt(2, idLivro);

			prepared.executeUpdate();
			
			prepared.close();
			conn.close();
			initContext.close();
			sucess = 1;
		} catch (NamingException | SQLException e) {
			// erro na base de dados
			e.printStackTrace();
		}
		
		return sucess;
	}


}
