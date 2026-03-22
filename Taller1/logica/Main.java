package logica;
import java.util.Scanner;
import java.io.*;
public class Main {
	// Ignacio Antonio Pastén Durán 22.067.577-7 ICCI
	
	public static void main(String[] args) throws IOException {
		
		String[] listaNombre = new String[3];
		String[] contraseñas = new String[3];
		
		String[] listaUser = new String[300];
		String[] listaFechas = new String[300];
		int[] cantHoras = new int[300];
		String[] listaActividades = new String[300];
		
		
		File f = new File("Usuarios.txt");
		Scanner sc = new Scanner(f);
		
		int i = 0;
		
		while (sc.hasNextLine()) {
			
		 String linea = sc.nextLine().strip();
			
		 String[] partes = linea.split(";");
		
		 String id = partes[0];
		 String contraseña = partes[1];
		
		 listaNombre[i] = id;
		 contraseñas[i] = contraseña;
		 i++;
		}
		 f = new File("Registros.txt");
		 sc = new Scanner(f);
		 
		 i = 0;
		 
		 while (sc.hasNextLine()) {
			
			String linea = sc.nextLine().strip();
			String[] partes = linea.split(";");
			
			String iduser = partes[0];
			String fecha = partes[1];
			int horas = Integer.parseInt(partes[2]);
			String actividad = partes[3];
			
			listaUser[i] = iduser;
			listaFechas[i] = fecha;
			cantHoras[i] = horas;
			listaActividades[i] = actividad;
		 }
				 
		sc.close();
		
		mostrarMenu();
		
	}
	
	public static void  mostrarMenu() {
		String opcion;
		Scanner s = new Scanner(System.in);
		do {
			System.out.println("1) Menu de Usuarios\r\n"
					+ "2) Menu de Analisis\r\n"
					+ "3) Salir");
			opcion = s.nextLine();
			switch (opcion) {
			
				case "1":
					mostrarMenuUsuarios();
					break;
				
				case "2":
					mostrarMenuAnalisis();
					break;
				case"3":
					System.out.println("Saliendo... ");
					break;
				default:
					System.out.println("Ingrese opción válida");
					break;
			}
		} while (!opcion.equals("3"));
		 
		
		s.close();
		
	}

	private static void mostrarMenuAnalisis() {
		// TODO Auto-generated method stub
		
	}

	private static void mostrarMenuUsuarios() {
		// TODO Auto-generated method stub
		
	}
	
}
