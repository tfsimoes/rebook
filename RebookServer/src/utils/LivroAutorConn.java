package utils;

public class LivroAutorConn {

	int idLivro;
	int idAutor;
	
	public LivroAutorConn(int idLivro, int idAutor){
		this.idLivro = idLivro;
		this.idAutor = idAutor;
	}
	
	public int getIdLivro(){
		return this.idLivro;
	}
	
	public int getIdAutor(){
		return this.idAutor;
	}
}
