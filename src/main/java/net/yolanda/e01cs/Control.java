package net.yolanda.e01cs;

import com.google.gson.Gson;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.client.Entity;
import net.daw.beans.Celda;
import net.daw.beans.Fila;

/**
 *
 * @author Yolanda
 */
public class Control extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //response.setContentType("text/html;charset=UTF-8");
        response.setContentType("application/json");

        try (PrintWriter out = response.getWriter()) {
            
//        ArrayList<ArrayList> errores = new ArrayList<ArrayList>();
//        ArrayList<String> lineaerrores;
//        
//        if(request.getParameter("ancho").equals("")) {
//        	lineaerrores = new ArrayList<String>();
//        	lineaerrores.add("Error: El parámetro ancho no contiene números.");
//        	errores.add(lineaerrores);
//        }
//        
//        if(request.getParameter("alto").equals("")) {
//        	lineaerrores = new ArrayList<String>();
//        	lineaerrores.add("Error: El parámetro alto no contiene números.");
//        	errores.add(lineaerrores);
//        }
//        
//        if(request.getParameter("ancho").matches("^[0-9][0-9]*$")) {
//        	lineaerrores = new ArrayList<String>();
//        	lineaerrores.add("Error: Se han introducido valores no numéricos en el parámetro ancho.");
//        	errores.add(lineaerrores);
//        }
//        
//        if(request.getParameter("alto").matches("^[0-9][0-9]*$")) {
//        	lineaerrores = new ArrayList<String>();
//        	lineaerrores.add("Error: Se han introducido valores no numéricos en el parámetro alto.");
//        	errores.add(lineaerrores);
//        }
//        
//        if(!errores.isEmpty()) {
//        	response.setStatus(500);
//        	Gson gSon = new Gson();
//        	response.getWriter().write(gSon.toJson(errores));
//        }
//        
      

        //-------------------------------------------------------------------
            

            if (request.getParameter("ancho") == "" || request.getParameter("alto") == "") {
                response.setStatus(500);
                return;
            }

            if (!request.getParameter("ancho").matches("^[1-9][0-9]*$")
                    || !request.getParameter("alto").matches("^[1-9][0-9]*$")) {
                response.setStatus(500);
                return;
            }

            int ancho = Integer.parseInt(request.getParameter("ancho"));
            int alto = Integer.parseInt(request.getParameter("alto"));

            if (ancho > 100 || alto > 100) {
                response.setStatus(500);
                return;
            }
            
          

//            int ancho = 10;
//            int alto = 10;
            //creación ArrayList
            Fila fila;
            Celda celda;
            ArrayList dataJson = new ArrayList();

            for (int i = 1; i <= alto; i++) {
                fila = new Fila();// se inicializa dentro para limpiar
                fila.setI(i);
                ArrayList<Celda> arrayCelda = new ArrayList();
                for (int j = 1; j <= ancho; j++) {
                    celda = new Celda();
                    celda.setI(i);
                    celda.setJ(j);
                    celda.setResultado(j * i);
                    arrayCelda.add(celda);
                    fila.setArray(arrayCelda);
                }
                dataJson.add(fila);
            }

//            Gson gson = new Gson();
//            String strGson = gson.toJson(json);
            Gson oGson = new Gson();
            response.getWriter().append(oGson.toJson(dataJson));

//            out.print(strGson);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);

    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
