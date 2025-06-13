package TPE;
import java.util.Collections;
import java.util.ArrayList;


public class Backtracking {
	private Solucion mejorSolucion;           //secuencia de maquinas puestas en funcionamiento
	private int estadosGenerados;             //cantidad de estados generados en la su totalidad
	private int maqUsadas;

	public Backtracking(ArrayList<Maquina> maquinas) { 
		//no es necesario ordenarlas pero en este caso resulta beneficioso para allar la solucion 
		Collections.sort(maquinas, new ComparatorCantPiezasDesc());  //las ordeno de forma descendente 
		this.estadosGenerados = 0;
		this.mejorSolucion = null;
		this.maqUsadas = Integer.MAX_VALUE;
	}

	/* Planteo Backtracking
	 * cuando llego a un estado final? ...si ya recorri todas las combinaciones de maquinas 
	 * es solucion? ...si la suma de las piezasProducidas es igual al piezasTotales
	 * 
	 * 
	 * */
	public Solucion backtracking(ArrayList<Maquina> maquinas, int piezasTotales){
		Estado actual = new Estado();
		backtracking(actual, maquinas, piezasTotales,0);
		if(mejorSolucion == null) { //si no encontro una solucion devuelvo un Solucion con valores por defecto
			return new Solucion(estadosGenerados); // le paso los estados porque puedo no tener pero si haber recorrido
		}
		mejorSolucion.setEstadosGenerados(estadosGenerados);
		return this.mejorSolucion;
	}

	private void backtracking(Estado e, ArrayList<Maquina> maquinas, int piezasTotales, int indice) {
		this.estadosGenerados++; //contador de estados generados
		if(esEstadoFinal(e, piezasTotales)) {
			if(esSolucion(e, maquinas)) {
				guardarSolucion(e.getMaquinas(),e.getCantPiezas());
			}
		}
		else {
			//recorrer la lista de maquinas
			for (int i=indice;i<maquinas.size();i++) { //evita permutaciones redundantes (ej:[M1,M2] y [M2,M1])
				Maquina maquina = maquinas.get(i);
				//sumo las piezas de la maquina con las del estadoActual
				int nuevaCantPiezasTotales = maquina.getCantPiezas() + e.getCantPiezas(); 
				//si las piezas actuales superara al total de piezas a producir las podo
				if(!podar(nuevaCantPiezasTotales,piezasTotales)) { 
					//agregar a la solucion estado
					e.addMaquina(maquina);
					//recursion
					backtracking(e, maquinas, piezasTotales, i);
					//deshacer cambios
					e.remove(maquina);
				}
			}
		}
	}

	public boolean podar(int nuevaCantPiezasTotales, int piezasTotales) {
		return nuevaCantPiezasTotales>piezasTotales;
	}

	private void guardarSolucion(ArrayList<Maquina> maquinas, int piezasTotales) {
		mejorSolucion = new Solucion(maquinas,piezasTotales);
	}
	/*
	private void guardarSolucion(ArrayList<Maquina> maquinas, int piezasTotales, int estadosGenerados) {
		mejorSolucion = new Solucion(maquinas,piezasTotales, estadosGenerados);
	}*/

	public boolean esEstadoFinal(Estado e, int piezasTotales) {//SI COMPLETE LAS PIEZAS ES ESTADO FINAL
		if(e.getCantPiezas() == piezasTotales) {
			return true;
		}			
		return false;
	}

	public boolean esSolucion(Estado e, ArrayList<Maquina> maquinas) { //es solucion si es la mas corta...quiero guardar la mejor
		int minMaquinas = e.getMaquinas().size();
		if(minMaquinas<maqUsadas) { //si dejo <= piso con la solucion [M3,M3,M3]
			maqUsadas=minMaquinas;
			return true;
		}
		return false;
	}

}
