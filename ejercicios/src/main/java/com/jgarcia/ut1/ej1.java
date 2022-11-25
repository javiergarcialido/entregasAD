package com.jgarcia.ut1;

import com.jgarcia.Videojugador;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Scanner;

public class ej1
{
    public static Scanner escaner = new Scanner (System.in);
    public static Videojugador videojugador;
    public static RandomAccessFile raf;

    public static void main( String[] args )
    {
        try {
            RandomAccessFile raf = new RandomAccessFile("videojugadores.dat", "rw");

            System.out.printf ( "GESTIÓN DE VIDEOJUGADORES\n\t1. Introducir nuevo jugador\n\t2. Consultar datos de videojugador\n\t0. Salir \nElige una opción: \n");

            int opcion = escaner.nextInt();

            switch (opcion) {
                case 1: registraVideojugador(); break;
                case 2: consultaVideojugaor(); break;
                default: if (raf != null) {
                    try {
                        raf.close();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
                        System.out.println("Fin del programa.");
                }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void registraVideojugador() {
        //Creamos un videojugador con los parametros que diga el usuario y lo escribimos
        System.out.println("Introduce su id: ");
        int i = escaner.nextInt();
        escaner.nextLine();
        System.out.println("Introduce su nombre (10 caracteres max): ");
        String n = escaner.nextLine();
        System.out.println("Introduce su edad: ");
        int e = escaner.nextInt();
        escaner.nextLine();
        videojugador = new Videojugador(i, n, e);

        try {
            raf.writeInt(videojugador.getId());

            StringBuffer sb = new StringBuffer(videojugador.getNombre());
            sb.setLength(10);

            raf.writeChars(sb.toString());
            raf.writeInt(videojugador.getId());
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }

    }

    public static void consultaVideojugaor() {
        System.out.println("Introduce el codigo del videojugador");
        int cod = escaner.nextInt();

        try {
            raf.seek(cod*28);
            System.out.println(raf.readInt());
            String nombre = "";
            for (int i = 0; i < 10; i++) {
                nombre += raf.readChar();
            }
            System.out.println(nombre);
            System.out.println(raf.readInt());

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
