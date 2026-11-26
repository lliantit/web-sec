package chnu.edu.websec;

import chnu.edu.websec.config.SecurityConfig;
import chnu.edu.websec.controller.CarController;
import chnu.edu.websec.service.CarService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Task 7. Access testing: 10 JUnit tests that check role-based access
 * (@PreAuthorize in CarController + SecurityConfig) with MockMvc.
 * The service layer is mocked, so no MongoDB is needed.
 */
@WebMvcTest(CarController.class)
@Import(SecurityConfig.class)
class CarAccessTests {

    private static final String BASE = "/api/v1/cars";
    private static final String CAR_JSON = "{\"name\":\"BMW M5\",\"year\":\"2024\"}";

    @Autowired
    private WebApplicationContext context;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders
                .webAppContextSetup(context)
                .apply(springSecurity())
                .build();
    }

    @MockitoBean
    private CarService carService;

    // 1
    @Test
    @DisplayName("1. Anonymous -> GET /cars -> 401")
    void anonymousCannotGetCars() throws Exception {
        mockMvc.perform(get(BASE)).andExpect(status().isUnauthorized());
    }

    // 2
    @Test
    @WithMockUser(username = "user", roles = "USER")
    @DisplayName("2. USER -> GET /cars -> 200")
    void userCanGetCars() throws Exception {
        mockMvc.perform(get(BASE)).andExpect(status().isOk());
    }

    // 3
    @Test
    @WithMockUser(username = "user", roles = "USER")
    @DisplayName("3. USER -> GET /hello/user -> 200")
    void userCanAccessHelloUser() throws Exception {
        mockMvc.perform(get(BASE + "/hello/user")).andExpect(status().isOk());
    }

    // 4
    @Test
    @WithMockUser(username = "admin", roles = "ADMIN")
    @DisplayName("4. ADMIN -> GET /hello/user -> 403")
    void adminCannotAccessHelloUser() throws Exception {
        mockMvc.perform(get(BASE + "/hello/user")).andExpect(status().isForbidden());
    }

    // 5
    @Test
    @WithMockUser(username = "user", roles = "USER")
    @DisplayName("5. USER -> GET /hello/admin -> 403")
    void userCannotAccessHelloAdmin() throws Exception {
        mockMvc.perform(get(BASE + "/hello/admin")).andExpect(status().isForbidden());
    }

    // 6
    @Test
    @WithMockUser(username = "admin", roles = "ADMIN")
    @DisplayName("6. ADMIN -> GET /hello/admin -> 200")
    void adminCanAccessHelloAdmin() throws Exception {
        mockMvc.perform(get(BASE + "/hello/admin")).andExpect(status().isOk());
    }

    // 7
    @Test
    @WithMockUser(username = "user", roles = "USER")
    @DisplayName("7. USER -> POST /cars -> 403")
    void userCannotCreateCar() throws Exception {
        mockMvc.perform(post(BASE)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(CAR_JSON))
                .andExpect(status().isForbidden());
    }

    // 8
    @Test
    @WithMockUser(username = "admin", roles = "ADMIN")
    @DisplayName("8. ADMIN -> POST /cars -> 200")
    void adminCanCreateCar() throws Exception {
        mockMvc.perform(post(BASE)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(CAR_JSON))
                .andExpect(status().isOk());
    }

    // 9
    @Test
    @WithMockUser(username = "admin", roles = "ADMIN")
    @DisplayName("9. ADMIN -> DELETE /cars/1 -> 403")
    void adminCannotDeleteCar() throws Exception {
        mockMvc.perform(delete(BASE + "/1")).andExpect(status().isForbidden());
    }

    // 10
    @Test
    @WithMockUser(username = "superadmin", roles = "SUPERADMIN")
    @DisplayName("10. SUPERADMIN -> DELETE /cars/1 -> 200")
    void superAdminCanDeleteCar() throws Exception {
        mockMvc.perform(delete(BASE + "/1")).andExpect(status().isOk());
    }
}