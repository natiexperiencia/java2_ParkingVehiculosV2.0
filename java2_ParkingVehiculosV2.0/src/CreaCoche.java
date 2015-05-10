import java.io.IOException;
import java.io.PrintWriter;

import com.zubiri.parking.Coche;
import com.zubiri.parking.Vehiculo;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
//import java.sql.SQLException;
import java.sql.Statement;

/**
 * Servlet implementation class CreaCoche
 */
public class CreaCoche extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public CreaCoche() {
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
		// TODO Auto-generated method stub
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();

		int consumo = 0;
		int auto = 0;
		//int numPinones = 0;
		
		//String tipoVehiculo = "Coche";
		int numRuedas = Integer.parseInt(request.getParameter("numRuedas").trim());
		String marca = request.getParameter("marca");
		String combustible =  request.getParameter("combustible");
		if(request.getParameter("auto") == "true"){
			auto = 1;
		}else{
			auto = 0;
		}
		//int auto = Integer.parseInt(request.getParameter("auto").trim());
		if (request.getParameter("consumo") != ""){
			consumo = Integer.parseInt(request.getParameter("consumo").trim());
		}
		String matricula = request.getParameter("matricula");
		String color = request.getParameter("color");
	//	String codBarras = request.getParameter("codBarras");
		//if (request.getParameter("numPinones") != null){
			//numPinones = Integer.parseInt(request.getParameter("numPinones").trim());
		//}
	//	String tipoBici = request.getParameter("tipo");
	
		//ParkingVehiculos.addVehiculo(tipoVehiculo, numRuedas, combustible, marca, auto, consumo, matricula, color, codBarras, numPinones, tipoBici);

		try{
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			Connection conexion = DriverManager.getConnection("jdbc:mysql://natiteka.zapto.org:3306/parkingVehiculos", "aparcacoches", "anboto");
			Statement sentencia = conexion.createStatement();
			
			String create = 
					"CREATE TABLE IF NOT EXISTS coches(" +
					"matricula varchar(7) primary key not null," +
					"marca varchar(30)," +
					"color varchar(30)," +
					"combustible varchar(30)," +
					"consumo int(2)," +
					"automatico boolean," +
					"numRuedas int(2));";
			
			
			String insert =
			"INSERT INTO coches(matricula, marca, color, combustible, consumo, automatico, numRuedas)" +
			"VALUES('"+matricula+"','"+marca+"','"+color+"','"+combustible+"','"+consumo+"','"+auto+"','"+numRuedas+"')";
			
			String select =
					"SELECT * FROM coches WHERE matricula = '"+matricula+"';";
			
			sentencia.execute(create);
			sentencia.execute(insert);
			ResultSet nuevoCoche = sentencia.executeQuery(select);
			nuevoCoche.next();
			
			Vehiculo coche = new Coche(
						
					nuevoCoche.getInt("numRuedas"),
					nuevoCoche.getString("combustible"),
					nuevoCoche.getString("marca"),
					nuevoCoche.getBoolean(6),
					nuevoCoche.getInt("consumo"),
					nuevoCoche.getString("matricula"), 
					nuevoCoche.getString("color")			 
			);

			out.println("<html>");
			out.println("<head>");
			out.println("<link rel='StyleSheet' href='css/styles.css' type='text/css'>");
			out.println("<title>Respuesta</title></head>");
			out.println("<body>");
			out.println("<h1>Respuesta desde servidor</h1>");
			out.println("<p>" + coche.formatted() + "</p>");
			out.println("<br><br><a href='crear.html'>Volver</a>");
			out.println("</body></html>");
		
		}catch(Exception e){
			out.println(e);
		}
	}
}