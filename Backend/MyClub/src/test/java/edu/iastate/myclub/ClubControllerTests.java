package edu.iastate.myclub;

import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.util.LinkedMultiValueMap;

import com.fasterxml.jackson.databind.ObjectMapper;

import edu.iastate.myclub.controllers.club.ClubController;
import edu.iastate.myclub.models.ContactDetailsDto;
import edu.iastate.myclub.models.club.ClubBasicDto;
import edu.iastate.myclub.models.club.ClubDto;
import edu.iastate.myclub.services.club.ClubService;

@WebMvcTest(ClubController.class)
public class ClubControllerTests {
	
	@Autowired
	private ClubController clubController;
	
	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	private ObjectMapper objectMapper;

	@MockBean
	private ClubService clubService;
	
	private static ClubDto testDto;
	
	public void setup()
	{
		testDto = new ClubDto();
		testDto.setName("ClubTest");
		testDto.setDescription("Test Description");
		testDto.setMeetingTimes("Test Meeting Times");
		testDto.setContacts(new ArrayList<ContactDetailsDto>() {{add(new ContactDetailsDto());}});
		testDto.setOfficerPositions(new ArrayList<String>());
	}
	
	@Test
	public void createClubShouldReturnResponseFromService() throws Exception {
		setup();
		when(clubService.createClub(new ClubDto())).thenReturn(true);
		MvcResult result = this.mockMvc.perform(post("/club/create")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(new ClubDto())))
				.andExpect(status().isOk())
				.andExpect(content().string("true"))
				.andReturn();
		
		System.out.println(result.getResponse().getContentAsString());
	}
	
	@Test
	public void modifyClubShouldReturnResponseFromService() throws Exception {
		setup();
		when(clubService.modifyClub(new ClubDto())).thenReturn(true);
		MvcResult result = this.mockMvc.perform(post("/club/modify")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(new ClubDto())))
				.andExpect(status().isOk())
				.andExpect(content().string("true"))
				.andReturn();
		
		System.out.println(result.getResponse().getContentAsString());
	}
	
//	@Test
//	public void getJoinedClubsShouldReturnResponseFromService() throws Exception {
//		setup();
//		Club c = new Club();
//		c.setName("Test");
//		c.setMeetingTimes("test");
//		ArrayList<ClubBasicDto> clubs = new ArrayList<ClubBasicDto>() {
//			{
//				add(new ClubBasicDto(c));
//			}
//		};
//		when(clubService.getJoinedClubs("")).thenReturn(clubs);
//		MvcResult result = this.mockMvc.perform(get("/club/joined"))
//				.andExpect(status().isOk())
//				.andReturn();
//		System.out.println("Here");
//		System.out.println(objectMapper.writeValueAsString(clubs));
//		assert(result.getResponse().getContentAsString().contentEquals(objectMapper.writeValueAsString(clubs)));
//	}
	
	@Test
	public void getClubsBySearchShouldReturnResponseFromService() throws Exception {
		setup();
		LinkedMultiValueMap<String, String> requestParams = new LinkedMultiValueMap<>();
				requestParams.add("phrase", "Te");
				requestParams.add("page", "3");
		List<ClubBasicDto> clubs = new ArrayList<ClubBasicDto>() {
			{
				add(new ClubBasicDto());
			}
		};
		when(clubService.findClubs("Te", 3, 5)).thenReturn(clubs);
		MvcResult result = this.mockMvc.perform(get("/club/search").params(requestParams))
				.andDo(print())
				.andExpect(status().isOk())
				.andReturn();
		assert(result.getResponse().getContentAsString().contentEquals(clubs.toString()));
	}
	
//	@Test
//	public void getJoinedClubsNotificationsShouldReturnResponseFromService() throws Exception {
//		setup();
//		Club c = new Club();
//		c.setName("Test");
//		c.setMeetingTimes("test");
//		ArrayList<ClubNotification> clubs = new ArrayList<ClubNotification>() {
//			{
//				add(new ClubNotification(c));
//			}
//		};
//		when(clubService.getJoinedClubsNotifications("", 0)).thenReturn(clubs);
//		MvcResult result = this.mockMvc.perform(get("/club/joined/notifications"))
//				.andExpect(status().isOk())
//				.andReturn();
//		
//		assert(result.getResponse().getContentAsString().contentEquals(objectMapper.writeValueAsString(clubs)));
//	}
}
