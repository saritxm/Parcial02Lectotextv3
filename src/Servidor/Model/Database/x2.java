package Servidor.Model.Database;

public class x2 {
    DatabaseDAO cosa = new DatabaseDAO();
    x2(){
        try {
            cosa.agregarUsuario("usuario","de prueba");
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            System.out.println(cosa.inicioSesion("usu","a"));
        } catch (Exception e) {
            // TODO: handle exception
        }
        try {
            System.out.println(cosa.inicioSesion("usuario","a"));
        } catch (Exception e) {
            // TODO: handle exception
        }
        try {
            System.out.println(cosa.inicioSesion("usuario","de prueba"));
        } catch (Exception e) {
            // TODO: handle exception
        }
    }
    
}
