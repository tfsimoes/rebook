package utils;

public class Livro {

	int idLivro;
	String nome;
	String nomeficheiro;
	int derivado;
	
	public Livro(int idLivro, String nome, String nomeFicheiro, int derivado){
		this.idLivro = idLivro;
		this.nome = nome;
		this.nomeficheiro = nomeFicheiro;
		this.derivado = derivado;
	}
	
	public int getIdLivro(){
		return this.idLivro;
	}
	
	public String getNome(){
		return this.nome;
	}
	
	public String getNomeFicheiro(){
		return this.nomeficheiro;
	}
	
	public int getDerivado(){
		return this.derivado;
	}
}
