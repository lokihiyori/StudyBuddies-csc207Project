package GoogleCalendar;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.services.calendar.Calendar;
import com.google.api.services.calendar.model.Event;
import entity.CommonCalendarEvent;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

import static javax.management.Query.eq;
import static jdk.internal.org.objectweb.asm.util.CheckClassAdapter.verify;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class GoogleCalendarApiTest {

    private GoogleCalendarApi googleCalendarApi;
    private Calendar mockCalendarService;
    private Calendar.Events mockEvents;
    private Calendar.Events.Insert mockInsert;

    @BeforeEach
    void setUp() throws GeneralSecurityException, IOException {
        mockCalendarService = mock(Calendar.class);
        mockEvents = mock(Calendar.Events.class);
        mockInsert = mock(Calendar.Events.Insert.class);

        when(mockCalendarService.events()).thenReturn(mockEvents);
        when(mockEvents.insert(anyString(), any(Event.class))).thenReturn(mockInsert);
        when(mockInsert.execute()).thenReturn(new Event().setHtmlLink("http://test-link"));

        googleCalendarApi = new GoogleCalendarApi() {
            @Override
            protected Calendar buildCalendarService(NetHttpTransport httpTransport, JsonFactory jsonFactory, Credential credential) {
                return mockCalendarService;
            }
        };
    }

    @Test
    void testAddToGoogleCalendar() throws IOException {
        ArrayList<String> attendance = new ArrayList<>();
        CommonCalendarEvent calendarEvent = new CommonCalendarEvent("Tester", "Test",
                LocalDate.of(2024, 8, 5), LocalTime.of(10, 0),
                "Test Location", "Description",attendance,
                LocalDate.of(2024, 8, 5), LocalTime.of(11, 0));

        googleCalendarApi.addToGoogleCalendar(calendarEvent);

        ArgumentCaptor<Event> eventCaptor = ArgumentCaptor.forClass(Event.class);
        verify(mockEvents).insert(eq("primary"), eventCaptor.capture());
        Event capturedEvent = eventCaptor.getValue();

        assertEquals("Test Event", capturedEvent.getSummary());
        assertEquals("Test Description", capturedEvent.getDescription());
        assertEquals("Test Location", capturedEvent.getLocation());
    }
}
