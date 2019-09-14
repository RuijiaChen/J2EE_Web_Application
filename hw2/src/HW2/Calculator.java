package HW2;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Calculator
 */
@WebServlet("/Calculator")
public class Calculator extends HttpServlet {
    private static final long serialVersionUID = 1L;
    int runtime = 0;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Calculator() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        doPost(request,response);
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub

        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();

        out.println("<!doctype html>");
        out.println("<html lang=\"en\">");
        out.println("    <head>");
        out.println("        <meta charset=\"UTF-8\">");
        out.println("        <title>Calculator</title>");
        out.println("    </head>");
        out.println("    <body>");

        String xStr = request.getParameter("x");
        String yStr = request.getParameter("y");
        String operation = request.getParameter("button");
        double xNum=0,yNum=0;
        String xShow = "";
        String yShow = "";

        if(xStr == null){
            if(runtime>0){
                out.println("    <h2 style=\"color:red;\">Please input X!</span></h2>");
            }
            xShow = "";
        }
        else {
            xShow = xStr;
        }

        if(yStr == null){
            if(runtime>0){
                out.println("    <h2 style=\"color:red;\">Please input Y!</span></h2>");
            }
            yShow = "";
        }
        else {
            yShow = yStr;
        }

        if((operation == null) && (runtime>0)){
            out.println("    <h2 style=\"color:red;\">Operation value should not be null!</span></h2>");
        }
        
        if(operation != null ) {
	        if((operation.equals("+")) || (operation.equals("-")) || (operation.equals("*")) ||(operation.equals("/"))){
	            boolean xValid = true;
	            boolean yValid = true;
	            try {
	                xNum = Double.parseDouble(xStr);                      
	            } 
	            catch (NumberFormatException e) {
	                out.println("    <h2 style=\"color:red;\">Input X is illegal!</span></h2>");
	                xValid = false;
	            }
	            try {
	                yNum = Double.parseDouble(yStr);                      
	            } 
	            catch (NumberFormatException e) {
	                out.println("    <h2 style=\"color:red;\">Input Y is illegal!</span></h2>");
	                yValid = false;
	            }
	            if(xValid & yValid) {
	            	if((yNum == 0) &&(operation.equals("/"))){
	            		out.println("    <h2 style=\"color:red;\">Cannot devide by 0!</span></h2>");
	            	}
	            	else{
	            		out.println("    <h2>"+ calculate(xNum, yNum, operation)+"</h2>");
	            	}
	            }
	        }
	        else{
	            out.println("    <h2 style=\"color:red;\">Operation value is illegal!</span></h2>");
	        }
        }

        out.println("        <form action=\"Calculator\" method=\"GET\">");
        out.println("            <div class=\"title\">");
        out.println("                Calculator");
        out.println("            </div>");
        out.println("            <div class=\"row\">");
        out.println("                <label for=\"x\"> X: </label>");
        out.println("                <input name=\"x\" type=\"text\" value=\""+xShow+"\">");
        out.println("            </div>");
        out.println("            <div class=\"row\">");
        out.println("                <label for=\"y\"> Y: </label>");
        out.println("                <input name=\"y\" type=\"text\" value=\""+yShow+"\">");
        out.println("            </div>");
        out.println("            <div class=\"row\">");
        out.println("                <input type=\"submit\" name=\"button\" value=\"+\">");
        out.println("                <input type=\"submit\" name=\"button\" value=\"-\">");
        out.println("                <input type=\"submit\" name=\"button\" value=\"*\">");
        out.println("                <input type=\"submit\" name=\"button\" value=\"/\">");
        out.println("            </div>");
        out.println("      </form>");
        out.println("    </body>");
        out.println("</html>");
        runtime++;
    }

    private String calculate(double x, double y, String operation){
    	double ans;
    	if(operation.equals("+")){
    		ans = x + y;
    	}
    	else if(operation.equals("-")){
    		ans = x - y;
    	}
    	else if (operation.equals("*")) {
    		ans = x * y;
    	}
    	else{
    		ans = x/y;
    	}
    	String answer = String.format("%,.2f", x) + operation + String.format("%,.2f", y) + "=" + String.format("%,.2f", ans);

    	return answer;
    }

}



