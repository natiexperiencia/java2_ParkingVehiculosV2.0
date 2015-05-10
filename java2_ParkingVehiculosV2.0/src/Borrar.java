import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Borrar
 */
public class Borrar extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Borrar() {
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
		// TODO Auto-generated method stub
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		String matricula = request.getParameter("matricula");
		
		try{
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			Connection conexion = DriverManager.getConnection("jdbc:mysql://natiteka.zapto.org:3306/parkingVehiculos", "aparcacoches", "anboto");
			Statement sentencia = conexion.createStatement();
			
			String borrar = 
					"DELETE FROM coches WHERE matricula = '"+matricula+"';";
			
			sentencia.execute(borrar);
						 
	
		
			out.println("<html>");
			out.println("<head>");
			out.println("<link rel='StyleSheet' href='css/styles.css' type='text/css'>");
			out.println("<title>Respuesta</title>");
			out.println("<body>");
			out.println("<h1>Respuesta desde servidor</h1>");

			if (sentencia.execute(borrar) == true) {
				out.println("<p>Coche con la matricula " + matricula +" se ha borrado correctamente</p>");	
			}else {
				out.println("No existe el coche con la matricula" + matricula);
			}
			out.println("<br><br><a href='borrar.html'>Volver</a>");
			out.println("</body></html>");
		
		}catch(Exception e){
			out.println(e);
		}
	}
}