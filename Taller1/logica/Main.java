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
			i++;
		 }
				 
		sc.close();
		
		mostrarMenu(listaActividades, listaUser, cantHoras);
		
	}
	
	public static void  mostrarMenu(String[] listaActividades, String[] listaUser, int[] cantHoras) {
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
					mostrarMenuAnalisis(listaActividades, listaUser, cantHoras);
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

	private static void mostrarMenuAnalisis(String[] listaActividades, String[] listaUser, int[] cantHoras) {
		String opcion;
		Scanner s = new Scanner(System.in);
		
		do {
			
			System.out.println("Bienvenido al menu de analisis!\r\n"
					+ "\r\n"
					+ "Que deseas realizar?\r\n"
					+ "\r\n"
					+ "1) Actividad más realizada\r\n"
					+ "2) Actividad más realizada por cada usuario\r\n"
					+ "3) Usuario con mayor procastinacion\r\n"
					+ "4) Ver todas las actividades\r\n"
					+ "5) Salir");
			
			opcion = s.nextLine();
			switch (opcion) {
			
			case "1":
				mostrarMasRealizada(listaActividades);
				break;
			case "2":
				masRealizadaPorUsuario(listaActividades, listaUser);
				break;
			case "3":
				masProcrastinacion(listaUser, cantHoras);
				break;
			case "4":
				todasActividades(listaActividades);
				break;
			case "5":
				System.out.println("Saliendo...");
				break;
			default:
				System.out.println("Ingrese opción válida");
				break;
			}	
		} while (!opcion.equals("5"));
		
	}

	private static void mostrarMasRealizada(String[] actividades) {
		
		String masRepetida = " ";
		int mayorCant = 0;
		
		for (int i = 0; i < actividades.length; i++) {
			
			if (actividades[i] == null) {
				continue;
			}
			
			int contador = 0;
			
			for(int j = 0; j < actividades.length; j++) {
			
				if (actividades[j] == null) {
					continue;
				}
				
				if (actividades[i].equals(actividades[j])) {
					contador++;
				}
				
				if (contador > mayorCant) {
					
					mayorCant = contador;
					masRepetida = actividades[i];
				}
				
			}
		}
		
		System.out.println("La actividad más realizada fue: " + masRepetida + 
				" un total de " + mayorCant + " veces");
		
	}
		
	private static void todasActividades(String[] listaActividades) {
		// TODO Auto-generated method stub
		
	}

	private static void masRealizadaPorUsuario(String[] actividades, String[] usuarios) {
		// TODO Auto-generated method stub
		
	}
	private static void masProcrastinacion(String[] listaUser, int[] cantHoras) {
		// TODO Auto-generated method stub
		
	}


	

	private static void mostrarMenuUsuarios() {
		// TODO Auto-generated method stub
		
	}
	
}
