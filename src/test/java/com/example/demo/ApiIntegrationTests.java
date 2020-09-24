package com.example.demo;

import com.example.demo.model.Trainee;
import com.example.demo.model.Trainer;
import com.example.demo.repository.TraineeRepo;
import com.example.demo.repository.TrainerRepo;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import javax.transaction.Transactional;

import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
@ActiveProfiles("test")
class ApiIntegrationTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private TraineeRepo traineeRepo;

    @Autowired
    private TrainerRepo trainerRepo;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Test
    public void should_add_trainee_and_return_list_when_post_trainee() throws Exception {
        Trainee trainee = Trainee.builder()
            .name("小明")
            .email("xiaoming@gmail.com")
            .github("xiaoming")
            .office("深圳")
            .zoomId("xiaoming")
            .build();
        String returnString = mockMvc.perform(
            post("/trainees").content(objectMapper.writeValueAsString(trainee))
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("utf-8"))
            .andExpect(status().isCreated())
            .andReturn()
            .getResponse()
            .getContentAsString();
        trainee = objectMapper.readValue(returnString, Trainee.class);

        assertEquals("小明", traineeRepo.findById(trainee.getId()).orElseThrow(Exception::new).getName());
    }

    @Test
    public void should_throw_error_when_post_trainees_given_illegal_trainee_info() throws Exception {
        Trainee trainee = Trainee.builder()
            .name("小明")
            .email("xiaoming.com")
            .github("xiaoming")
            .office("深圳")
            .zoomId("xiaoming")
            .build();
        mockMvc.perform(post("/trainees").content(objectMapper.writeValueAsString(trainee))
            .contentType(MediaType.APPLICATION_JSON)
            .characterEncoding("utf-8"))
            .andExpect(status().isUnprocessableEntity())
            .andExpect(jsonPath("$.message").value("电子邮箱格式不合法"));

        assertEquals(35, traineeRepo.count());
    }

    @Test
    public void should_get_ungroup_list_before_grouping() throws Exception {
        mockMvc.perform(get("/trainees/ungroupedList"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$", hasSize(35)));
    }

    @Test
    public void should_delete_trainee_when_delete_id_given_trainee_id() throws Exception {
        Trainee trainee = Trainee.builder()
            .name("小明")
            .email("xiaoming@gmail.com")
            .github("xiaoming")
            .office("深圳")
            .zoomId("xiaoming")
            .build();
        String returnString = mockMvc.perform(
            post("/trainees").content(objectMapper.writeValueAsString(trainee))
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("utf-8"))
            .andExpect(status().isCreated())
            .andReturn()
            .getResponse()
            .getContentAsString();
        trainee = objectMapper.readValue(returnString, Trainee.class);
        mockMvc.perform(get("/trainees/ungroupedList"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$", hasSize(36)));
        mockMvc.perform(delete("/trainees/" + trainee.getId())).andExpect(status().isNoContent());

        assertEquals(35, traineeRepo.count());
    }

    @Test
    public void should_add_trainer_when_post_trainer() throws Exception {
        Trainer trainer = Trainer.builder().name("小明").build();
        String returnString = mockMvc.perform(
            post("/trainers").content(objectMapper.writeValueAsString(trainer))
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("utf-8"))
            .andExpect(status().isCreated())
            .andReturn()
            .getResponse()
            .getContentAsString();
        trainer = objectMapper.readValue(returnString, Trainer.class);

        assertEquals("小明", trainerRepo.findById(trainer.getId()).orElseThrow(Exception::new).getName());
    }

    @Test
    public void should_delete_trainer_when_delete_trainer_given_trainer_id() throws Exception {
        Trainer trainer = Trainer.builder().name("小明").build();
        String returnString = mockMvc.perform(
            post("/trainers").content(objectMapper.writeValueAsString(trainer))
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("utf-8"))
            .andExpect(status().isCreated())
            .andReturn()
            .getResponse()
            .getContentAsString();
        trainer = objectMapper.readValue(returnString, Trainer.class);

        mockMvc.perform(delete("/trainers/" + trainer.getId())).andExpect(status().isNoContent());
    }

    @Test
    public void should_get_ungrouped_trainer_when_get_trainers_ungrouped() throws Exception {
        mockMvc.perform(get("/trainers/ungrouped"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$", hasSize(8)));
    }
}
