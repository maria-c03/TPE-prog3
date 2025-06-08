package TPE;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.io.InputStream;


public class Main {

	public static void main(String[] args) {
		List<Maquina> maquinas = new ArrayList<>();
		int piezasTotales = 0;
		InputStream is = Main.class.getResourceAsStream("text");
		//URL url = Main.class.getResource("text");
		//System.out.println("URL del archivo: " + url);
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

		System.out.println("Piezas totales: " + piezasTotales);
		for (Maquina m : maquinas) {
			System.out.println("Máquina: " + m.getNombre() + " - Cantidad de piezas: " + m.getCantPiezas());
		}
	}
}