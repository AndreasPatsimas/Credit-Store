package gr.pwc.assignment;

import gr.pwc.assignment.config.BasicWiremockTest;
import gr.pwc.assignment.dto.authenticate.AuthenticationRequestDTO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@TestPropertySource(properties = {"spring.application.name=AuthenticationControllerTest",
        "spring.jmx.default-domain=AuthenticationControllerTest"})
public class AuthenticationControllerTest extends BasicWiremockTest {

    @Test
    public void authenticateSuccess() throws Exception {

        final String password = "superAdmin123!@#";

        AuthenticationRequestDTO authenticationRequest = new AuthenticationRequestDTO(ADMIN, password);

        this.mockMvc.perform(
                        post(CONTEXT_PATH + "/authenticate").contextPath(CONTEXT_PATH)
                                .content(asJsonString(authenticationRequest))
                                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE));
    }
}
