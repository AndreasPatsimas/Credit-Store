package gr.pwc.assignment;

import gr.pwc.assignment.config.BasicWiremockTest;
import gr.pwc.assignment.dto.staff_members.StaffMemberRequestDTO;
import gr.pwc.assignment.enums.Role;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.http.MediaType;
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@TestPropertySource(properties = {"spring.application.name=StaffMemberControllerTest",
        "spring.jmx.default-domain=StaffMemberControllerTest"})
public class StaffMemberControllerTest extends BasicWiremockTest {

    @Test
    public void add() throws Exception {

        final String name = "Andreas Patsimas";
        final String userName = "andreaspts3";
        final String password = "ar!$1914#@";
        final Role role = Role.ROLE_OPERATOR;

        StaffMemberRequestDTO staffMemberRequest = new StaffMemberRequestDTO(name, userName, password, role);

        this.mockMvc.perform(RestDocumentationRequestBuilders
                        .post("/staff-members/add")
                        .principal(ADMIN_PRINCIPAL)
                        .content(asJsonString(staffMemberRequest)).contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void remove() throws Exception {
        this.mockMvc.perform(delete("/staff-members/remove/{staffMemberId}", 1)
                        .principal(ADMIN_PRINCIPAL))
                .andDo(print())
                .andExpect(status().isOk());
    }
}
