package utils;

public class Autor {

	int idAutor;
	String nome;
	String descri;
	
	public Autor(int idAutor, String nome, String descri){
		this.idAutor = idAutor;
		this.nome = nome;
		this.descri = descri;
	}
	
	public int getIdAutor(){
		return this.idAutor;
	}
	
	public String getNome(){
		return this.nome;
	}
	
	public String getDescri(){
		return this.descri;
	}
}
