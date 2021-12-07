package edu.iastate.myclub;

//@DataJpaTest
//@AutoConfigureTestDatabase(replace = Replace.NONE)
public class EventServiceAndRepositoryTests {
	
	//TODO Showed during demo, but uses database for tests so need to find better approach to be used by everyone

//	private EventController eventController;
//	
//	private EventService eventService;
//	
//	@Autowired
//	private EventRepository eventRepository;
//	
//	@Autowired
//	private ClubRepository clubRepository;
//	
//	public void setup()
//	{
//		eventService = new EventService(eventRepository, clubRepository);
//		eventController = new EventController(eventService);
//	}
//	
//	@Test
//	public void getClubEventsByMonthAndYearShouldReturnResponseFromService() throws Exception {
//		setup();
//		List<EventDto> events = eventController.getClubEventsByMonthAndYear(null, "postman", "05", "2021").getBody();
//		
//		for(EventDto event: events)
//		{
//			assert(event.getClubName().contentEquals("postman"));
//			assert(event.getDate().contains("05/2021"));
//		}
//	}
//	
//	@Test
//	public void createEventShouldReturnResponseFromService() throws Exception {
//		setup();
//		String randomStr = "";
//		for(int i = 0; i < 6; i++)
//			randomStr += (char)(Math.random() * 25 + 97);
//		
//		EventDto event = new EventDto();
//		event.setClubName("postman");
//		event.setTitle(randomStr);
//		event.setDate("23/05/2021");
//		event.setTime("12:00");
//		event.setDescription("");
//		boolean result = eventController.addEvent(null, event).getBody();
//		
//		assert(result);
//	}
//	
//	@Test
//	public void modifyEventShouldReturnResponseFromService() throws Exception {
//		setup();
//		String randomStr = "";
//		for(int i = 0; i < 6; i++)
//			randomStr += (char)(Math.random() * 25 + 97);
//		
//		EventDto event = new EventDto();
//		event.setClubName("postman");
//		event.setTitle(randomStr);
//		event.setDate("23/05/2021");
//		event.setTime("12:00");
//		event.setDescription("");
//		eventController.addEvent(null, event);
//		
//		String oldTime = event.getTime();
//		event.setTime("11:00");
//		boolean result = eventController.modifyEvent(null, event).getBody();
//		
//		assert(result);
//	}
}
