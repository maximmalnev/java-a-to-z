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

import static org.mockito.Matchers.anyObject;
import static org.mockito.Mockito.*;

@RunWith(PowerMockRunner.class)
public class EditUserJspServletTest {

    private HttpServletRequest request = MockFactory.getRequest();

    private HttpServletResponse response = MockFactory.getResponse();

    private DBManager manager = MockFactory.getDBManager();

    public EditUserJspServletTest() throws ServletException, IOException {
    }

    @Before
    @PrepareForTest({PostgreManager.class})
    public void init() throws ServletException, IOException {
        PowerMockito.mockStatic(PostgreManager.class);
        when(request.getParameter("id")).thenReturn("1");
        when(request.getParameter("name")).thenReturn("test");
        when(request.getParameter("password")).thenReturn("test");
        when(request.getParameter("login")).thenReturn("test");
        when(request.getParameter("email")).thenReturn("test");
        when(request.getParameter("role")).thenReturn("user");

        BDDMockito.given(PostgreManager.getInstance()).willReturn(manager);
    }

    @Test
    @PrepareForTest({PostgreManager.class})
    public void whenEditUserThenEditByManager() throws ServletException, IOException {
        EditUserJspServlet servlet = new EditUserJspServlet();

        servlet.doPost(request, response);

        verify(manager, atLeastOnce()).update(anyObject());
    }

    @Test
    @PrepareForTest({PostgreManager.class})
    public void whenEditUserThenGetParameterId() throws ServletException, IOException {
        EditUserJspServlet servlet = new EditUserJspServlet();

        servlet.doPost(request, response);

        verify(request, atLeastOnce()).getParameter("id");
    }

    @Test
    @PrepareForTest({PostgreManager.class})
    public void whenEditUserThenGetParameterName() throws ServletException, IOException {
        EditUserJspServlet servlet = new EditUserJspServlet();

        servlet.doPost(request, response);

        verify(request, atLeastOnce()).getParameter("name");
    }

    @Test
    @PrepareForTest({PostgreManager.class})
    public void whenEditUserThenGetParameterPassword() throws ServletException, IOException {
        EditUserJspServlet servlet = new EditUserJspServlet();

        servlet.doPost(request, response);

        verify(request, atLeastOnce()).getParameter("password");
    }

    @Test
    @PrepareForTest({PostgreManager.class})
    public void whenEditUserThenGetParameterLogin() throws ServletException, IOException {
        EditUserJspServlet servlet = new EditUserJspServlet();

        servlet.doPost(request, response);

        verify(request, atLeastOnce()).getParameter("login");
    }

    @Test
    @PrepareForTest({PostgreManager.class})
    public void whenEditUserThenGetParameterEmail() throws ServletException, IOException {
        EditUserJspServlet servlet = new EditUserJspServlet();

        servlet.doPost(request, response);

        verify(request, atLeastOnce()).getParameter("email");
    }


    @Test
    @PrepareForTest({PostgreManager.class})
    public void whenEditUserThenGetParameterRole() throws ServletException, IOException {
        EditUserJspServlet servlet = new EditUserJspServlet();

        servlet.doPost(request, response);

        verify(request, atLeastOnce()).getParameter("role");
    }


}