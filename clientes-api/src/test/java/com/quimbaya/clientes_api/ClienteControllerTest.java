package com.quimbaya.clientes_api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.quimbaya.clientes_api.cliente.domain.model.Cliente;
import com.quimbaya.clientes_api.cliente.domain.port.in.CreateClienteUseCase;
import com.quimbaya.clientes_api.cliente.domain.port.in.DeleteClienteUseCase;
import com.quimbaya.clientes_api.cliente.domain.port.in.FindClienteByIdUseCase;
import com.quimbaya.clientes_api.cliente.domain.port.in.UpdateClienteUseCase;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;

import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@SpringBootTest
@AutoConfigureMockMvc 
public class ClienteControllerTest {

	 	@Autowired
	    private MockMvc mockMvc;

	    @MockBean
	    private CreateClienteUseCase createClienteUseCase;

	    @MockBean
	    private DeleteClienteUseCase deleteClienteUseCase;

	    @MockBean
	    private FindClienteByIdUseCase findClienteByIdUseCase;

	    @MockBean
	    private UpdateClienteUseCase updateClienteUseCase;

	    private ObjectMapper objectMapper;

	    @BeforeEach
	    public void setup() {
	        objectMapper = new ObjectMapper();
	    }

	    @Test
	    public void testCreateCliente() throws Exception {
	        Cliente cliente = new Cliente(null,"sharedKey123", "John", "Doe", "john.doe@example.com", "123456789");
	        Mockito.when(createClienteUseCase.createCliente(Mockito.any(Cliente.class))).thenReturn(cliente);

	        mockMvc.perform(post("/api/cliente")
	                .contentType(MediaType.APPLICATION_JSON)
	                .content(objectMapper.writeValueAsString(cliente)))
	                .andExpect(status().isOk())
	                .andExpect(jsonPath("$.sharedKey").value("sharedKey123"))
	                .andExpect(jsonPath("$.nombre").value("John"))
	                .andExpect(jsonPath("$.apellido").value("Doe"))
	                .andExpect(jsonPath("$.correo").value("john.doe@example.com"))
	                .andExpect(jsonPath("$.telefono").value("123456789"));
	    }

	    @Test
	    public void testFindClienteById() throws Exception {
	        Cliente cliente = new Cliente(null,"sharedKey123", "John", "Doe", "john.doe@example.com", "123456789");
	        Mockito.when(findClienteByIdUseCase.findById(52L)).thenReturn(Optional.of(cliente));

	        mockMvc.perform(get("/api/cliente/{id}", 1L)
	                .contentType(MediaType.APPLICATION_JSON))
	                .andExpect(status().isOk())
	                .andExpect(jsonPath("$.sharedKey").value("sharedKey123"))
	                .andExpect(jsonPath("$.nombre").value("John"))
	                .andExpect(jsonPath("$.apellido").value("Doe"))
	                .andExpect(jsonPath("$.correo").value("john.doe@example.com"))
	                .andExpect(jsonPath("$.telefono").value("123456789"));
	    }

	    @Test
	    public void testUpdateCliente() throws Exception {
	        Cliente updatedCliente = new Cliente(null, "sharedKey123", "John", "Doe", "john.doe@example.com", "987654321");
	        Mockito.when(updateClienteUseCase.updateCliente(Mockito.anyLong(), Mockito.any(Cliente.class))).thenReturn(updatedCliente);

	        mockMvc.perform(put("/api/cliente/{id}", 52L)
	                .contentType(MediaType.APPLICATION_JSON)
	                .content(objectMapper.writeValueAsString(updatedCliente)))
	                .andExpect(status().isOk())
	                .andExpect(jsonPath("$.telefono").value("987654321"));
	    }

	    @Test
	    public void testDeleteCliente() throws Exception {
	        Mockito.when(deleteClienteUseCase.deleteById(1L)).thenReturn(true);

	        mockMvc.perform(delete("/api/cliente/{id}", 52L)
	                .contentType(MediaType.APPLICATION_JSON))
	                .andExpect(status().isOk());
	    }
}
