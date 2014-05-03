package utils;

public class LivroUserSee {
	int idLivro;
	String nome;
	String autor;
	String nomeFicheiro;
	String derivado;
	
	public LivroUserSee(int idLivro, String nome, String autor, String nomeFicheiro, String derivado){
		this.idLivro = idLivro;
		this.nome = nome;
		this.autor = autor;
		this.nomeFicheiro = nomeFicheiro;
		this.derivado = derivado;
	}
	
	public int getIdLivro(){
		return idLivro;
	}
	
	public void setAutor(String autor){
		this.autor = autor;
	}
	
	public String getNome(){
		return nome;
	}
	
	public String getAutor(){
		return autor;
	}
	
	public String getNomeFicheiro(){
		return nomeFicheiro;
	}
	
	public String getDerivado(){
		return derivado;
	}
}
