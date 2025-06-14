package TPE;

import java.util.ArrayList;
import java.util.Collections;


public class Greedy {
	private int cantPiezas;
	
	
	public Greedy(int cantPiezas) {
		this.cantPiezas = cantPiezas;
	}
	
	
	/**
	* -CANDIDATOS = Al estar ordenados por la cantidad de piezas de forma descendente, los candidatos quedan ordenados por este criterio
	*
	* -CRITERIO GREEDY = elejimos, dentro del conjunto de maquinas, la de mayor cantidad de piezas 
	*
	* -Estrategia de seleccion  = Es un posible candidato si cumple que sus piezas sumadas a la solucion es menor igual a la cantidad total de piezas
	* 
	* -Como puede suceder que no haya candidatos que cumplan los requisitos, de no encontrarse una solucion se devolvera null.
	*Este sera controlado en el main donde si la solcion es null o el arreglo de maquinasCandidatas esta vacio mostramos por pantalla "No hay solucion"
	*/
	
	
	public SolucionGreedy greedy(ArrayList<Maquina> maquinasCandidatas) {
		Collections.sort(maquinasCandidatas, new ComparatorCantPiezasDesc());
		SolucionGreedy solucion = new SolucionGreedy();
		solucion.setTotalCandidatos(maquinasCandidatas.size());
		while(!maquinasCandidatas.isEmpty() && !esSolucion(solucion)) {
			Maquina mejorCandidato = maquinasCandidatas.get(0);//Al estar ordenado la primer maquina siempre es el mejor candidato
			if(mejorCandidato !=null) {
				maquinasCandidatas.remove(mejorCandidato); 
				if(factible(solucion, mejorCandidato.getCantPiezas())) {
					solucion.agregarMaquina(mejorCandidato); // si el candidato cumple el requerimiento lo agrego a mi solucion					
				}
			}
		}
		if (esSolucion(solucion)) {
			solucion.setPuestasEnFuncionamiento();
			return solucion;
			
		}
		else {
			return null;
		}
	}
	
	public boolean esSolucion(SolucionGreedy solucion) {
		if(solucion.getCantPiezas() == cantPiezas) {
			return true;			
		}
		return false;
	}
	
	//un candidato cumple si sus piezas sumadas a la solucion es menor igual a la cantidad total de piezas
	public boolean factible(SolucionGreedy solucion, int pieza) {
		return solucion.getCantPiezas() + pieza <= cantPiezas;
	}
	
}
