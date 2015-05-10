import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zubiri.parking.Coche;
import com.zubiri.parking.Vehiculo;

/**
 * Servlet implementation class Buscar
 */
public class Buscar extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Buscar() {
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
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		String matricula = request.getParameter("matricula");
		
		try{
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			Connection conexion = DriverManager.getConnection("jdbc:mysql://natiteka.zapto.org:3306/parkingVehiculos", "aparcacoches", "anboto");
			Statement sentencia = conexion.createStatement();
			
			String select =
					"SELECT * FROM coches WHERE matricula = '"+matricula+"';";
		
			ResultSet viejoCoche = sentencia.executeQuery(select);
			viejoCoche.next();
			
			Vehiculo coche = new Coche(
						
					viejoCoche.getInt("numRuedas"),
					viejoCoche.getString("combustible"),
					viejoCoche.getString("marca"),
					viejoCoche.getBoolean(6),
					viejoCoche.getInt("consumo"),
					viejoCoche.getString("matricula"), 
					viejoCoche.getString("color")			 
			);
			
			out.println("<html>");
			out.println("<head>");
			out.println("<link rel='StyleSheet' href='css/styles.css' type='text/css'>");
			out.println("<head><title>Respuesta</title></head>");
			out.println("<body>");
			out.println("<h1>Respuesta desde servidor</h1>");
			out.println("<p>" + coche.formatted() + "</p>");
			out.println("<br><br><a href='buscar.html'>Volver</a>");
			out.println("</body></html>");
		
		}catch(Exception e){
			out.println(e);
		}
	}
}
