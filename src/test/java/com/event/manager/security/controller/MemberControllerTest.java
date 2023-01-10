package com.event.manager.security.controller;

import com.event.manager.security.config.DefaultSpringBootTest;
import com.event.manager.security.domain.exception.notfound.MemberNotFoundException;
import com.event.manager.security.domain.model.api.MemberDTO;
import com.event.manager.security.service.MemberService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.MOCK;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@DefaultSpringBootTest(webEnvironment = MOCK)
class MemberControllerTest {
    private final static String API_BASE_URI = "/api/v1/member";
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper mapper;

    @MockBean
    private MemberService memberService;

    @Test
    void get_givenHappyPath_thenReturnMember() throws Exception {

        // params
        String usernamedExpected = "usernamedExpected";
        String emailExpected = "emailExpected";

        // mock
        when(this.memberService.getByUsername(usernamedExpected)).thenReturn(new MemberDTO(usernamedExpected, emailExpected));

        // execute
        MockHttpServletRequestBuilder requestBuilder = get(API_BASE_URI + "/" + usernamedExpected);
        ResultActions perform = this.mockMvc.perform(requestBuilder);

        ResultActions resultActions = perform.andDo(print()).andExpect(status().isOk());
        MockHttpServletResponse response = resultActions.andReturn().getResponse();
        String contentAsString = response.getContentAsString();

        // convert
        MemberDTO memberResponse = this.mapper.readValue(contentAsString, MemberDTO.class);

        // assert
        assertThat(memberResponse).isNotNull();
        assertThat(memberResponse.getUsername()).isEqualTo(usernamedExpected);
        assertThat(memberResponse.getEmail()).isEqualTo(emailExpected);

        // verify
        verify(this.memberService).getByUsername(usernamedExpected);
        verifyNoMoreInteractions(this.memberService);
    }

    @Test
    void get_givenUnknownUsername_thenThrowMemberNotFoundException() throws Exception {

        // params
        String usernameProvided = "UNKNOWN";

        // mock
        doThrow(new MemberNotFoundException(usernameProvided))
                .when(this.memberService).getByUsername(usernameProvided);

        // execute
        MockHttpServletRequestBuilder requestBuilder = get(API_BASE_URI + "/" + usernameProvided);
        ResultActions perform = this.mockMvc.perform(requestBuilder);

        ResultActions resultActions = perform.andDo(print()).andExpect(status().isNotFound());
        MockHttpServletResponse response = resultActions.andReturn().getResponse();

        // assert
        assertThat(response.getErrorMessage()).isEqualTo("Member Not Found");

        // verify
        verify(this.memberService).getByUsername(usernameProvided);
        verifyNoMoreInteractions(this.memberService);
    }
}