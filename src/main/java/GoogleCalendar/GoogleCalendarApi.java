package GoogleCalendar;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.client.util.DateTime;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.api.services.calendar.Calendar;
import com.google.api.services.calendar.CalendarScopes;
import com.google.api.services.calendar.model.Event;
import com.google.api.services.calendar.model.EventDateTime;
import entity.CalendarEvent;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.security.GeneralSecurityException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class GoogleCalendarApi {

    private final Calendar service;

    private static final List<String> SCOPES =  Collections.singletonList(CalendarScopes.CALENDAR);

    private static final String APPLICATION_NAME = "Google Calendar API";

    private static final JsonFactory JSON_FACTORY = GsonFactory.getDefaultInstance();

    private static final String TOKENS_DIRECTORY_PATH = "tokens";

    private static final String CREDENTIALS_FILE_PATH = "/credentials.json";





    public GoogleCalendarApi() throws GeneralSecurityException, IOException {

        this.service = new Calendar.Builder(GoogleNetHttpTransport.newTrustedTransport(),
                GsonFactory.getDefaultInstance(),
                getCredentials(GoogleNetHttpTransport.newTrustedTransport()))
                        .setApplicationName(APPLICATION_NAME)
                        .build();
    }

    public void addToGoogleCalendar(CalendarEvent calendarEvent) throws IOException {

        Event event = new Event()
                .setSummary(calendarEvent.getName())
                .setLocation(calendarEvent.getLocation())
                .setDescription(calendarEvent.getEventDescription());

        /////convert local date and local time to com.google.api.client.util.DateTime
        LocalDateTime localDateTime = LocalDateTime.of(calendarEvent.getDate(), calendarEvent.getTime());
        Date dateUtil = Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
        DateTime startDateTime = new DateTime(dateUtil);

        /////
        EventDateTime start = new EventDateTime()
                .setDateTime(startDateTime)
                .setTimeZone("America/Los_Angeles");
        event.setStart(start);

        /////convert end date and end time to com.google.api.client.util.DateTime
        LocalDateTime endlocalDateTime = LocalDateTime.of(calendarEvent.getEventEndDate(), calendarEvent.getEventEndTime());
        Date enddateUtil = Date.from(endlocalDateTime.atZone(ZoneId.systemDefault()).toInstant());
        DateTime endDateTime = new DateTime(enddateUtil);

        EventDateTime end = new EventDateTime()
                .setDateTime(endDateTime)
                .setTimeZone("America/Los_Angeles");
        event.setEnd(end);


        String calendarId = "primary";

        event = service.events().insert(calendarId, event).execute();
        System.out.printf("Event created: %s\n", event.getHtmlLink());

    }


    private static Credential getCredentials(final NetHttpTransport HTTP_TRANSPORT)
            throws IOException {

        InputStream in = GoogleCalendarApi.class.getResourceAsStream(CREDENTIALS_FILE_PATH);
        if (in == null) {
            throw new FileNotFoundException("Resource not found: " + CREDENTIALS_FILE_PATH);
        }
        GoogleClientSecrets clientSecrets =
                GoogleClientSecrets.load(JSON_FACTORY, new InputStreamReader(in));

        // Build flow and trigger user authorization request.
        GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(
                HTTP_TRANSPORT, JSON_FACTORY, clientSecrets, SCOPES)
                .setDataStoreFactory(new FileDataStoreFactory(new java.io.File(TOKENS_DIRECTORY_PATH)))
                .setAccessType("offline")
                .build();
        LocalServerReceiver receiver = new LocalServerReceiver.Builder().setPort(8888).build();
        Credential credential = new AuthorizationCodeInstalledApp(flow, receiver).authorize("user");
        //returns an authorized Credential object.
        return credential;
    }
}
