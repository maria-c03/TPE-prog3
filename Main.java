package TPE;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.io.InputStream;


public class Main {

	public static void main(String[] args) {
		//para poder leer el archivo text
		ArrayList<Maquina> maquinas = new ArrayList<>();
		int piezasTotales = 0;
		InputStream is = Main.class.getResourceAsStream("text");
		if (is == null) {
			System.out.println("No se encontró el archivo.");
			return;
		}
		try (BufferedReader br = new BufferedReader(new InputStreamReader(is))) {
			String line;
			boolean primeraLinea = true;

			while ((line = br.readLine()) != null) {
				line = line.trim();
				if (line.isEmpty()) continue;

				if (primeraLinea) {
					piezasTotales = Integer.parseInt(line);
					primeraLinea = false;
				} else {
					String[] partes = line.split(",");
					String nombre = partes[0].trim();
					int piezas = Integer.parseInt(partes[1].trim());
					maquinas.add(new Maquina(nombre, piezas));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		//se imprime en pantalla el total de piezas y las maquinas con sus respectivas piezas
		System.out.println("Piezas totales: " + piezasTotales);
		for (Maquina m : maquinas) {
			System.out.println("Máquina: " + m.getNombre() + " - Cantidad de piezas: " + m.getCantPiezas());
		}
		
		// --RESULTADOS--
		
		// --BACKTRACKING--
		Backtracking bc =new Backtracking(maquinas);
		SolucionBacktracking solBack = bc.backtracking(maquinas, piezasTotales);
		System.out.println( "Solucion Backtracking ");
		if(solBack.getMaquinas().isEmpty()) {
			System.out.println("No hay solución");			
		}
		System.out.print("Secuencia de Maquinas= ");
		solBack.getSecuenciaDeMaquinas();
		System.out.println("Cantidad de Piezas producidas= " + solBack.getCantPiezas());
		System.out.println("Cantidad de Puestas en funcionamiento = " + solBack.getPuestaFuncionamiento());
		System.out.println("Cantidad de Estados generados = " + solBack.getEstadosGenerados());
	
		
		// --GREEDY-- 
		Greedy g = new Greedy(piezasTotales);
		SolucionGreedy solGreedy = g.greedy(maquinas);
		System.out.println( "Solucion Greedy ");
		if(solGreedy==null || solGreedy.getMaquinas().isEmpty()) {
			System.out.println("No hay solución");			
		}else {
			solGreedy.getSecuenciaDeMaquinas();
			System.out.println("Cantidad de Piezas producidas= " + solGreedy.getCantPiezas());
			System.out.println("Cantidad de Puestas en funcionamiento = " + solGreedy.getPuestaFuncionamiento());
			System.out.println("Cantidad de Candidatos = " + solGreedy.getTotalCandidatos());	
		}
	
	}
}