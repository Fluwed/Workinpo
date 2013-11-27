package Flux;

public class Img extends Flux {
	public Img(){
		this.nom= new ImgWidth();
	}
	public Img(String Lien, String Table, Nom nom) {
		super(Lien, Table, nom);
		this.nom=new ImgWidth();
		// TODO Auto-generated constructor stub
	}

}
