package edu.iastate.myclub;

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

import edu.iastate.myclub.controllers.event.EventController;
import edu.iastate.myclub.models.event.EventDto;
import edu.iastate.myclub.services.event.EventService;

@WebMvcTest(EventController.class)
public class EventControllerTests {

	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	private ObjectMapper objectMapper;

	@MockBean
	private EventService eventService;
	
	@Test
	void contextLoads() {
	}
	
	@Test
	public void getClubEventsByMonthAndYearShouldReturnResponseFromService() throws Exception {
		LinkedMultiValueMap<String, String> requestParams = new LinkedMultiValueMap<>();
		requestParams.add("club", "TestClub");
		requestParams.add("month", "05");
		requestParams.add("year", "2021");
		
		List<EventDto> events = new ArrayList<EventDto>() {
			{
				add(new EventDto());
			}
		};
		
		when(eventService.findClubEventsByMonthAndYear("TestClub", "05/2021")).thenReturn(events);
		MvcResult result = this.mockMvc.perform(get("/event/scheduled").params(requestParams))
				.andDo(print())
				.andExpect(status().isOk())
				.andReturn();
		
		assert(result.getResponse().getContentAsString().contentEquals(objectMapper.writeValueAsString(events)));

	}
	
	@Test
	public void createEventShouldReturnResponseFromService() throws Exception {
		when(eventService.addEvent(new EventDto())).thenReturn(true);
		MvcResult result = this.mockMvc.perform(post("/event/add")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(new EventDto())))
				.andDo(print())
				.andExpect(content().string("true"))
				.andReturn();
		}
	
	@Test
	public void modifyEventShouldReturnResponseFromService() throws Exception {
		when(eventService.modifyEvent(new EventDto())).thenReturn(true);
		MvcResult result = this.mockMvc.perform(post("/event/modify")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(new EventDto())))
				.andExpect(status().isOk())
				.andExpect(content().string("true"))
				.andReturn();
		
		System.out.println(result.getResponse().getContentAsString());
	}
}
