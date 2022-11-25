package com.jgarcia.ut1;

import com.jgarcia.Videojugador;
import jakarta.xml.bind.*;

import java.io.File;

public class ej4 {
    public static void main(String[] args) {
        escribeXML();
        leeXML();
    }

    public static void escribeXML() {
        try {
            Videojugador videojugador = new Videojugador(1, "manolo99",23);
            JAXBContext contexto = JAXBContext.newInstance(videojugador.getClass() );
            Marshaller marshaller = contexto.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT,
                    Boolean.TRUE);
            marshaller.marshal(videojugador, System.out);
        } catch (JAXBException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public static void leeXML() {
        try {
            JAXBContext context = JAXBContext.newInstance( Videojugador.class );
            Unmarshaller unmarshaller = context.createUnmarshaller();
            Videojugador videojugador = (Videojugador) unmarshaller.unmarshal(new File("videojugadores.xml"));
            System.out.println(videojugador.getId());
            System.out.println(videojugador.getNombre());
            System.out.println(videojugador.getEdad());
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }
}
