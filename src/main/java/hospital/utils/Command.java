package hospital.utils;

import javax.servlet.ServletException;
import java.io.IOException;

public interface Command {
    void execute() throws ServletException, IOException;
}
