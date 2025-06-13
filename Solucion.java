package TPE;

import java.util.ArrayList;


public class Solucion {
	private ArrayList<Maquina> maquinas;
	private int cantPiezas;
	private int puestaFuncionamiento;         //cantidad puestas en funcionamiento
	private int estadosGenerados;
	private int totalCandidatos;

	public Solucion() {
		this.maquinas = new ArrayList<>();
	}
	public Solucion(int estadosGenerados) {
		this.maquinas = new ArrayList<>();
		this.cantPiezas = 0;
		this.puestaFuncionamiento = 0;
		this.estadosGenerados = estadosGenerados;
	}
	public Solucion(ArrayList<Maquina> m, int cantPiezas) {
		this.maquinas = new ArrayList<>();
		this.maquinas.addAll(m);
		this.cantPiezas = cantPiezas;
		this.puestaFuncionamiento = maquinas.size();
	}

	public Solucion(ArrayList<Maquina> m, int cantPiezas, int estadosGenerados) {
		this.maquinas = new ArrayList<>();
		this.maquinas.addAll(m);
		this.cantPiezas = cantPiezas;
		this.puestaFuncionamiento = maquinas.size();
		this.estadosGenerados = estadosGenerados;
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
	
	//-----para greedy---
	
	public void agregarMaquina(Maquina m) {
		maquinas.add(m);
		this.cantPiezas += m.getCantPiezas();
	}
	
	public int getTotalCandidatos() {
		return this.totalCandidatos;
	}
	public void setTotalCandidatos(int candidatos) {
		this.totalCandidatos = candidatos;
	}
	
	public void setPuestasEnFuncionamiento() {
		this.puestaFuncionamiento = maquinas.size();
	}
	
}
