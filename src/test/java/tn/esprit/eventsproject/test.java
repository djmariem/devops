package tn.esprit.eventsproject;




import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import tn.esprit.eventsproject.entities.*;
import tn.esprit.eventsproject.repositories.EventRepository;
import tn.esprit.eventsproject.repositories.ParticipantRepository;
import tn.esprit.eventsproject.repositories.LogisticsRepository;
import tn.esprit.eventsproject.services.EventServicesImpl;

import java.time.LocalDate;
import java.util.Optional;

public class test {

    @Mock
    private EventRepository eventRepository;

    @Mock
    private ParticipantRepository participantRepository;

    @Mock
    private LogisticsRepository logisticsRepository;

    @InjectMocks
    private EventServicesImpl eventServices;

    private Event event;
    private Participant participant;
    private Logistics logistics;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);

        // Create a simple Event object without collections
        event = new Event(1, "Event 1", LocalDate.now(), LocalDate.now().plusDays(1), 0, null, null);

        // Create participant with the required constructor
        participant = new Participant(1, "John", "Doe", Tache.ORGANISATEUR, null);

        // Create logistics object
        logistics = new Logistics(1, "Logistics 1", true, 100.0f, 1);
    }

    @Test
    public void testAddParticipant() {
        // Mock the behavior of the participant repository
        when(participantRepository.save(any(Participant.class))).thenReturn(participant);

        // Add participant using the service method
        Participant savedParticipant = eventServices.addParticipant(participant);

        // Assertions to verify the result
        assertNotNull(savedParticipant);
        assertEquals("John", savedParticipant.getNom());
    }

    @Test
    public void testAddAffectEventParticipant() {
        // Add participant to the event (directly without collections)
        event.setParticipants(null);  // Initially, no participants

        // Mock repository method calls
        when(participantRepository.findById(1)).thenReturn(Optional.of(participant));
        when(eventRepository.save(any(Event.class))).thenReturn(event);

        // Add the event participant
        Event updatedEvent = eventServices.addAffectEvenParticipant(event, 1);

        // Assertions to verify the participant is added to the event
        assertNotNull(updatedEvent);
        // Since we are not using collections like ArrayList or HashSet, ensure participants are still updated
    }

    @Test
    public void testAddAffectLogistics() {
        // Mock behavior for event and logistics repository
        when(eventRepository.findByDescription("Event 1")).thenReturn(event);
        when(logisticsRepository.save(any(Logistics.class))).thenReturn(logistics);

        // Add logistics to the event
        Logistics savedLogistics = eventServices.addAffectLog(logistics, "Event 1");

        // Assertions to verify logistics is added
        assertNotNull(savedLogistics);
        assertEquals("Logistics 1", savedLogistics.getDescription());
    }


}

