package ru.kovladimir.webcrudjsp;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import ru.kovladimir.webcrudhtml.model.DBManager;
import ru.kovladimir.webcrudhtml.model.PostgreManager;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

import static org.mockito.Mockito.*;

@RunWith(PowerMockRunner.class)
public class AddUserJspServletTest {

    private HttpServletRequest request = MockFactory.getRequest();

    private HttpServletResponse response = MockFactory.getResponse();

    private DBManager manager = MockFactory.getDBManager();

    public AddUserJspServletTest() throws ServletException, IOException {
    }

    @Before
    @PrepareForTest({PostgreManager.class})
    public void init() throws ServletException, IOException {
        PowerMockito.mockStatic(PostgreManager.class);

        BDDMockito.given(PostgreManager.getInstance()).willReturn(manager);
    }

    @Test
    @PrepareForTest({PostgreManager.class})
    public void whenAddThenAddToManager() throws ServletException, IOException {
        AddUserJspServlet servlet = new AddUserJspServlet();

        servlet.doPost(request, response);

        verify(manager, atLeastOnce()).add(anyObject());
    }

    @Test
    @PrepareForTest({PostgreManager.class})
    public void whenAddThenGetParameterLogin() throws ServletException, IOException {
        AddUserJspServlet servlet = new AddUserJspServlet();
        when(request.getParameter("login")).thenReturn("test");

        servlet.doPost(request, response);

        verify(request, atLeastOnce()).getParameter("login");
    }

    @Test
    @PrepareForTest({PostgreManager.class})
    public void whenAddThenGetParameterPassword() throws ServletException, IOException {
        AddUserJspServlet servlet = new AddUserJspServlet();
        when(request.getParameter("password")).thenReturn("test");

        servlet.doPost(request, response);

        verify(request, atLeastOnce()).getParameter("password");
    }

    @Test
    @PrepareForTest({PostgreManager.class})
    public void whenAddThenGetParameterName() throws ServletException, IOException {
        AddUserJspServlet servlet = new AddUserJspServlet();
        when(request.getParameter("name")).thenReturn("test");

        servlet.doPost(request, response);

        verify(request, atLeastOnce()).getParameter("name");
    }

    @Test
    @PrepareForTest({PostgreManager.class})
    public void whenAddThenGetParameterEmail() throws ServletException, IOException {
        AddUserJspServlet servlet = new AddUserJspServlet();
        when(request.getParameter("email")).thenReturn("test");

        servlet.doPost(request, response);

        verify(request, atLeastOnce()).getParameter("email");
    }

    @Test
    @PrepareForTest({PostgreManager.class})
    public void whenAddThenGetParameterRole() throws ServletException, IOException {
        AddUserJspServlet servlet = new AddUserJspServlet();
        when(request.getParameter("role")).thenReturn("test");

        servlet.doPost(request, response);

        verify(request, atLeastOnce()).getParameter("role");
    }

}