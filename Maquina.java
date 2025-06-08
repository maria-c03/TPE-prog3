package TPE;

public class Maquina {
	private String nombre;
	private int cantPiezas;
	
	Maquina(String n, int p){
		nombre = n;
		cantPiezas =p;
	}
		
	public String getNombre() {
		return this.nombre;
	}
	
	public void setNombre(String n) {
		nombre = n;
	}
	
	public int getCantPiezas() {
		return this.cantPiezas;
	}
	
	@Override
	public boolean equals (Object o) {
		try {
			Maquina m = (Maquina) o;
			return this.getNombre().equals(m.getNombre())
					&& (this.getCantPiezas() == m.getCantPiezas());
		}catch(Exception e){
			return false;
		}
	}
	 @Override
	    public String toString() {
	        return nombre + "(" + cantPiezas + ")";
	    }
	
}
