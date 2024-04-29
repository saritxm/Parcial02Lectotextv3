/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Servidor.Controller;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.Statement;
/**
 *
 * @author Sara
 */
public class ServidorDAO {
    
    private Connection con;
    private Statement st;
    private ResultSet rs;
    private PreparedStatement ps;
    
    public ServidorDAO() {
        con = null;
        st = null;
        rs = null;
}
}
