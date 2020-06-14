package hospital.utils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface ServletCommand  {
    public void execute (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;
}
