package com.example.DomguiaSimo_BankingApp;

import com.example.DomguiaSimo_BankingApp.Config.JWTServices;
import com.example.DomguiaSimo_BankingApp.User.User;
import com.example.DomguiaSimo_BankingApp.User.UserController;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import static org.mockito.Mockito.when;

@SpringBootTest
@AutoConfigureMockMvc
class DomguiaSimoBankingAppApplicationTests {

	@Mock
	JWTServices jwtService;
	@InjectMocks
	private UserController userController;
	@Mock
	private AuthenticationManager authenticationManager;


	@Test
	void contextLoads() {
	}

	@Autowired
	private MockMvc mockMvc;

//	Intergration testing
	@Test
	public void testGetUsersEndpoint() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/api/user/get-users"))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON));
	}


//	Unit testing
	@Test
	public void testLogin() {
		User user = new User();
		user.setEmail("test@example.com");
		user.setPassword("password");

		when(jwtService.generateToken(any(User.class))).thenReturn("testToken");

		ResponseEntity<?> response = userController.login(user);

		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals("testToken", response.getBody());
		verify(authenticationManager).authenticate(any(UsernamePasswordAuthenticationToken.class));
		verify(jwtService).generateToken(any(User.class));
	}

}
