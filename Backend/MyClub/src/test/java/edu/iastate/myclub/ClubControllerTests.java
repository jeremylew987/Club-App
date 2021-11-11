package edu.iastate.myclub;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.util.LinkedMultiValueMap;

import com.fasterxml.jackson.databind.ObjectMapper;

import edu.iastate.myclub.controllers.club.ClubController;
import edu.iastate.myclub.models.club.Club;
import edu.iastate.myclub.models.club.ClubBasicDto;
import edu.iastate.myclub.models.club.ClubDto;
import edu.iastate.myclub.services.club.ClubService;

@WebMvcTest(ClubController.class)
public class ClubControllerTests {

	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	private ObjectMapper objectMapper;

	@MockBean
	private ClubService clubService;
	
	@Test
	void contextLoads() {
	}

	@Test
	public void createClubShouldReturnResponseFromService() throws Exception {
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
		when(clubService.modifyClub(new ClubDto())).thenReturn(true);
		MvcResult result = this.mockMvc.perform(post("/club/modify")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(new ClubDto())))
				.andExpect(status().isOk())
				.andExpect(content().string("true"))
				.andReturn();
		
		System.out.println(result.getResponse().getContentAsString());
	}
	
//	TODO fix
//	@Test
//	public void getJoinedClubsShouldReturnResponseFromService() throws Exception {
//		Club c = new Club();
//		c.setName("Test");
//		ArrayList<ClubDto> clubs = new ArrayList<ClubDto>() {
//			{
//				add(new ClubDto(c));
//			}
//		};
//		when(clubService.getJoinedClubs("test")).thenReturn(clubs);
//		MvcResult result = this.mockMvc.perform(get("/club/joined"))
//				.andExpect(status().isOk())
//				.andExpect(content().string(clubs.toString()))
//				.andReturn();
//		
//		System.out.println("here");
//		//System.out.println(result.getResponse().getContentAsString());
//	}
//	
	//TODO fix
	@Test
	public void getClubsBySearchShouldReturnResponseFromService() throws Exception {
//		Club c = new Club();
//		c.setName("Test");
		LinkedMultiValueMap<String, String> requestParams = new LinkedMultiValueMap<>();
				requestParams.add("phrase", "Te");
				requestParams.add("page", "3");
		List<ClubBasicDto> clubs = new ArrayList<ClubBasicDto>() {
			{
				add(new ClubBasicDto());
			}
		};
		when(clubService.findClubs("Te", "3", 5)).thenReturn(clubs);
		MvcResult result = this.mockMvc.perform(get("/club/search").params(requestParams))
				.andDo(print())
				.andExpect(status().isOk())
				.andReturn();
		assert(result.getResponse().getContentAsString().contentEquals(clubs.toString()));
		//System.out.println("here");
	}
	
	//TODO finish when user types are contributed
//	@Test
//	public void getJoinedClubsNotificationsShouldReturnResponseFromService() throws Exception {
//		Club c = new Club();
//		c.setName("Test");
//		ArrayList<ClubDto> clubs = new ArrayList<ClubDto>() {
//			{
//				add(new ClubDto(c));
//			}
//		};
//		when(clubService.getJoinedClubs("test")).thenReturn(clubs);
//		MvcResult result = this.mockMvc.perform(get("/club/joined"))
//				.andExpect(status().isOk())
//				.andExpect(content().string(clubs.toString()))
//				.andReturn();
//		
//		System.out.println("here");
//		//System.out.println(result.getResponse().getContentAsString());
//	}
}

//@SpringBootTest
//class ClubControllerTests {
//
//	@Test
//	void contextLoads() {
//	}
//
//}
