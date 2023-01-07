package com.event.manager.security.controller;

import com.event.manager.security.domain.exception.RoleNotFoundException;
import com.event.manager.security.domain.model.Role;
import com.event.manager.security.service.RoleService;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class RoleControllerTest {

    private final static String API_BASE_URI = "/api/v1/role";

    private final static String EXPECTED_NOT_FOUND_MESSAGE = "The asked role %s has been not found";

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper mapper;

    @MockBean
    private RoleService roleService;

    @Test
    void get_givenHappyPath_thenReturnRoleAndDefaultPermissions() throws Exception {

        // params
        String roleProvided = "SUPER_ADMIN";
        Role roleExpected = Role.SUPER_ADMIN;

        // mock
        when(this.roleService.get(roleProvided)).thenReturn(roleExpected);

        // execute
        MockHttpServletRequestBuilder requestBuilder = get(API_BASE_URI + "/" + roleProvided);
        ResultActions perform = this.mockMvc.perform(requestBuilder);

        ResultActions resultActions = perform.andDo(print()).andExpect(status().isOk());
        MockHttpServletResponse response = resultActions.andReturn().getResponse();
        String contentAsString = response.getContentAsString();

        // convert
        Role roleResponse = this.mapper.readValue(contentAsString, Role.class);

        // assert
        assertThat(roleResponse).isNotNull();
        assertThat(roleResponse).isEqualTo(roleExpected);

        // verify
        verify(this.roleService).get(roleProvided);
        verifyNoMoreInteractions(this.roleService);
    }

    @Test
    void get_givenUnknownRole_thenThrowRoleNotFoundException() throws Exception {

        // params
        String roleProvided = "UNKNOWN";

        // mock
        doThrow(new RoleNotFoundException(roleProvided))
                .when(this.roleService).get(roleProvided);

        // execute
        MockHttpServletRequestBuilder requestBuilder = get(API_BASE_URI + "/" + roleProvided);
        ResultActions perform = this.mockMvc.perform(requestBuilder);

        ResultActions resultActions = perform.andDo(print()).andExpect(status().isNotFound());
        MockHttpServletResponse response = resultActions.andReturn().getResponse();

        // assert
        assertThat(response.getErrorMessage()).isEqualTo("Role Not Found");

        // verify
        verify(this.roleService).get(roleProvided);
        verifyNoMoreInteractions(this.roleService);
    }

    @Test
    void getAll_givenHappyPath_thenReturnAllRolesWithDefaultPermissions() throws Exception {

        // mock
        when(this.roleService.getAll())
                .thenReturn(Arrays.stream(Role.values()).collect(Collectors.toSet()));

        // execute
        MockHttpServletRequestBuilder requestBuilder = get(API_BASE_URI);
        ResultActions perform = this.mockMvc.perform(requestBuilder);

        ResultActions resultActions = perform.andDo(print()).andExpect(status().isOk());
        MockHttpServletResponse response = resultActions.andReturn().getResponse();
        String contentAsString = response.getContentAsString();

        // convert
        JavaType roleSetType = this.mapper.getTypeFactory().constructCollectionType(Set.class, Role.class);
        Set<Role> roles = this.mapper.readValue(contentAsString, roleSetType);

        // assert
        assertThat(roles).hasSize(5);

        // verify
        verify(this.roleService).getAll();
        verifyNoMoreInteractions(this.roleService);
    }

}