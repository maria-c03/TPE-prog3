package TPE;
import java.util.Collections;
import java.util.ArrayList;


public class Backtracking {
	//private List<Maquina> maquinas;         //secuencia de maquinas
	//private int piezasTotales;              //cantidad de piezas totales a producir     las abstraigo para usar el backtracking como algoritmo
	private Solucion mejorSolucion;           //secuencia de maquinas puestas en funcionamiento
	private int piezasProducidas;             //cantidad piezas producidas por una maquina
	private int estadosGenerados;             //cantidad de estados generados 
	//CONSULTAR QUE ESTADO, EL DE LA BUSQUEDA COMPLETA (72) O LOS QUE TARDA EN ENCONTRAR LA MEJOR SOLUCION (4)
	private int maqUsadas;

	public Backtracking(ArrayList<Maquina> maquinas) { 
		Collections.sort(maquinas, new ComparatorCantPiezasDesc());  //las ordeno de forma descendente 
		this.piezasProducidas = 0;
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
		//if(esEstadoFinal(e, maquinas)) {// TIENE REALMENTE ESTADO FINAL? Consultar!!
		if(esSolucion(e, piezasTotales)) {
			//System.out.println("maquinas usadas"+ maqUsadas);
			int minMaquinas = e.getMaquinas().size();
			if(minMaquinas<maqUsadas) { //si dejo <= piso con la solucion [M3,M3,M3]
				guardarSolucion(e.getMaquinas(),e.getCantPiezas());
				maqUsadas=minMaquinas;
			}
		}
		//}
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

	public boolean esEstadoFinal(Estado e, ArrayList<Maquina> maquinas) {
		if(maquinas.isEmpty()) {
			return false;
		}
		return true;
	}

	public boolean esSolucion(Estado e, int piezasTotales) {
		if(e.getCantPiezas() == piezasTotales) {
			return true;
		}
		return false;
	}

}
