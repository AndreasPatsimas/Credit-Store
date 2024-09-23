package gr.pwc.assignment;

import gr.pwc.assignment.config.BasicWiremockTest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@TestPropertySource(properties = {"spring.application.name=UserControllerTest",
        "spring.jmx.default-domain=UserControllerTest"})
public class UserControllerTest extends BasicWiremockTest {

    @Test
    public void overview() throws Exception {
        this.mockMvc.perform(get("/users/info?userId=7f31ab45-8e27-4a62-bd1e-9641d75bde22"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void findUsersBalances() throws Exception {
        this.mockMvc.perform(get("/users/balances"))
                .andDo(print())
                .andExpect(status().isOk());
    }
}
