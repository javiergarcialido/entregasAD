package com.jgarcia;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlRootElement;

import java.io.EOFException;
import java.io.IOException;
import java.io.RandomAccessFile;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Videojugador {
    private static final int maxString = 46;
    private static final int TAMAÑO = 2*(maxString + 1) + 2*Integer.SIZE/8;
    private int id;
    public String nombre;
    public int edad;

    public Videojugador() {
    }

    public Videojugador(int id, String nombre, int edad) {
        this.id = id;
        this.nombre = nombre;
        this.edad = edad;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public void show()
    {
        if (id != 0)
            System.out.printf("\n%d\t%s\n\t%d\n---\n", id, nombre, edad);
        else
            System.out.println("Este videojugador no existe");
    }

    public void writeFile(RandomAccessFile raf)
    {
        try
        {
            raf.seek (((id - 1)*TAMAÑO));
            raf.writeInt(id);
            raf.writeBytes(nombre + '\n');
            raf.writeInt(edad);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public void readFile (RandomAccessFile raf)
    {
        try
        {
            raf.seek((id-1)*TAMAÑO);
            id = raf.readInt();
            if (id != 0)
            {
                nombre = raf.readLine();
                edad = raf.readInt();
            }
        }
        catch (EOFException i)
        {
            System.out.printf("Este videojugador no exite\n");
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public String toString() {
        return "Videojugador{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", edad=" + edad +
                '}';
    }
}
