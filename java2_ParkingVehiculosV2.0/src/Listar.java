import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zubiri.parking.Coche;
import com.zubiri.parking.Vehiculo;

/**
 * Servlet implementation class Listar
 */
@WebServlet("/Listar")
public class Listar extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Listar() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		
		try{
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			Connection conexion = DriverManager.getConnection("jdbc:mysql://natiteka.zapto.org:3306/parkingVehiculos", "aparcacoches", "anboto");
			Statement sentencia = conexion.createStatement();
			
			String select =
					"SELECT * FROM coches;";
		
			ResultSet coches = sentencia.executeQuery(select);
			out.println("<html>");
			out.println("<head>");
			out.println("<link rel='StyleSheet' href='css/styles.css' type='text/css'>");
			out.println("<title>Respuesta</title></head>");
			out.println("<body>");
			out.println("<h1>Respuesta desde servidor</h1>");
			
			while(coches.next()){
			
				Vehiculo coche = new Coche(
						
					coches.getInt("numRuedas"),
					coches.getString("combustible"),
					coches.getString("marca"),
					coches.getBoolean(6),
					coches.getInt("consumo"),
					coches.getString("matricula"), 
					coches.getString("color")			 
				);
				out.println("<p>" + coche.formatted() + "</p>");
			}
			out.println("<br><br><a href='listar.html'>Volver</a>");
			out.println("</body></html>");
		
		}catch(Exception e){
			
		}
	}
}
