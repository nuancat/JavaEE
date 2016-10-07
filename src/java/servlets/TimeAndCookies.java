/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author shamsi
 */
public class TimeAndCookies extends HttpServlet {

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
            boolean isnew = request.getSession(true).isNew();
            Cookie[] cookies = request.getCookies();
            Cookie cookie = null; 
            String time = new SimpleDateFormat("hh:mm:ss  dd-MM-YY").format(new Date());
            if (isnew)
            cookie = new Cookie("response_time", time);
            else 
                {
                    cookie = new Cookie("responce_time"+cookies.length,time);
                    for (int i = 1; i < cookies.length; i++) {
                    response.addCookie(cookies[i]);
                }            
            }           
            //cookie.setMaxAge(300);
            String lastresponse = isnew?"не было запросов":cookies[cookies.length-1].getValue();
            response.addCookie(cookie);
            request.setCharacterEncoding("UTF-8");
            out.printf("<!DOCTYPE html>\n" +
                            "<html>\n" +
                            "  <head>\n" +
                            "    <meta charset=\"utf-8\">\n" +
                            "    <title>TimeAndCookies</title>\n" +
                            "  </head>\n" +
                            "  <body>\n" +
                            "    <h3>Сейчас: %s</h3>\n" +
                            "    <h3>Последний запрос был в: %s</h3>\n" +
                            "  </body>\n" +
                            "</html>", time, lastresponse);
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
