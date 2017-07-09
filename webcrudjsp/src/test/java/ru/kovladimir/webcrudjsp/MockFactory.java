package ru.kovladimir.webcrudjsp;

import ru.kovladimir.webcrudhtml.model.DBManager;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class MockFactory {

    public static RequestDispatcher getRD() throws ServletException, IOException {
        RequestDispatcher dispatcher = mock(RequestDispatcher.class);
        doNothing().when(dispatcher).forward(anyObject(), anyObject());
        return dispatcher;
    }

    public static DBManager getDBManager() {
        DBManager manager = mock(DBManager.class);
        doNothing().when(manager).add(anyObject());
        doNothing().when(manager).update(anyObject());
        doNothing().when(manager).delete(anyObject());
        when(manager.getAll()).thenReturn(new ArrayList<>());
        when(manager.getUser(any(), any())).thenReturn(Optional.empty());
        when(manager.getUser(anyInt())).thenReturn(Optional.empty());


        return manager;
    }

    public static HttpServletRequest getRequest() throws ServletException, IOException {
        HttpServletRequest request = mock(HttpServletRequest.class);
        RequestDispatcher dispatcher = getRD();
        when(request.getRequestDispatcher(anyObject())).thenReturn(dispatcher);
        return request;
    }

    public static HttpServletResponse getResponse() {
        return mock(HttpServletResponse.class);
    }

    public static HttpSession getSession() {
        HttpSession session = mock(HttpSession.class);
        doNothing().when(session).invalidate();
        return session;
    }
}
