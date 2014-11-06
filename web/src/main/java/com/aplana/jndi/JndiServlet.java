package com.aplana.jndi;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintStream;

import com.ibm.websphere.naming.DumpNameSpace;

/**
 * @author Vitalii Samolovskikh
 */
@WebServlet(name = "JndiServlet", urlPatterns = {JndiServlet.SHORT_PATH, JndiServlet.LONG_PATH})
public class JndiServlet extends HttpServlet {

    public static final String SHORT_PATH = "/short";
    public static final String LONG_PATH = "/long";

    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = request.getServletPath();
        DumpNameSpace.ReportFormat format;
        if(SHORT_PATH.equals(path)){
            format = DumpNameSpace.ReportFormat.SHORT;
        } else if(LONG_PATH.equals(path)){
            format = DumpNameSpace.ReportFormat.LONG;
        } else {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        DumpNameSpace dns = new DumpNameSpace(new PrintStream(response.getOutputStream()), format);
        try {
            dns.generateDump(new InitialContext());
        } catch (NamingException e) {
            throw new ServletException(e);
        }
    }
}
