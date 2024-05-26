package com.quimbaya.clientes_api.security.aplication.service;


import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.quimbaya.clientes_api.user.domain.exception.UserNotFoundException;
import com.quimbaya.clientes_api.user.domain.model.Users;
import com.quimbaya.clientes_api.user.domain.port.out.UserRepositoryPort;

import java.util.List;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@Service
public class AuthenticationService implements UserDetailsService {

    private final UserRepositoryPort userRepositoryPort;
    private final JwtService jwtService;

  
    

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		Users userEntity=this.userRepositoryPort.findByEmail(email)
				.orElseThrow(() -> new UserNotFoundException((long) 1));
		
		// No autenticar al usuario aquí
		// Authentication  authenticationToken = new UsernamePasswordAuthenticationToken(userEntity.getUserEmail(), userEntity.getUserPass());
		// Authentication authentication = this.authenticationManager.authenticate(authenticationToken);
            
        /**return User.builder()
                .username(userEntity.getUserName())
                .password(userEntity.getUserPass())
                .authorities(this.grantedAuthorities("Admin"))
                //.accountLocked(userEntity.getFirst())
                //.disabled(userEntity.getState())
                .build();**/

        // ...

        return User.builder()
            .username(userEntity.getUserName())
            .password(userEntity.getUserPass())
            .authorities(List.of(new SimpleGrantedAuthority("ROLE_ADMIN"), new SimpleGrantedAuthority("ROLE_USER"))) // Aquí puedes agregar todos los roles que necesites
            .accountLocked(false) // Esto asegura que la cuenta no esté bloqueada
            .disabled(false) // Esto asegura que la cuenta esté habilitada
            .build();

	}
}
