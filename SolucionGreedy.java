package TPE;

import java.util.ArrayList;


public class SolucionGreedy {

	private ArrayList<Maquina> maquinas;
	private int cantPiezas;
	private int puestaFuncionamiento;
	private int totalCandidatos;

	public SolucionGreedy() {
		this.maquinas = new ArrayList<>();
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

	public void getSecuenciaDeMaquinas () {
		for(int i=0;i<maquinas.size();i++) {
			Maquina maquina = maquinas.get(i);
			System.out.print("("+maquina.getNombre()+ "-" + maquina.getCantPiezas() + ") ");
		}
		System.out.println();
	}

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


