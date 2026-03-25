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
		
		mostrarMenu(listaActividades, listaUser, listaFechas, cantHoras, listaNombre, contraseñas);
		
	}
	
	public static void  mostrarMenu(String[] listaActividades, String[] listaUser, String[] listaFechas, int[] cantHoras,String[] listaNombre, String[] contraseñas) {
		String opcion;
		Scanner s = new Scanner(System.in);
		do {
			System.out.println("1) Menu de Usuarios\r\n"
					+ "2) Menu de Analisis\r\n"
					+ "3) Salir");
			opcion = s.nextLine();
			switch (opcion) {
			
				case "1":
					mostrarMenuUsuarios(listaUser, listaNombre, contraseñas, listaActividades, listaFechas, cantHoras);
					break;
				
				case "2":
					mostrarMenuAnalisis(listaActividades, listaUser, listaFechas, cantHoras);
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

	private static void mostrarMenuAnalisis(String[] listaActividades, String[] listaUser, String[] listaFechas, int[] cantHoras) {
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
				masRealizadaPorUsuario(listaActividades, listaUser, cantHoras);
				break;
			case "3":
				masProcrastinacion(listaUser, cantHoras);
				break;
			case "4":
				todasActividades(listaActividades, listaFechas, listaUser, cantHoras);
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
		
	private static void masRealizadaPorUsuario(String[] actividades, String[] usuarios,int[] cantHoras) {
		
		for (int i = 0; i < usuarios.length; i++) {
			
			if (usuarios[i] == null) {
				continue;
			}
			
			boolean yaVisto = false;
			for (int x = 0; x < i; x++) {
				if (usuarios[x] != null && usuarios[x].equals(usuarios[i])) {
					yaVisto = true;
					break;
				}
			}
			
			if (yaVisto) {
				continue;
			}
			
			String usuario = usuarios[i];
			String masRepetida = " ";
			int mayorHoras = 0;
			
			for (int j = 0; j < actividades.length; j++ ) {
				
				if (usuarios[j] == null || actividades[j] == null ) {
					continue;
				}
				
				if (usuarios[j].equals(usuario)) {
					
					int sumaHoras = 0;
					

					for (int k = 0; k < actividades.length; k++) {
						
						if (usuarios[k] == null || actividades[k] == null) {
							continue;
						}
						
						if (usuarios[k].equals(usuario) && actividades[k].equals(actividades[j])) {
							
							sumaHoras += cantHoras[k];
						}
						
						if (sumaHoras > mayorHoras) {
							mayorHoras = sumaHoras;
							masRepetida = actividades[j];
					}
					
					}
					
				}
				
			}
			
			 System.out.println("* " + usuario + " -> " + masRepetida + 
		                " -> con " + mayorHoras + " horas registradas");
		}
		
		

		
	}
	
	private static void masProcrastinacion(String[] usuarios, int[] cantHoras) {
		
		String usuarioMayor = " ";
		int mayorHoras = 0;
		
		for (int i = 0; i < usuarios.length; i++) {
			
			if (usuarios[i] == null) {
				continue;
			}
			
			boolean yaVisto = false;
			for (int x = 0; x < i; x++) {
				if (usuarios[x] != null && usuarios[x].equals(usuarios[i])) {
					yaVisto = true;
					break;
				}
			}
			
			if (yaVisto) {
				continue;
			}
			
			String usuario = usuarios[i];
			int sumaHoras = 0;
			
			for (int j = 0; j < usuarios.length; j++) {
				
				if (usuarios[j] == null) {
					continue;
				}
				
				if (usuarios[j].equals(usuario)) {
					
					sumaHoras += cantHoras[j];
				}
			}
			
			if (sumaHoras > mayorHoras) {
				mayorHoras = sumaHoras;
				usuarioMayor = usuario;
			}
		}
		
		System.out.println("El usuario con mayor procrastinacion es: " 
	            + usuarioMayor + " con " + mayorHoras + " horas");
		
	}
	
	private static void todasActividades(String[] listaActividades, String[] listaUser, String[] listaFechas, int[] cantHoras) {

	System.out.println("Lista de actividades: ");
	
	for (int i = 0; i < listaActividades.length; i ++) {
		
		if (listaActividades[i] == null) {
			continue;
		}
		
		System.out.println(listaUser[i] + " -> " +
                listaFechas[i] + " -> " +
                cantHoras[i] + " hrs -> " +
                listaActividades[i]);
			
		
		
		}
	}
		
	
	private static void mostrarMenuUsuarios(String[] listaUser, String[] listaNombre, String[] contraseñas,
                                        String[] listaActividades, String[] listaFechas, int[] cantHoras) {
		Scanner s = new Scanner(System.in);
		System.out.println("Usuario: ");
		String user = s.nextLine();
		
		System.out.println("Contraseña: ");
		String pass = s.nextLine();
		
		boolean acceso = false;
		
		for(int i = 0; i < listaNombre.length; i++) {
			if (listaNombre[i].equals(user) && contraseñas[i].equals(pass)) {
				acceso = true;
				break;
			}
		}
		
		if (acceso) {
			System.out.println("Acceso correcto!");
			System.out.println();
			System.out.println("Bienvenido " + user + "!");
		
			String opcion;
			
			do {
			
				System.out.println("Que deseas realizar?\r\n"
						+ "\r\n"
						+ "1) Registrar actividad.\r\n"
						+ "2) Modificar actividad.\r\n"
						+ "3) Eliminar actividad.\r\n"
						+ "4) Cambiar contraseña.\r\n"
						+ "5) Salir.");
			
				opcion = s.nextLine();
				switch (opcion) {
			
				case "1":
					try{
						registrarActividad(user, listaUser, listaFechas, cantHoras, listaActividades);
					} catch (IOException e) {
						System.out.println("Error al guardar");
					}
					break;
				case "2":
					modificarActividad();
					break;
				case "3":
					eliminarActividad();
					break;
				case "4":
					cambiarContraseña();
					break;
				case "5":
					System.out.println("Saliendo...");
					break;
				default:
					System.out.println("Ingrese opción válida");
					break;
				}	
			} while (!opcion.equals("5"));
		} else {
			System.out.println("Usuario o contraseña incorrectos.");
		}
	}

	private static void registrarActividad(String user, String[] listaUser, String[] listaFechas, int[] cantHoras, String[] listaActividades) throws IOException {
		
		
		
		
	}
	private static void modificarActividad() {
		// TODO Auto-generated method stub
		
	}
	private static void eliminarActividad() {
		// TODO Auto-generated method stub
		
	}
	private static void cambiarContraseña() {
		// TODO Auto-generated method stub
		
	}



	
}
