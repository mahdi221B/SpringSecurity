package tn.esprit.spring.services;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.var;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import tn.esprit.spring.entity.Roles;
import tn.esprit.spring.entity.User;
import tn.esprit.spring.entity.auth.AuthenticationRequest;
import tn.esprit.spring.entity.auth.AuthenticationResponse;
import tn.esprit.spring.entity.auth.RegisterRequest;
import tn.esprit.spring.repositories.RolesRepository;
import tn.esprit.spring.repositories.UtilisateurRepository;
import javax.transaction.Transactional;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
@Slf4j
@Service
@AllArgsConstructor
public class UtilisateurServiceImp implements UtilisateurService {
    private final UtilisateurRepository utilisateurRepository;
    private final RolesRepository rolesRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

  @Override
    public User addUser(User dto) {
        return utilisateurRepository.save(dto);
    }

    @Override
    public Roles addRole(Roles role) {
        return rolesRepository.save(role);
    }

    @Transactional
    public void addRoleToUser(String roleName, String userName) {
        Roles role = rolesRepository.findRolesByName(roleName);
        User user = utilisateurRepository.findUtilisateurByName(userName);
        user.getRolesList().add(role);
    }

    public AuthenticationResponse register(RegisterRequest request) {
      User user = new User();
        user.setName(request.getFirstname());
        user.setEmail(request.getEmail());
        user.setMotDePasse(passwordEncoder.encode(request.getPassword()));
        user.setRolesList(request.getRolesList());
        utilisateurRepository.save(user);
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .accessToken(jwtToken)
                .build();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        User user = utilisateurRepository.findUtilisateurByEmail(request.getEmail());
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .accessToken(jwtToken)
                .build();
    }
}