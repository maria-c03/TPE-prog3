package TrabajoPractico;

import java.util.ArrayList;


public class Solucion {
	private int produccion;
	private ArrayList<Maquina> maquinas;
	
	public Solucion(int p) {
		this.maquinas = new ArrayList<Maquina>();
		produccion = p;
	}

	public void addMaquina(Maquina m) {
		if(!contieneMaquina(m)) {
			maquinas.add(m);			
		}
	}

	public boolean contieneMaquina (Maquina m) {
		return maquinas.contains(m);
	}



}
