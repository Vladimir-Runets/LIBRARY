package by.fpmibsu.LIBRARY.servlet;
import by.fpmibsu.LIBRARY.pool.ConnectionPool;
import by.fpmibsu.LIBRARY.exception.PersistentException;
import by.fpmibsu.LIBRARY.util.PropertiesUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import javax.servlet.*;
import java.io.IOException;

public class Connection_Filter implements Filter {
    private static final String URL_KEY = "db.url";
    private static final String USER_KEY = "db.user";
    private static final String PASSWORD_KEY = "db.password";
    private static Logger logger= LogManager.getLogger(LoginServlet.class);

    static {
        loadDriver();
    }

    private static void loadDriver() {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
          final String DB_DRIVER_CLASS = "org.postgresql.Driver";
          final String DB_URL = PropertiesUtil.get(URL_KEY);
          final String DB_USER = PropertiesUtil.get(USER_KEY);
          final String DB_PASSWORD = PropertiesUtil.get(PASSWORD_KEY);
          final int DB_POOL_START_SIZE = 10;
          final int DB_POOL_MAX_SIZE = 1000;
          final int DB_POOL_CHECK_CONNECTION_TIMEOUT = 0;

          try {
              ConnectionPool.getInstance().init(DB_DRIVER_CLASS, DB_URL, DB_USER, DB_PASSWORD, DB_POOL_START_SIZE, DB_POOL_MAX_SIZE, DB_POOL_CHECK_CONNECTION_TIMEOUT);
          } catch(PersistentException e) {
              logger.error("It is impossible to initialize application", e);
          }
          filterChain.doFilter(servletRequest,servletResponse);
    }

    @Override
    public void destroy() { 
    }
}
