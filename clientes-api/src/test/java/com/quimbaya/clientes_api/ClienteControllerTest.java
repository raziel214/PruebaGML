package com.quimbaya.clientes_api;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.quimbaya.clientes_api.cliente.domain.model.Cliente;
import com.quimbaya.clientes_api.cliente.domain.port.in.command.CreateClienteCommand;
import com.quimbaya.clientes_api.cliente.domain.port.in.command.DeleteClienteCommand;
import com.quimbaya.clientes_api.cliente.domain.port.in.command.UpdateClienteCommand;
import com.quimbaya.clientes_api.cliente.domain.port.in.query.FetchAllClientesQuery;
import com.quimbaya.clientes_api.cliente.domain.port.in.query.FindClienteByIdQuery;
import com.quimbaya.clientes_api.cliente.infrastructure.web.dto.CreateClienteRequest;
import com.quimbaya.clientes_api.cliente.infrastructure.web.dto.UpdateClienteRequest;
import com.quimbaya.clientes_api.shared.cqrs.CommandBus;
import com.quimbaya.clientes_api.shared.cqrs.QueryBus;

@SpringBootTest
@AutoConfigureMockMvc
@WithMockUser
@ActiveProfiles("test")
class ClienteControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CommandBus commandBus;

    @MockBean
    private QueryBus queryBus;

    private ObjectMapper objectMapper;

    @BeforeEach
    void setup() {
        objectMapper = new ObjectMapper();
    }

    @Test
    void createClienteReturnsCreatedResponse() throws Exception {
        Cliente created = Cliente.reconstitute(1L, "key123", "John", "Doe", "john.doe@example.com", "3001234567");
        when(commandBus.dispatch(any(CreateClienteCommand.class))).thenReturn(created);

        CreateClienteRequest request = new CreateClienteRequest(
                "key123", "John", "Doe", "john.doe@example.com", "3001234567");

        mockMvc.perform(post("/api/cliente")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.sharedKey").value("key123"))
                .andExpect(jsonPath("$.nombre").value("John"))
                .andExpect(jsonPath("$.apellido").value("Doe"))
                .andExpect(jsonPath("$.correo").value("john.doe@example.com"))
                .andExpect(jsonPath("$.telefono").value("3001234567"));
    }

    @Test
    void findClienteByIdReturnsCliente() throws Exception {
        Cliente cliente = Cliente.reconstitute(52L, "key123", "John", "Doe", "john.doe@example.com", "3001234567");
        when(queryBus.dispatch(any(FindClienteByIdQuery.class))).thenReturn(cliente);

        mockMvc.perform(get("/api/cliente/{id}", 52L))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(52))
                .andExpect(jsonPath("$.nombre").value("John"));
    }

    @Test
    void fetchAllClientesReturnsList() throws Exception {
        Cliente cliente = Cliente.reconstitute(1L, "key123", "Jane", "Roe", "jane@example.com", "3009999999");
        when(queryBus.dispatch(any(FetchAllClientesQuery.class))).thenReturn(List.of(cliente));

        mockMvc.perform(get("/api/cliente"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].nombre").value("Jane"));
    }

    @Test
    void updateClienteReturnsUpdated() throws Exception {
        Cliente updated = Cliente.reconstitute(52L, "key123", "John", "Doe", "john.doe@example.com", "987654321");
        when(commandBus.dispatch(any(UpdateClienteCommand.class))).thenReturn(updated);

        UpdateClienteRequest request = new UpdateClienteRequest(
                "key123", "John", "Doe", "john.doe@example.com", "987654321");

        mockMvc.perform(put("/api/cliente/{id}", 52L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.telefono").value("987654321"));
    }

    @Test
    void deleteClienteReturnsNoContent() throws Exception {
        when(commandBus.dispatch(any(DeleteClienteCommand.class))).thenReturn(null);

        mockMvc.perform(delete("/api/cliente/{id}", 52L))
                .andExpect(status().isNoContent());
    }
}
