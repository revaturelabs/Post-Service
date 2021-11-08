package com.reverse.postservice.controllers;

import com.reverse.postservice.services.PostDtoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.reverse.postservice.models.Post;
import com.reverse.postservice.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@WebMvcTest(value = PostController.class)
public class PostControllerTest {
    @Autowired private MockMvc mockMvc;
    @MockBean @Qualifier("PostService") PostService postService;
    @MockBean @Qualifier("PostDtoService") PostDtoService postDtoService;


    Post post;


    @BeforeEach
    public void setUpForTests() throws Exception {
        post = new Post();

    }

    @Test
    public void createPostTest() throws Exception {

        mockMvc.perform(
                        MockMvcRequestBuilders.post("/posts/create")
                                .content(asJsonString(post))
                                .contentType(MediaType.APPLICATION_JSON_VALUE)
                                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void getPostTest() throws Exception {
        mockMvc.perform(
                        MockMvcRequestBuilders.get("/posts/1")
                                .content(asJsonString(post))
                                .contentType(MediaType.APPLICATION_JSON_VALUE)
                                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isNotFound())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void likePostTest() throws Exception {
        mockMvc.perform(
                        MockMvcRequestBuilders.post("/posts/like")
                                .content(asJsonString(post))
                                .contentType(MediaType.APPLICATION_JSON_VALUE)
                                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }

//    @Test
//    public void editPostTest() throws Exception {
//        mockMvc.perform(
//                        MockMvcRequestBuilders.put("/posts/edit")
//                                .content(asJsonString(post))
//                                .contentType(MediaType.APPLICATION_JSON_VALUE)
//                                .accept(MediaType.APPLICATION_JSON))
//                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andDo(MockMvcResultHandlers.print());
//    }

    @Test
    public void deletePostTest() throws Exception {
        mockMvc.perform(
                        MockMvcRequestBuilders.delete("/posts/delete/1")
                                .content(asJsonString(post))
                                .contentType(MediaType.APPLICATION_JSON_VALUE)
                                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void getAllPostsTest() throws Exception {
        mockMvc.perform(
                        MockMvcRequestBuilders.get("/posts")
                                .content(asJsonString(post))
                                .contentType(MediaType.APPLICATION_JSON_VALUE)
                                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }


    private String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

}
