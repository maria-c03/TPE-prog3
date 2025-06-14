package TPE;
import java.util.ArrayList;
import java.util.List;

public class Estado {
	
	private List<Maquina> maquinas;
	private int cantPiezas;
	
	public Estado() {
		maquinas=new ArrayList<>();
		this.cantPiezas = 0;
	}
	
	public ArrayList<Maquina> getMaquinas(){
		return new ArrayList<>(maquinas);
	}
	
	public void addMaquina(Maquina m) {
		maquinas.add(m);
		cantPiezas += m.getCantPiezas(); //cuando agrego la maquina actualizo la cantidad de piezas del estado
	}
	public int getTam() {
		return maquinas.size();
	}
	
	public int getCantPiezas() {
		return this.cantPiezas;
	}

	public void remove(Maquina m) {
		maquinas.remove(m);
		cantPiezas-=m.getCantPiezas();
	}
}
