package logica;
import java.util.Scanner;
import java.io.*;
public class Main {
	// Ignacio Antonio Pastén Durán 22.067.577-7 ICCI
	
	public static void main(String[] args) throws IOException {
		
		//Carga los usuarios y registros desde archivos
		
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
		//Muestra los menus disponibles 
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
		//Muestra el menu de analisis y lo que puede hacer cada una de sus opciones
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
				mostrarMasRealizada(listaActividades, cantHoras);
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

	private static void mostrarMasRealizada(String[] actividades, int[] cantHoras) {
		
		//Muestra la actividad que mas se realizo y cuantas veces se realizo
		
		String masRepetida = " ";
		int maxHoras = 0;
		
		for (int i = 0; i < actividades.length; i++) {
			
			if (actividades[i] == null) {
				continue;
			}
			
			String actividadActual = actividades[i];
			int suma = 0;
			
			for(int j = 0; j < actividades.length; j++) {
			
				if (actividades[j] == null) {
					continue;
				}
				
				if (actividades[i].equals(actividades[j])) {
					suma+=cantHoras[j];
				}
				
			}
				if (suma > maxHoras) {
					
					maxHoras = suma;
					masRepetida = actividadActual;
				
			}
		}
		
		System.out.println("La actividad más realizada fue: " + masRepetida + 
				" un total de " + maxHoras + " horas");
		
	}
		
	private static void masRealizadaPorUsuario(String[] actividades, String[] usuarios,int[] cantHoras) {
		
		//Muestra la actividad mas realizada por cada uno de los usuarios y cuantas horas duro contando todas
		
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
		
		//Muestra al usuario mas procrastinador y cuantas horas gasto en total
		
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
		
	//Muestra todas las lineas del archivo pero de una forma mas bonita
		
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
		
		//Muestra el menu de usuarios y las opciones disponibles para usar
		
		Scanner s = new Scanner(System.in);
		System.out.println("Usuario: ");
		String user = s.nextLine();
		
		System.out.println("Contraseña: ");
		String pass = s.nextLine();
		
		boolean acceso = false;
		
		for(int i = 0; i < listaNombre.length; i++) {
			if (listaNombre[i] != null && contraseñas[i] != null && listaNombre[i].equals(user) && contraseñas[i].equals(pass)) {
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
					try {
						modificarActividad(user, listaUser, listaFechas, cantHoras, listaActividades);
					} catch (IOException e) {
						System.out.println("Error al modificar");
					}
					break;
				case "3":
					try {
					eliminarActividad(user, listaUser, listaFechas, cantHoras, listaActividades);
						
					} catch (IOException e) {
						System.out.println("Error al eliminar");
					}
					break;
				case "4":
					try {
						cambiarContraseña(user, listaNombre, contraseñas);
					} catch (IOException e) {
						System.out.println("Error al cambiar contraseña");
					}
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
		
		//Registra una actividad (añade) y modifica el txt
		
		Scanner s = new Scanner(System.in);
		
		System.out.println("Ingrese fecha: ");
		String fecha = s.nextLine();
		
		System.out.println("Ingrese cantidad de horas: ");
		int horas = Integer.parseInt(s.nextLine());
		
		System.out.println("Ingrese actividad: ");
		String actividad = s.nextLine();
		
		for (int i = 0; i < listaUser.length; i++) {
			
			if (listaUser[i] == null) {
				listaUser[i] = user;
				listaFechas[i] = fecha;
				cantHoras[i] = horas;
				listaActividades[i] = actividad;
				break;
			}
			
		}
		
		FileWriter fw = new FileWriter("Registros.txt", true);
		BufferedWriter bw = new BufferedWriter(fw);
		
		bw.write(user + ";" + fecha + ";" + horas + ";" + actividad);
		bw.newLine();
		
		bw.close();
		
		System.out.println("Actividad registrada correctamente");
		
		
	}
	private static void actualizarArchivo(String[] listaUser, String[] listaFechas, int[] cantHoras, String[] listaActividades) throws IOException {
		
		//Reescribe el archivo de registros actualizado
		
		BufferedWriter bw = new BufferedWriter(new FileWriter("Registros.txt")); 
		
		for (int i = 0; i < listaUser.length;i++) {
			if (listaUser[i] != null) {
				
				bw.write(listaUser[i] + ";" + listaFechas[i] + ";" + cantHoras[i] + ";" + listaActividades[i]);
				bw.newLine();
			}
		}
		
		bw.close();
		
	}
	private static void modificarActividad(String user, String[] listaUser, String[] listaFechas, int[] cantHoras, String[] listaActividades) throws IOException  {
		
		//Modifica una actividad ya existente y podemos modificar tanto fecha, duracion o actividad
		
		Scanner s = new Scanner(System.in);
		System.out.println("Cual actividad deseas modificar?");
		System.out.println("0) Regresar.");
		
		for (int i = 0; i < listaUser.length; i++) {
			
			if (listaUser[i] != null && listaUser[i].equals(user)) {
				System.out.println(i + ")" + listaUser[i] + ";" + listaFechas[i] + ";" + cantHoras[i] + ";" + listaActividades[i]);
			}
		}
		int posicion;
		try {
		posicion = Integer.parseInt(s.nextLine());
		} catch (NumberFormatException e) {
			System.out.println("Debe ingresar número válido");
			return;
		}
		if (posicion < 0 || posicion >= listaUser.length) {
			System.out.println("Índice fuera de rango");
			return;
		}
		if (posicion == 0) {
			return;
		}
		
		if (listaUser[posicion] == null || !listaUser[posicion].equals(user)) {
			System.out.println("Opción inválida");
			return;
		}
		
		int opcion = -1;
		
		do {
			System.out.println("Que deseas modificar?\r\n"
					+ "\r\n"
					+ "0) Regresar.\r\n"
					+ "1) Fecha\r\n"
					+ "2) Duracion\r\n"
					+ "3) Tipo de actividad");

			try {
			opcion = Integer.parseInt(s.nextLine());
			} catch (NumberFormatException e) {
				System.out.println("Ingrese un número válido");
				continue;
			}
			switch (opcion) {
				case 1:
					System.out.println("0) Regresar.");
					System.out.println("Ingrese nueva fecha: ");
					String nuevaFecha = s.nextLine();
					
					if (!nuevaFecha.equals("0")) {
						listaFechas[posicion] = nuevaFecha;
					}
					break;
			
				case 2:
					System.out.println("0) Regresar.");
					System.out.println("Ingrese nueva duración: ");
					String nuevaDuracion = s.nextLine();
					
					if (!nuevaDuracion.equals("0")) {
						cantHoras[posicion] = Integer.parseInt(nuevaDuracion);
					}
					break;
					
				case 3:
					System.out.println("0) Regresar.");
					System.out.println("Ingrese nueva actividad: ");
					String nuevaActividad = s.nextLine();
					
					if (!nuevaActividad.equals("0")) {
						listaActividades[posicion] = nuevaActividad;
					}
					break;
			}

		} while (opcion != 0);
		
		System.out.println("Operación finalizada");
		
		actualizarArchivo(listaUser, listaFechas, cantHoras, listaActividades);
		
	}
	private static void eliminarActividad(String user, String[] listaUser, String[] listaFechas, int[] cantHoras, String[] listaActividades) throws IOException  {
		
		//Permite eliminar una actividad ya existente y lo modifica en el archivo
		
		Scanner s = new Scanner(System.in);
		System.out.println("Cual actividad deseas eliminar?");
		System.out.println("0) Regresar.");
		
		for (int i = 0; i < listaUser.length; i++) {
			
			if (listaUser[i] != null && listaUser[i].equals(user)) {
				System.out.println(i + ")" + listaUser[i] + ";" + listaFechas[i] + ";" + cantHoras[i] + ";" + listaActividades[i]);
			}
		}
		
		int posicion = Integer.parseInt(s.nextLine());
		if (posicion == 0) {
			System.out.println("Operación cancelada");
			return;
		}
		if (listaUser[posicion] == null || !listaUser[posicion].equals(user)) {
			System.out.println("Opción inválida");
			return;
		}
		
		listaUser[posicion] = null;
		listaFechas[posicion] = null;
		cantHoras[posicion] = 0;
		listaActividades[posicion] = null;
		
		System.out.println("Actividad eliminada correctamente");
		
		actualizarArchivo(listaUser, listaFechas, cantHoras, listaActividades);
		
	}
	private static void cambiarContraseña(String user, String[] listaNombre, String[] contraseñas) throws IOException {
		
		//Permite modificar una contraseña mientras que sepa la contraseña anterior y la modifica en el archivo
		
		Scanner s = new Scanner(System.in);
		
		System.out.println("Ingrese contraseña actual: ");
		String actual = s.nextLine();
		
		int posicion = -1;
		
		for (int i = 0; i < listaNombre.length; i++) {
			if (listaNombre[i] != null && listaNombre[i].equals(user)) {
				posicion = i;
				break;
			}
		}
		
		if (posicion == -1 || !contraseñas[posicion].equals(actual)) {
			System.out.println("Contraseña incorrecta");
			return;
		}
		
		System.out.println("Ingrese nueva contraseña: ");
		String nueva = s.nextLine();
		
		contraseñas[posicion] = nueva;
		
		BufferedWriter bw = new BufferedWriter(new FileWriter("Usuarios.txt"));
		
		for (int i = 0; i < listaNombre.length;i++) {
			
			if (listaNombre[i] != null) {
				
				bw.write(listaNombre[i] + ";" + contraseñas[i]);
				bw.newLine();
			}
		}
		
		bw.close();
		
		System.out.println("Contraseña cambiada correctamente");
		
	}
}
