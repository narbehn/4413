	package studentCalc1;

import java.io.IOException;
import java.io.Writer;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Start
 */
@WebServlet({"/Start/*", "/StartUp", "/StartUp/*"})
public class Start extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private int flag1, flag2 = 0;
	private Double prevPrinc = null;
	private Double prevPer = null;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Start() {
        super();
        // TODO Auto-generated constructor stub
        System.out.println("Hello, Got a GET request!");
        //in here, the println gets printed to the console instead
        //of in the html
    }

	/**?principal=2500
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	@SuppressWarnings("null")
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at : Hello, Got a GET request! \n").append(request.getContextPath());
		response.setContentType("text/plain");
		Writer resOut = response.getWriter();
		resOut.write("Hello, World!\n");
		
		//show IP of client that makes req?principal=2500uests on the page
		String clientIP = request.getRemoteAddr();
		resOut.write("client IP: " + clientIP + "\n");
		int portnum = request.getLocalPort();
		resOut.write("Port#: "+ portnum + "\n");
		
		resOut.write("protocol: " + request.getProtocol() + request.getMethod() + "\n");
		//query fuckery
		String clientQuery = request.getQueryString();
		//these two do the same thing, basically, above line looks nicer
		String q = request.getParameter("q");
		resOut.write("Query Param q=" + q + "\n");
		resOut.write("client query = " + clientQuery + "\n");
		
		String uri = request.getRequestURI();
		resOut.write("URI + CONTEXT "  + uri + "\n");
		String context = request.getServletContext().getContextPath();
		resOut.write(context);
		
		if(uri.contains("/StartUp/YorkBank"))
		{
			response.sendRedirect(this.getServletContext().getContextPath() 
					+ "/Start");
		}
		
		
		//principal gives null points exception ?
		//double principal = Double.parseDouble(this.getServletContext().getInitParameter("principal"));//whats this do
		//so "principal" throws exception on start up while "principle" doesn't get me any exception...
		
		
		//Task D
		resOut.write("\n=====================================TASK D==============================\n" );
		String princ = request.getParameter("principal"); 
		String period = request.getParameter("period"); 
		String interest = request.getParameter("interest"); 
		double prnc = 0;
		double prd = 0;
		double intrst = 0;
		
		//Double prevPrinc = null;//request.getParameter("principal");
		//Double prevPer = null;//request.getParameter("period");
		
		if(princ != null)
		{
			prnc = Double.parseDouble(princ);
			prevPrinc = prnc;
			flag1 = 1;
		}
		else if(flag1== 0){ //i.e. princ is null
			prnc = Double.parseDouble(this.getServletContext().getInitParameter("principal"));
			prevPrinc = prnc;
		}
		else{
			prnc = prevPrinc;
		}
		///////////
		if(period != null)
		{
			prd = Double.parseDouble(period);
			prevPer = prd;
			flag2 = 1;
		}
		else if(flag2== 0){
			prd = Double.parseDouble(this.getServletContext().getInitParameter("period"));
			prevPer = prd;
		}
		else{
			prd = prevPer;
		}
		///////////////
//		if(princ != null){
//			prnc = Double.parseDouble(princ);
//		}
//		else{
//			prnc = Double.parseDouble(this.getServletContext().getInitParameter("principal"));
//
//		}
//		if(period != null)
//		{
//			prd = Double.parseDouble(period);
//		}
//		else{
//			prd = Double.parseDouble(this.getServletContext().getInitParameter("period"));
//			
//		}
		if(interest != null){
			intrst = Double.parseDouble(interest);
		}
		else{
			
			intrst = Double.parseDouble(this.getServletContext().getInitParameter("interest"));
		}
		
		
		
		resOut.write("principal= " + prnc + " & period = " + prd+ " & interest = " + intrst + "\n");
		
		double formula = ((0.01*intrst)/12)*prnc/(1-Math.pow(1+((0.01*intrst)/12), (-1)*prd));
		
		resOut.write("\n Monthly Payment: " + Math.round(formula* 100.0) /100.0);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
