package hospital.utils.factories;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Basic interface for servlet command.
 * @author Yelyzaveta Onyshchenko
 * @version 1.01
 */
public interface ServletCommand  {
    /**
     * Instructions for command to perform.
     * @param request HttpRequest
     * @param response HttpResponse
     * @throws ServletException on servlet exception
     * @throws IOException on error while performing the request
     */
    public void execute (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;
}
