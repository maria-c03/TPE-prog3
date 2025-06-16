package TPE;

import java.util.ArrayList;
import java.util.Collections;


public class Greedy {
	private int cantPiezas;


	public Greedy(int cantPiezas) {
		this.cantPiezas = cantPiezas;
	}


	/**
	 * -¿Cuales son los candidatos?
	 * CANDIDATOS = las maquinas ordenadas por la cantidad de piezas de forma descendente.
	 *
	 * -¿Estrategia de seleccion?
	 * Elejimos dentro del conjunto de maquinas la de mayor cantidad de piezas, en este caso la primer maquina, y la removemos de la lista.
	 * Luego determinamos si es un posible candidato verificando si cumple con que sus piezas sumadas a la solucion sea menor igual a la cantidad total de piezas,
	 * de serlo se agrega a la solucion y se evalua el siguiente candidato, asi hasta que la lista de maquinasCandidatas quede vacio.
	 * 
	 * -Cosideraciones respecto a encontrar o no solucion
	 * Para saber si los candidatos selecionados son realmente solucion se evalua que la suma de sus piezas sea igual al total de piezas a producir
	 * Como puede suceder que hayan soluciones que no cumplan con el total de piezas a producir, se devolvera null.
	 * Esto sera controlado en el main donde si la solcion es null o el arreglo de maquinasCandidatas esta vacio mostramos por pantalla "No hay solucion"
	 * 
	 * -Complejidad temporal= O(k^2), hacemos una iteracion por cada elemento de la lista O(k) y con cada iteracion hacemos O(k) por el remove()
	 */


	public SolucionGreedy greedy(ArrayList<Maquina> maquinasCandidatas) {
		Collections.sort(maquinasCandidatas, new ComparatorCantPiezasDesc());
		SolucionGreedy solucion = new SolucionGreedy();
		while(!maquinasCandidatas.isEmpty() && !esSolucion(solucion)) {
			Maquina mejorCandidato = maquinasCandidatas.get(0);//Al estar ordenado la primer maquina siempre es el mejor candidato
			if(mejorCandidato !=null) {
				solucion.setCandidatosConsiderados();
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
