package TPE;

import java.util.ArrayList;

public class Backtracking {
	private SolucionBacktracking mejorSolucion;           //secuencia de maquinas puestas en funcionamiento
	private int estadosGenerados;                         //cantidad de estados generados en la su totalidad
	private int maqUsadas;                                //cantidad de maquinas usadas inicia seteada en el valor mas alto de un Integer

	public Backtracking(ArrayList<Maquina> maquinas) { 
		this.estadosGenerados = 0;
		this.mejorSolucion = null;
		this.maqUsadas = Integer.MAX_VALUE;
	}

	
	/** Planteo Backtracking
	 *  -Arbol de exploracion...arrancamos con un arbol vacio, luego se evalua si M1 es solucion, de serlo la guarda, avanza y evalua si M1M1 son solucion
	 * de no serlo evalua la siguiente combinacion M1M2, de no serlo evalua la proxima combinacion y asi con el resto de las maquinas. Una vez encontrada
	 * una solucion la agrega al arreglo como mejorSolcion hasta ese momento.
	 * 
	 *  -ESTADO FINAL =  cuando produjo la totalidad de piezas requeridas, en este caso 12
	 *  
	 *  -ES SOLUCION = como quiero solo guardar la mejor solucion esta sera mi estado final
	 * 
	 *  -PODA = cuando la suma de las piezas de la maquina y las del estado superen la cantidad de piezas totales a fabricar se realizara la poda
	 * */
	
	public SolucionBacktracking backtracking(ArrayList<Maquina> maquinas, int piezasTotales){
		Estado actual = new Estado();
		backtracking(actual, maquinas, piezasTotales,0);
		if(mejorSolucion == null) { //si no encontro una solucion devuelvo un Solucion con valores por defecto
			return new SolucionBacktracking(estadosGenerados); // le paso los estados porque puedo no tener pero si haber recorrido
		}
		mejorSolucion.setEstadosGenerados(estadosGenerados);
		return this.mejorSolucion;
	}

	private void backtracking(Estado e, ArrayList<Maquina> maquinas, int piezasTotales, int indice) {
		this.estadosGenerados++;  //contador de estados generados
		if(esEstadoFinal(e, piezasTotales)) {
			if(esSolucion(e, maquinas)) {
				guardarSolucion(e.getMaquinas(),e.getCantPiezas());
			}
		}
		else {
			for (int i=indice;i<maquinas.size();i++) { // con el indice evitamos permutaciones redundantes (ej:[M1,M2] y [M2,M1])
				Maquina maquina = maquinas.get(i);
				int nuevaCantPiezasTotales = maquina.getCantPiezas() + e.getCantPiezas(); 
				if(!podar(nuevaCantPiezasTotales,piezasTotales)) { 
					e.addMaquina(maquina);
					backtracking(e, maquinas, piezasTotales, i);
					e.remove(maquina);
				}
			}
		}
	}

	public boolean podar(int nuevaCantPiezasTotales, int piezasTotales) {
		return nuevaCantPiezasTotales>piezasTotales;
	}

	private void guardarSolucion(ArrayList<Maquina> maquinas, int piezasTotales) {
		mejorSolucion = new SolucionBacktracking(maquinas,piezasTotales);
	}
	

	public boolean esEstadoFinal(Estado e, int piezasTotales) {
		if(e.getCantPiezas() == piezasTotales) {
			return true;
		}			
		return false;
	}

	public boolean esSolucion(Estado e, ArrayList<Maquina> maquinas) { 
		int minMaquinas = e.getMaquinas().size();
		if(minMaquinas<maqUsadas) { // si dejo <= piso la mejor solucion actual con la siguiente mejor solucion en este caso [M3,M3,M3]
			maqUsadas=minMaquinas;
			return true;
		}
		return false;
	}

}
