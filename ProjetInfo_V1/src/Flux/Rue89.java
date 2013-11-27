package Flux;

public class Rue89 extends Flux {
	public Rue89(){
		this.nom= new StopHtml();
	}
	public Rue89(String Lien, String Table, Nom nom) {
		super(Lien, Table, nom);
		this.nom=new StopHtml();
		// TODO Auto-generated constructor stub
	}

}

