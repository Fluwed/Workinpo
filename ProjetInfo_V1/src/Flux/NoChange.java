package Flux;


public class NoChange extends Flux {
	public NoChange(){
		this.nom= new Classic();
	}
	public NoChange(String Lien, String Table, Nom nom) {
		super(Lien, Table, nom);
		this.nom=new Classic();
		// TODO Auto-generated constructor stub
	}

}
