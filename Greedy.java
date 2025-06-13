package TPE;

import java.util.ArrayList;
import java.util.Collections;

public class Greedy {
	private int cantPiezas;
	//candidatos las maquinas que suman 12 piezas
	//criterio Greedy : elegir, dentro del conjunto de maquinas, la de mayor cantidad de piezas 
	//Es solucion si la suma de las piezas es = 12
	
	
	public Greedy(int cantPiezas) {
		this.cantPiezas = cantPiezas;
	}
	
	
	public SolucionGreedy greedy(ArrayList<Maquina> maquinasCandidatas) {
		Collections.sort(maquinasCandidatas, new ComparatorCantPiezasDesc());
		SolucionGreedy solucion = new SolucionGreedy();
		solucion.setTotalCandidatos(maquinasCandidatas.size());
		while(!maquinasCandidatas.isEmpty() && !esSolucion(solucion)) {
			Maquina mejorCandidato = maquinasCandidatas.get(0);//elijo la mejor maquina candidata
			if(mejorCandidato !=null) {
				maquinasCandidatas.remove(mejorCandidato); // la saco de los candidatos
				if(factible(solucion, mejorCandidato.getCantPiezas())) {
					solucion.agregarMaquina(mejorCandidato); // la agrego a la solucion 					
				}
			}
		}
		if (esSolucion(solucion)) {
			solucion.setPuestasEnFuncionamiento();
			return solucion;
			
		}
		else {
			return null;		//CONSULTAR si no encuentro solucion retorno una solucion vacia o null??	en el main controlaar e imprimir no hay solucion
		}
	}
	
	public boolean esSolucion(SolucionGreedy solucion) {
		if(solucion.getCantPiezas() == cantPiezas) {
			return true;			
		}
		return false;
	}
	
	
	public boolean factible(SolucionGreedy solucion, int pieza) {
		return solucion.getCantPiezas() + pieza <= cantPiezas;
	}
	
	
}
