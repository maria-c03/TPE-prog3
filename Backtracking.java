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
	 * -¿Como se genera el arbol de exploracion? 
	 * Arrancamos con un arbol vacio donde en cada nivel del arbol el algoritmo agrega una maquina desde el indice i, recorriendo de esta forma en profundidad.
	 * Con el indice lo que se logra es por un lado probar combinaciones con repeticion y por otro lado evitar los casos de permutaciones como [M1,M2] Y [M2,M1] ya que la solucion de estos es la misma.
	 * Luego, con la poda se descartan los casos en el que una rama, por ejemplo [M1,M1], supere la cantidad de piezas totales. 
	 * ejemplo de como se veria el arbol de exploracion 
	 * 
	 * nivel 0                                                       []
	 * nivel 1                  [M1]                     [M2]                 [M3]               [M4]
	 * nivel 2      [M1,M1] [M1,M2] [M1,M3] [M1,M4] ....
	 * :
	 * nivel n     hasta alcanzar piezasTotales = 12
	 * 
	 * asi por ejemplo un recorrido de exploracion seria i=o [M1] la suma de las piezas no supera las piezas totales 
	 * evalua [M1,M1] pero como supera las piezas totales lo poda y avanza i=1
	 * evalua [M1,M2] la suma no supera las piezas totales, evalua [M1,M2,M2] supera el total de piezas, poda y avanza i=2
	 * evalua [M1,M2,M3] supera el total de piezas, poda y avanza i=3
	 * evalua [M1,M2,M4] no supera el total de piezas, evalua [M1,M2,M4,M4] primera solucion allada 
	 * 
	 * -Complejidad temporal= en caso de que la primer maquina produzca las 12 piezas seria O(1), 
	 *  en el peor caso O(k^n) donde k = cantidad de maquinas y n = profundidad del arbol
	 * 
	 * -¿Cuales son los estados finales y solucion?
	 * ESTADO FINAL =  cuando produjo la totalidad de piezas requeridas, en este caso 12
	 * ES SOLUCION = quiero solo guardar la mejor solucion, que en este caso seria la menor cantidad de maquinas puestas en funcionamieto.
	 * 	
	 * -¿Posible poda?
	 * PODA = cuando la suma de las piezas de la maquina y las del estado superen la cantidad de piezas totales se realizara la poda
	 *  
	 * */

	public SolucionBacktracking backtracking(ArrayList<Maquina> maquinas, int piezasTotales){
		Estado actual = new Estado();
		ArrayList<Maquina> maqValidadas= addMaquinasValidas(maquinas);
		backtracking(actual, maqValidadas, piezasTotales,0);
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

	//para evitar un loop infinito generado por una maquina que no produce piezas, es decir cantPiezas = 0,
	//valido las maquinas previamente de esta forma trabajamos con maquinas validas
	public ArrayList<Maquina> addMaquinasValidas(ArrayList<Maquina> maqAValidar){
		ArrayList<Maquina> maquinas = new ArrayList<>();
		for(int i=0; i<maqAValidar.size();i++) {
			if(maqAValidar.get(i).getCantPiezas() > 0) {
				maquinas.add(maqAValidar.get(i));
			}
		}
		return maquinas;
	}

}
