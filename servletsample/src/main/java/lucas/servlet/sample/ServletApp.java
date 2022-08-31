package lucas.servlet.sample;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public class ServletApp extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        URL resource = ServletApp.class.getClassLoader().getResource("index.html");
        assert resource != null;
        try (InputStream stream = resource.openStream()) {
            resp.setContentType("text/html");
            resp.getOutputStream().write(stream.readAllBytes());
        }
    }
}
