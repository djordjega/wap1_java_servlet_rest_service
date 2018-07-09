package djordje.wap1m.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import djordje.wap1m.model.EntryDAO;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author djordje gavrilovic
 */
public class MyServlet extends HttpServlet {

    private static EntryDAO entryDao = new EntryDAO();
    private static ObjectMapper om = new ObjectMapper();

    
    // initial GET
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("application/json");
        response.getWriter().write(om.writeValueAsString(entryDao.getAllEntries()));

    }

    // POST - add new entry
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        if (request.getParameter("title") != null) {
            String title = request.getParameter("title");
            String text = request.getParameter("txt");
            entryDao.addNewEntry(title, text);
            response.setStatus(200);
            response.getWriter().write("New entry added.");
        } else {
            response.setStatus(404);
            response.getWriter().write("Title not provided");
        }

    }

    // DELETE selected entry
    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        if (request.getParameter("entryId") != null) {
            String id = request.getParameter("entryId");
            entryDao.removeEntry(id);
            response.setStatus(200);
            response.getWriter().write("Entry with id=" + id + " deleted.");
        } else {
            response.setStatus(404);
            response.getWriter().write("Entry id not provided.");
        }

    }

}
