package TPE;

import java.util.ArrayList;


public class SolucionBacktracking {
	private ArrayList<Maquina> maquinas;
	private int cantPiezas;
	private int puestaFuncionamiento;         
	private int estadosGenerados;


	public SolucionBacktracking() {
		this.maquinas = new ArrayList<>();
	}
	public SolucionBacktracking(int estadosGenerados) {
		this.maquinas = new ArrayList<>();
		this.cantPiezas = 0;
		this.puestaFuncionamiento = 0;
		this.estadosGenerados = estadosGenerados;
	}
	public SolucionBacktracking(ArrayList<Maquina> m, int cantPiezas) {
		this.maquinas = new ArrayList<>();
		this.maquinas.addAll(m);
		this.cantPiezas = cantPiezas;
		this.puestaFuncionamiento = maquinas.size();
	}
	
	public ArrayList<Maquina> getMaquinas(){
		return new ArrayList<>(maquinas);
	}
	
	public int getCantPiezas(){
		return cantPiezas;
	}
	
	public int getPuestaFuncionamiento() {
		return this.puestaFuncionamiento;
	}
	
	public int getEstadosGenerados() {
		return this.estadosGenerados;
	}
	
	public void setEstadosGenerados(int estadosGenerados) {
		this.estadosGenerados = estadosGenerados;
	}
	
	public void getSecuenciaDeMaquinas () {
		for(int i=0;i<maquinas.size();i++) {
			Maquina maquina = maquinas.get(i);
			System.out.print("("+maquina.getNombre()+ "-" + maquina.getCantPiezas() + ") ");
		}
		System.out.println();
	}
	
}
