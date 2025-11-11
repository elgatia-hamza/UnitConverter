package com.example.unitconverter;


import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

public class LengthConverterServlet extends HttpServlet {
    private static final Map<String, Double> lengthToMeter = new HashMap<>();
    static {
        lengthToMeter.put("mm", 0.001);
        lengthToMeter.put("cm", 0.01);
        lengthToMeter.put("m", 1.0);
        lengthToMeter.put("km", 1000.0);
        lengthToMeter.put("in", 0.0254);
        lengthToMeter.put("ft", 0.3048);
        lengthToMeter.put("yd", 0.9144);
        lengthToMeter.put("mi", 1609.34);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

        double value = Double.parseDouble(req.getParameter("value"));
        String from = req.getParameter("from");
        String to = req.getParameter("to");

        // Convert to meters
        double meters = value * lengthToMeter.get(from);

        // Convert to target unit
        double result = meters / lengthToMeter.get(to);

        res.setContentType("text/html");
        PrintWriter out = res.getWriter();
        out.println("<html><body>");
        out.println("<h1>Result</h1>");
        out.println("<p>" + value + " " + from + " = " + result + " " + to + "</p>");
        out.println("<a href='length.html'>Convert Again</a>");
        out.println("</body></html>");
    }
}