/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import Calculator.CalcSession;
import Calculator.Calculation;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpSession;

/**
 *
 * @author sha
 */
public class TestServlet extends HttpServlet {

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
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            String sessID = request.getSession().getId();
            HashMap<String,ArrayList<String>>operations = (HashMap<String, ArrayList<String>>) getServletContext().getAttribute("operations");
            if (operations==null){
                operations = new HashMap<>();
            }
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<link rel=\"stylesheet\" href=\"" + request.getContextPath() + "\\css\\style.css\">");
            out.println("<title>Servlet TestServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Calculator at " + request.getContextPath() + "</h1>");
            out.println("<div class=\"nova\">");
            Map<String, String> hm = new HashMap<>();
            Enumeration<String> en = request.getParameterNames();
            while (en.hasMoreElements()) {
                String element = en.nextElement();
                hm.put(element, (String) request.getParameter(element));
            }

            String s;

            try {
                s = Calculation.calculator(hm);
                out.printf("<pre> %s </pre>", s);
                out.println("</div>");
                out.println("<hr>");
                out.println("<div class=\"block\">");
                out.println("<p>THIS SESSION</p>");
                ArrayList<String>historyList = new ArrayList<>();
                if (operations.containsKey(sessID)) {                    
                    historyList = operations.get(sessID);
                    for (String x : historyList) {
                        out.printf("<p class=\"nova\">%s</p>", x);
                    }
                }    
                out.println("</div>");
                historyList.add(s);
                out.println("<div class=\"block\">");
               operations.put(sessID, historyList);
               getServletContext().setAttribute("operations", operations);
               // out.println("<hr>");
                out.println("<p>ALL SESSIONS</p>");
                for (Map.Entry<String,ArrayList<String>>entry: operations.entrySet()){                  
                    if (!entry.getKey().equals(sessID)){
                        out.printf("<h4>%s</h4>",entry.getKey());
                        ArrayList<String>list = entry.getValue();
                        if (!list.isEmpty())
                            for (String y:list){
                                out.printf("<p>%s</p>", y);
                        }
                    }
                }
                out.println("</div>");
            } catch (Exception ex) {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST);
                out.printf("<pre>%s</pre>", ex.toString());
            } 
            
            
            
            finally {
                out.println("</body>");
                out.println("</html>");
            }
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
