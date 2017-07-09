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
public class SignInServletTest {

    private HttpServletRequest request = MockFactory.getRequest();

    private HttpServletResponse response = MockFactory.getResponse();

    private DBManager manager = MockFactory.getDBManager();

    public SignInServletTest() throws ServletException, IOException {
    }

    @Before
    @PrepareForTest({PostgreManager.class})
    public void init() throws ServletException, IOException {
        PowerMockito.mockStatic(PostgreManager.class);
        when(request.getParameter("login")).thenReturn("1");
        when(request.getParameter("password")).thenReturn("1");

        BDDMockito.given(PostgreManager.getInstance()).willReturn(manager);
    }

    @Test
    @PrepareForTest({PostgreManager.class})
    public void whenSignInThenGetParameterLogin() throws ServletException, IOException {
        SignInServlet servlet = new SignInServlet();

        servlet.doPost(request, response);

        verify(request, atLeastOnce()).getParameter("login");
    }

    @Test
    @PrepareForTest({PostgreManager.class})
    public void whenSignInThenGetParameterPassword() throws ServletException, IOException {
        SignInServlet servlet = new SignInServlet();

        servlet.doPost(request, response);

        verify(request, atLeastOnce()).getParameter("password");
    }

}