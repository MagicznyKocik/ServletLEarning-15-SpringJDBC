package pl.adamLupinski.controller;

import pl.adamLupinski.dao.BookDao;
import pl.adamLupinski.dao.DaoFactory;
import pl.adamLupinski.model.Book;
import pl.adamLupinski.util.DbOperationException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/BookServlet")
public class BookServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // adding polish signs handling
        request.setCharacterEncoding("UTF-8");

        String isbn = request.getParameter("isbn");
        String title = request.getParameter("title");
        String description = request.getParameter("description");
        String option = request.getParameter("option");

        try {
            DaoFactory factory = DaoFactory.getDaoFactory(DaoFactory.MYSQL_DAO);
            BookDao dao = factory.getBookDao();
            Book book = null;
            String operation = null;
            if ("read".equals(option)) {
                book = dao.read(isbn);
                operation = option;
            } else if ("create".equals(option)) {
                book = new Book(isbn, title, description);
                dao.create(book);
                operation = option;
            } else if ("update".equals(option)) {
                book = new Book(isbn, title, description);
                dao.update(book);
                operation = option;
            } else if ("delete".equals(option)) {
                book = new Book(isbn, title, description);
                dao.delete(book);
                operation = "delete";
            }

            request.setAttribute("option", operation);
            request.setAttribute("book", book);

            if (book == null){
                request.getRequestDispatcher("error.jsp").forward(request, response);
            } else {
                request.getRequestDispatcher("result.jsp").forward(request, response);
            }

        } catch (DbOperationException e) {
            e.printStackTrace();
            request.getRequestDispatcher("error.jsp").forward(request, response);
        }


    }
}
