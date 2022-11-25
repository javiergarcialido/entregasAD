package com.jgarcia.ut1;

import com.jgarcia.Videojugador;
import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ej3 {
    public static void main(String[] args) {
        escribeXML();
        leeXML();
    }

    public static void escribeXML() {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            DOMImplementation dom = builder.getDOMImplementation();
            //En lugar de xml le ponemos videojugador para poder el documento desde jaxb
            Document documento = dom.createDocument(null, "videojugador", null);
            Element raiz = documento.createElement("videojugadores");
            documento.getDocumentElement().appendChild(raiz);
            Element nodoVideojugador = null, nodoDatos = null;
            Text texto = null;

            List<Videojugador> listaVideojugadores = new ArrayList<>();
            Videojugador videojugador1 = new Videojugador(1, "manolito99", 23);
            Videojugador videojugador2 = new Videojugador(2, "pepe1234", 29);
            listaVideojugadores.add(videojugador1); listaVideojugadores.add(videojugador2);

            for (Videojugador videojugador : listaVideojugadores) {
                nodoVideojugador = documento.createElement("videojugador");
                raiz.appendChild(nodoVideojugador);
                //ID
                nodoDatos = documento.createElement("id");
                nodoVideojugador.appendChild(nodoDatos);
                texto = documento.createTextNode(String.valueOf(videojugador.getId()));
                nodoDatos.appendChild(texto);
                //NOMBRE
                nodoDatos = documento.createElement("nombre");
                nodoVideojugador.appendChild(nodoDatos);
                texto = documento.createTextNode(videojugador.getNombre());
                nodoDatos.appendChild(texto);
                //EDAD
                nodoDatos = documento.createElement("edad");
                nodoVideojugador.appendChild(nodoDatos);
                texto = documento.createTextNode(String.valueOf(videojugador.getEdad()));
                nodoDatos.appendChild(texto);
            }

            Source source = new DOMSource(documento);
            Result resultado = new StreamResult(new File("videojugadores.xml"));
            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.transform(source,resultado);

        }
        catch (ParserConfigurationException | TransformerException e) {
            throw new RuntimeException(e);
        }
    }

    public static void leeXML() {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document documento = builder.parse(new File("videojugadores.xml"));

            NodeList videojugadores = documento.getElementsByTagName("videojugador");

            for (int i = 0; i < videojugadores.getLength() ; i++) {
                Node videojugador = videojugadores.item(i);
                Element elemento = (Element) videojugador;
                System.out.println(elemento.getElementsByTagName("id").item(0).getChildNodes().item(0).getNodeValue());
                System.out.println(elemento.getElementsByTagName("nombre").item(0).getChildNodes().item(0).getNodeValue());
                System.out.println(elemento.getElementsByTagName("edad").item(0).getChildNodes().item(0).getNodeValue());
            }
        }
        catch (ParserConfigurationException | SAXException | IOException e) {
            throw new RuntimeException(e);
        }
    }
}
