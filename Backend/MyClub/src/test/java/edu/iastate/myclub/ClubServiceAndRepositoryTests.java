package edu.iastate.myclub;

//@DataJpaTest
//@AutoConfigureTestDatabase(replace = Replace.NONE)
public class ClubServiceAndRepositoryTests {
	
	//TODO works but need to find better approach which doesn't require actually updating the real database
	
//	private ClubController clubController;
//		
//	@Autowired
//	private ClubRepository clubRepository;
//	
//	@Autowired
//	private ContactDetailsRepository contactDetailsRepository;
//	
//	@Autowired
//	private PositionRepository positionRepository;
//	
//	@Autowired
//	private ClubLogoRepository clubLogoRepository;
//
//	private ClubService clubService;
//	
//	private static ClubDto testDto;
//	
//	public void setup()
//	{
//		clubService = new ClubService(clubRepository, contactDetailsRepository, positionRepository, clubLogoRepository);
//		clubController = new ClubController(clubService);
//		testDto = new ClubDto();
//		testDto.setName("ClubTest");
//		testDto.setDescription("Test Description");
//		testDto.setMeetingTimes("Test Meeting Times");
//		testDto.setContacts(new ArrayList<ContactDetailsDto>() {{add(new ContactDetailsDto("Graham", "123", "g@gmail.com"));}});
//		testDto.setOfficerPositions(new ArrayList<String>());
//	}
//	
//	@Test
//	public void createClubShouldReturnResponseFromService() throws Exception {
//		setup();
//		String randomStr = "";
//		for(int i = 0; i < 6; i++)
//			randomStr += (char)(Math.random() * 25 + 97);
//		testDto.setName(randomStr);
//		assert(clubRepository.findByName(randomStr) == null);
//		clubController.createClub(null, testDto);
//		
//		assert(clubRepository.findByName(randomStr) != null);
//	}
//	
////	@Test
////	public void modifyClubShouldReturnResponseFromService() throws Exception {
////		setup();
////		String randomStr = "";
////		for(int i = 0; i < 6; i++)
////			randomStr += (char)(Math.random() * 25 + 97);
////		System.out.println("A: " + randomStr);
////		Club c = clubRepository.findByName("ClubTest");
////		if(c == null)
////		{
////			c = new Club();
////			c.setName("ClubTest");
////		}
////		c.setDescription(randomStr);
////		clubRepository.save(c);
////		randomStr = "";
////		for(int i = 0; i < 6; i++)
////			randomStr += (char)(Math.random() * 25 + 97);
////		testDto.setDescription(randomStr);
////		System.out.println("B: " + randomStr);
////
////		assert(clubRepository.findByName("ClubTest") != null);
////		
////		clubController.modifyClub(testDto);
////		System.out.println("1: " + clubRepository.findByName("ClubTest").getDescription());
////		System.out.println("2: " + c.getDescription());
////		assert(clubRepository.findByName("ClubTest") != null);
////		assert(!clubRepository.findByName("ClubTest").getDescription().contentEquals(c.getDescription()));
////	}
//	
////	@Test
////	public void getJoinedClubsShouldReturnResponseFromService() throws Exception {
////		setup();
////		Club c = new Club();
////		c.setName("Test");
////		c.setMeetingTimes("test");
////		ArrayList<ClubBasicDto> clubs = new ArrayList<ClubBasicDto>() {
////			{
////				add(new ClubBasicDto(c));
////			}
////		};
////		when(clubService.getJoinedClubs("")).thenReturn(clubs);
////		MvcResult result = this.mockMvc.perform(get("/club/joined"))
////				.andExpect(status().isOk())
////				.andReturn();
////		System.out.println("Here");
////		System.out.println(objectMapper.writeValueAsString(clubs));
////		assert(result.getResponse().getContentAsString().contentEquals(objectMapper.writeValueAsString(clubs)));
////	}
////	
//	@Test
//	public void getClubsBySearchShouldReturnResponseFromService() throws Exception {
//		setup();
//		String randomStr = "";
//		for(int i = 0; i < 6; i++)
//			randomStr += (char)(Math.random() * 25 + 97);
//		testDto.setName(randomStr);
//		clubController.createClub(null, testDto);
//		
//		testDto.setName(randomStr + "11");
//		clubController.createClub(null, testDto);
//
//		List<ClubBasicDto> clubs = clubController.getClubsBySearch(null, randomStr, 0).getBody();
//		for(ClubBasicDto c: clubs)
//			assert(c.getName().contains(randomStr));
//	}
////	
////	@Test
////	public void getJoinedClubsNotificationsShouldReturnResponseFromService() throws Exception {
////		setup();
////		Club c = new Club();
////		c.setName("Test");
////		c.setMeetingTimes("test");
////		ArrayList<ClubNotification> clubs = new ArrayList<ClubNotification>() {
////			{
////				add(new ClubNotification(c));
////			}
////		};
////		when(clubService.getJoinedClubsNotifications("")).thenReturn(clubs);
////		MvcResult result = this.mockMvc.perform(get("/club/joined/notifications"))
////				.andExpect(status().isOk())
////				.andReturn();
////		
////		assert(result.getResponse().getContentAsString().contentEquals(objectMapper.writeValueAsString(clubs)));
////	}
}