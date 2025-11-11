package com.example.unitconverter;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

public class WeightConverterServlet extends HttpServlet {
    private static final Map<String, Double> weightToGram = new HashMap<>();
    static {
        weightToGram.put("mg", 0.001);
        weightToGram.put("g", 1.0);
        weightToGram.put("kg", 1000.0);
        weightToGram.put("oz", 28.3495);
        weightToGram.put("lb", 453.592);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

        res.setContentType("text/html");
        PrintWriter out = res.getWriter();

        try {
            double value = Double.parseDouble(req.getParameter("value"));
            String from = req.getParameter("from");
            String to = req.getParameter("to");

            double inGrams = value * weightToGram.get(from);
            double result = inGrams / weightToGram.get(to);

            out.println("<html><body>");
            out.println("<h1>Conversion Result</h1>");
            out.println("<p>" + value + " " + from + " = " + result + " " + to + "</p>");
            out.println("<a href='weight.html'>Convert Again</a><br>");
            out.println("<a href='length.html'>Go to Length Converter</a>");
            out.println("</body></html>");

        } catch (Exception e) {
            out.println("<html><body>");
            out.println("<p style='color:red;'>Invalid input. Please try again.</p>");
            out.println("<a href='weight.html'>Back</a>");
            out.println("</body></html>");
        }
    }
}
