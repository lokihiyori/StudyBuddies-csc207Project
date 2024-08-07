package GoogleCalendar;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.services.calendar.Calendar;

import java.io.IOException;
import java.security.GeneralSecurityException;

public class MyGoogleCalendarApi extends GoogleCalendarApi{
    public MyGoogleCalendarApi() throws GeneralSecurityException, IOException {
    }

    @Override
    protected Calendar buildCalendarService(NetHttpTransport httpTransport, JsonFactory jsonFactory, Credential credential) {
        return new Calendar.Builder(httpTransport, jsonFactory, credential)
                .setApplicationName("Google Calendar API")
                .build();
    }
}
