package TPE;

import java.util.Comparator;

public class ComparatorCantPiezasDesc implements Comparator<Maquina> {
	@Override
	public int compare(Maquina m1, Maquina m2) {
		return Integer.compare(m2.getCantPiezas(), m1.getCantPiezas()); 
	}
}

