package com.example.unitconverter;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

public class TemperatureConverterServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

        res.setContentType("text/html");
        PrintWriter out = res.getWriter();

        try {
            double value = Double.parseDouble(req.getParameter("value"));
            String from = req.getParameter("from");
            String to = req.getParameter("to");

            double result = convertTemperature(value, from, to);

            out.println("<html><body>");
            out.println("<h1>Conversion Result</h1>");
            out.println("<p>" + value + " " + from + " = " + result + " " + to + "</p>");
            out.println("<a href='temperature.html'>Convert Again</a><br>");
            out.println("<a href='length.html'>Go to Length Converter</a>");
            out.println("</body></html>");
        } catch (Exception e) {
            out.println("<html><body>");
            out.println("<p style='color:red'>Invalid input. Please try again.</p>");
            out.println("<a href='temperature.html'>Back</a><br>");
            out.println("</body></html>");
        }
    }

    private double convertTemperature(double value, String from, String to) {
        double celsius;

        switch (from) {
            case "Fahrenheit":
                celsius = (value - 32) * 5 / 9;
                break;
            case "Kelvin":
                celsius = value - 273.15;
                break;
            default:
                celsius = value;
        }

        switch (to) {
            case "Fahrenheit":
                return  (value * 9 / 5) + 32;
            case "Kelvin":
                return value + 273.15;
            default:
                return celsius;
        }
    }
}
