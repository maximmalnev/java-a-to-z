package ru.kovladimir.webcrudjsp;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import ru.kovladimir.webcrudhtml.model.PostgreManager;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;

import static org.mockito.Matchers.anyObject;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(PowerMockRunner.class)
public class LogOutServletTest {

    private HttpServletRequest request = MockFactory.getRequest();

    private HttpServletResponse response = MockFactory.getResponse();

    public LogOutServletTest() throws ServletException, IOException {
    }

    @Before
    @PrepareForTest({PostgreManager.class})
    public void init() throws ServletException, IOException {
        HttpSession session = MockFactory.getSession();
        when(request.getSession()).thenReturn(session);
    }

    @Test
    @PrepareForTest({PostgreManager.class})
    public void whenEditUserThenEditByManager() throws ServletException, IOException {
        LogOutServlet servlet = new LogOutServlet();

        servlet.doGet(request, response);

        verify(request, atLeastOnce()).getRequestDispatcher(anyObject());
    }
}