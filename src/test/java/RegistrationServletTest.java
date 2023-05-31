import by.fpmibsu.LIBRARY.DTO.CreateUserDto;
import by.fpmibsu.LIBRARY.Service.UserService;
import by.fpmibsu.LIBRARY.Servlet.RegistrationServlet;
import by.fpmibsu.LIBRARY.exception.ValidationException;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static org.mockito.Mockito.*;

public class RegistrationServletTest {

    @Mock
    private UserService userService;

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse response;

    @Mock
    private RequestDispatcher requestDispatcher;

    @InjectMocks
    private by.fpmibsu.LIBRARY.Servlet.RegistrationServlet registrationServlet;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testDoGet() throws Exception {
        when(request.getRequestDispatcher("/path/to/registration.jsp")).thenReturn(requestDispatcher);

        registrationServlet.doGet(request, response);

        verify(requestDispatcher).forward(request, response);
    }

    @Test
    public void testDoPost() throws Exception {
        when(request.getParameter("username")).thenReturn("testuser");
        when(request.getParameter("password")).thenReturn("testpassword");
        when(request.getParameter("email")).thenReturn("test@example.com");

        Map<String, String> errors = new HashMap<>();
        CreateUserDto userDto = CreateUserDto.builder()
                .login("testuser")
                .password("testpassword")
                .mail("test@example.com")
                .build();

        try {
            registrationServlet.doPost(request, response);
            verify(userService).create(userDto);
            verify(response).sendRedirect("/JSP/UserPage.jsp");
        } catch (ValidationException exception) {
        }
    }
}