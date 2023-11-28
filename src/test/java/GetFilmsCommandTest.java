import by.bsuir.wtlab2.controller.JspPageName;
import by.bsuir.wtlab2.logic.CommandException;
import by.bsuir.wtlab2.logic.impl.GetFilmsCommand;
import jakarta.servlet.http.HttpServletRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class GetFilmsCommandTest {

    private HttpServletRequest request;

    private GetFilmsCommand getFilmsCommand;

    @BeforeEach
    public void setUp() {
        request = mock(HttpServletRequest.class);
        getFilmsCommand = new GetFilmsCommand();
    }

    @Test
    public void testGetFilmsCommand() throws CommandException {
        String result = getFilmsCommand.execute(request);

        ArgumentCaptor<String> attributeNameCaptor = ArgumentCaptor.forClass(String.class);
        ArgumentCaptor<Object> attributeValueCaptor = ArgumentCaptor.forClass(Object.class);
        verify(request).setAttribute(attributeNameCaptor.capture(), attributeValueCaptor.capture());

        assertEquals(JspPageName.FILMS_PAGE, result);
        assertEquals("filmsList", attributeNameCaptor.getValue());
        assertTrue(attributeValueCaptor.getValue() instanceof List<?>);
    }
}
