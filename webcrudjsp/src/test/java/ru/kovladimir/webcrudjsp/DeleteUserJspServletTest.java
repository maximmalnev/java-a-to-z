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
public class DeleteUserJspServletTest {

    private HttpServletRequest request = MockFactory.getRequest();

    private HttpServletResponse response = MockFactory.getResponse();

    private DBManager manager = MockFactory.getDBManager();

    public DeleteUserJspServletTest() throws ServletException, IOException {
    }

    @Before
    @PrepareForTest({PostgreManager.class})
    public void init() throws ServletException, IOException {
        PowerMockito.mockStatic(PostgreManager.class);

        BDDMockito.given(PostgreManager.getInstance()).willReturn(manager);
    }

    @Test
    @PrepareForTest({PostgreManager.class})
    public void whenDeleteUserThenDeleteByManager() throws ServletException, IOException {
        DeleteUserJspServlet servlet = new DeleteUserJspServlet();
        when(request.getParameter("id")).thenReturn("1");

        servlet.doPost(request, response);

        verify(manager, atLeastOnce()).delete(anyObject());
    }

    @Test
    @PrepareForTest({PostgreManager.class})
    public void whenDeleteUserAndIDNullThenNeverDelete() throws ServletException, IOException {
        DeleteUserJspServlet servlet = new DeleteUserJspServlet();

        servlet.doPost(request, response);

        verify(manager, never()).delete(anyObject());
    }

    @Test
    @PrepareForTest({PostgreManager.class})
    public void whenDeleteUserThenGetParameterId() throws ServletException, IOException {
        DeleteUserJspServlet servlet = new DeleteUserJspServlet();
        when(request.getParameter("id")).thenReturn("1");

        servlet.doPost(request, response);

        verify(request, atLeastOnce()).getParameter("id");
    }

}