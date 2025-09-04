/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package prueba2parcial2;

    import java.io.FileReader;
    import java.io.FileWriter;
    import java.io.IOException;
    import java.util.ArrayList;
    import java.util.Scanner;

    /**
     *
     * @author ljmc2
     */
    public class Prueba2Parcial2 {

        String archivo = "tareas.txt";

        public static void main(String[] args) {
            Scanner lea = new Scanner(System.in).useDelimiter("\n");
            int opcion = 0;
            Prueba2Parcial2 tareas = new Prueba2Parcial2();
            do {
                System.out.println("\n");
                System.out.println("GESTOR DE TAREAS"
                        + "\n==============================="
                        + "\n1. Agregar tarea"
                        + "\n2. Mostrar tareas"
                        + "\n3. Completar tarea"
                        + "\n4. Salir"
                        + "\nSeleccione una opcion: ");
                opcion = lea.nextInt();
                switch (opcion) {
                    case 1:
                        tareas.agregarTarea();
                        break;
                    case 2:
                        tareas.mostrarTareas();
                        break;
                    case 3:
                        tareas.completarTarea();
                        break;
                    case 4:
                        System.out.println("Programa finalizado.");
                        System.exit(0);
                        break;
                    default:
                        System.out.println("Opcion invalida.");
                        break;
                }
            } while (opcion != 4);

        }

        public void agregarTarea() {
            System.out.println("Ingrese la nueva tarea: ");
            Scanner lea = new Scanner(System.in).useDelimiter("\n");
            String tarea = lea.next();

            try {

                FileWriter fw = new FileWriter(archivo, true);
                fw.write("[ ] " + tarea + "\n");
                fw.close();
                System.out.println("Tarea agregada: " + tarea);

            } catch (IOException e) {
                System.out.println("Hubo un error agregando la tarea.");
                e.getMessage();
            }

        }

        public void mostrarTareas() {
            System.out.println("LISTA DE TAREAS"
                    + "\n======================");

            try {
                FileReader fr = new FileReader(archivo);
                String linea = "";
                int contador = 0;
                int num = 1;

                while ((contador = fr.read()) != -1) {
                    if (contador == '\n') {
                        System.out.println(num + ". " + linea);
                        linea = "";
                        num++;
                    } else {
                        linea += (char) contador;
                    }
                }

                if (!linea.isEmpty()) {
                    System.out.println(num + ". " + linea);
                }

            } catch (IOException e) {
                System.out.println("Hubo un error mostrando las tareas.");
            }

        }

        public void completarTarea() {
            ArrayList<String> tareas = new ArrayList<>();
            Scanner lea = new Scanner(System.in).useDelimiter("\n");
            try {
                FileReader fr = new FileReader(archivo);
                String linea = "";
                int contador = 0;

                while ((contador = fr.read()) != -1) {
                    if (contador == '\n') {
                        tareas.add(linea);
                        linea = "";
                    } else {
                        linea += (char) contador;
                    }
                }

                if (!linea.isEmpty()) {
                    tareas.add(linea);
                }

            } catch (IOException e) {
                System.out.println("No hay tareas anotadas.");
            }

            if (tareas.isEmpty()) {
                System.out.println("No hay tareas por completarse.");
                return;
            }

            System.out.println("Número de tarea a completar: ");
            int n = lea.nextInt();

            if (n < 1 || n > tareas.size()) {
                System.out.println("Número inválido.");
                return;
            }

            String tarea = tareas.get(n - 1);

            if (tarea.startsWith("[ ]")) {
                tarea = tarea.replaceFirst("\\[\\]", "[✓]");
                tareas.set(n - 1, tarea);

                try {
                    FileWriter fw = new FileWriter(archivo);
                    for (String t : tareas) {
                        fw.write(t + "\n");
                        
                    }
                    fw.close();

                } catch (IOException e) {
                    System.out.println("Hubo un error al actualizar las tareas.");
                }

                System.out.println("\n✓ Tarea #" + n + " completada :" + tarea.substring(4));

            } else {
                System.out.println("Tarea ya completada.");
            }

        }

    }
