import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

public class Aplicacion {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Connection conn = null;
        int opcion;
        String server = "datos.somox.com";
        String database= "Libreria Somox";
        String user = "LibreriaSomox";
        String password = "LibreriaSomox";
        String crearTablaSQL= "CREATE TABLE Libros (lib_isbn VARCHAR(10), lib_titulo VARCHAR(50), lib_autor VARCHAR(50), lib_id VARCHAR(10));";
        String insertarLibroSQL= "INSERT INTO Libros (lib_isbn, lib_titulo, lib_autor, lib_id) VALUES (?, ?, ?, ?)";


        do{
            Io.sop("----------------------------------------------------------");
            Io.sop("-----------------------Menu-------------------------------");
            Io.sop("----------------------------------------------------------");
            Io.sop("1. Realizar conexion ");
            Io.sop("2. Crear Tabla ");
            Io.sop("3. Insertar registro ");
            Io.sop("0. Salir de la Aplicacion ");

            Io.sop("Elige una opcion:");
            opcion = scanner.nextInt();
            scanner.nextLine();
            switch(opcion){
                case 1:
                    conn = Io.getConexion(server, database, user, password);
                    Io.continuar(scanner);
                    break;
                case 2: 
                    if(Io.crearTablaLibros(conn, crearTablaSQL)){
                        Io.sop("La tabla ha sido creada correctamente");
                    }else{
                        Io.sop("Error al crear la tabla");
                    }
                    Io.continuar(scanner);
                    break;
                case 3:
                    if(Io.insertarLibro(conn, scanner, insertarLibroSQL)){
                        Io.sop("Libro insertado correctamente");
                    }else{
                        Io.sop("Error al insertar el libro");
                    }
                    Io.continuar(scanner);
                    break;
                case 0:
                    Io.sop("Gracias por usar la aplicacion");
                    break;
                default:
                    Io.sop("Opcion  Incorrecta");
                    break;
            }
        }while(opcion!=0);
        try{
            if(conn!= null){
                conn.close();
            }
        }catch(SQLException e){
            Io.sop("Error al cerrar la conexion");
        }
        scanner.close();
    }
}