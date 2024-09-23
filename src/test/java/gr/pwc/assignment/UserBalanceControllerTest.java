package gr.pwc.assignment;

import gr.pwc.assignment.config.BasicWiremockTest;
import gr.pwc.assignment.dto.users.EditUserBalanceDTO;
import gr.pwc.assignment.enums.BalanceActionType;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.http.MediaType;
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigDecimal;
import java.util.UUID;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@TestPropertySource(properties = {"spring.application.name=UserBalanceControllerTest",
        "spring.jmx.default-domain=UserBalanceControllerTest"})
public class UserBalanceControllerTest extends BasicWiremockTest {

    @Test
    public void modify() throws Exception {

        EditUserBalanceDTO cardDetails = new EditUserBalanceDTO(
                BalanceActionType.DECREMENT, UUID.fromString("e9b5b8c6-77f6-4859-9ef5-872c1329e8f7"), BigDecimal.valueOf(1750.26));

        this.mockMvc.perform(RestDocumentationRequestBuilders
                        .patch("/user-balances")
                        .principal(OPERATOR_PRINCIPAL)
                        .content(asJsonString(cardDetails)).contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }
}
