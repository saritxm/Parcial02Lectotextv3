package Servidor.Controller;

import java.io.IOException;
import java.util.Locale;
import java.util.Properties;
import java.util.Scanner;
import javax.sound.midi.Synthesizer;
import Servidor.Model.ServidorConexion;
import Servidor.View.*;

public class ServidorControl {
    private ServidorConexion servidor;
    private Properties properties;
    private FileChooser fileChooser;
    private int p1;
    private Aviso aviso;
    private Thread hilo;
    
    public ServidorControl(){
        this.aviso = new Aviso();
        this.properties = new Properties();
        this.fileChooser = new FileChooser();
        cargarProperties();
        this.servidor = new ServidorConexion(p1);
        aviso.verMensaje("Servidor lanzado");
        try{
            servidor.conectar();
            hilo = new Thread(new HiloServer(servidor.getServerSocket(), this));
            hilo.start();
        }catch(IOException e){
            aviso.verExcepcionConexion(e);
        }
    }
    public void cargarProperties(){
        try{
            this.properties.load(this.fileChooser.getProperties());
            this.p1 = Integer.parseInt(this.properties.getProperty("port.1"));
        }catch(Exception e){
            aviso.verExcepcion(e);
        }
    }
    public ServidorConexion getConexion(){
        return this.servidor;
    }
    public Aviso getAviso(){
        return this.aviso;
    }

}
    
   

