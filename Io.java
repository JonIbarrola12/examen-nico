import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class Io {
    static Scanner scanner =new Scanner (System.in);
    public static void sop(String mensaje){
        System.out.println(mensaje);
    }
    public static void continuar(Scanner scanner){
        sop("Pulsa enter para continuar");
        scanner.nextLine();
    }
    public static Connection getConexion(String server, String database, String user, String password){
        String url = "jdbc:mysql://" + server + "/" + database;
        Connection conn = null;
        try{
            conn = DriverManager.getConnection(url, user, password);
            sop("Conexion establecida exitosamente");
        }catch (SQLException e){
            sop("No se pudo establecer la conexion");
        }
        return conn;
    }
    public static boolean crearTablaLibros(Connection conn, String sql){
        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.execute();
            return true;
        }catch(SQLException e){
            return false;
        }
    }
    public static boolean insertarLibro(Connection conn,  String sql){
        sop("Introduce el isbn del libro:");
        String isbn = scanner.nextLine();
        sop("Introduce el titulo del libro:");
        String titulo = scanner.nextLine();
        sop("Introduce el autor del libro:");
        String autor = scanner.nextLine();
        sop("Introduce el codigo del libro:");
        String id= scanner.nextLine();
        try(PreparedStatement stmt = conn.prepareStatement(sql)){
            stmt.setString(1, isbn);
            stmt.setString(2, titulo);
            stmt.setString(3, autor);
            stmt.setString(4, id);
            stmt.executeUpdate();
            return true;
        }catch(SQLException e){
            return false;
        }
    }
}
