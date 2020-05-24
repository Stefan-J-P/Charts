package interviewluty2020.listaprzebojow.service;

import interviewluty2020.listaprzebojow.dto.Mappers;
import interviewluty2020.listaprzebojow.dto.RegisterUserDto;
import interviewluty2020.listaprzebojow.exception.RegisterUserException;
import interviewluty2020.listaprzebojow.model.User;
import interviewluty2020.listaprzebojow.model.VerificationToken;
import interviewluty2020.listaprzebojow.repository.UserRepository;
import interviewluty2020.listaprzebojow.repository.VerificationTokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.security.saml2.Saml2RelyingPartyProperties;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class SecurityService
{
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final VerificationTokenRepository verificationTokenRepository;
    private final EmailService emailService;

    // --------------------------- REGISTER ---------------------------------
    public void register(RegisterUserDto registerUserDto)
    {
        if (registerUserDto == null)
        {
            throw new RegisterUserException("User registration - object is null");
        }

        User user = Mappers.fromRegisterUserDtoToUser(registerUserDto);
        user.setEnabled(true);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        var insertedUser = userRepository.save(user);
        sendActivationEmail(insertedUser);
    }

    // ------------------------ ACTIVATE USER ------------------------------
    public void activateUser(String token)
    {

        if (Objects.isNull(token)) {
            throw new SecurityException("activate user - token is null");
        }

        var verificationToken = verificationTokenRepository
                .findByToken(token)
                .orElseThrow(() -> new SecurityException("cannot find token"));

        var userToActivate = verificationToken.getUser();
        userToActivate.setEnabled(true);
        verificationTokenRepository.delete(verificationToken);
    }

    // ------------------------- SEND ACTIVATION EMAIL ---------------------------
    private void sendActivationEmail(User user)
    {

        if (Objects.isNull(user))
        {
            throw new SecurityException("send activation email - user is null");
        }

        var createdVerificationToken = verificationTokenRepository.save(VerificationToken
                .builder()
                .token(UUID.randomUUID().toString().replaceAll("\\W", ""))
                .user(user)
                .expirationDateTime(LocalDateTime.now().plusMinutes(5))
                .build());

        var url = "http://localhost:8080/security/confirm-registration?token=" + createdVerificationToken.getToken();
        var message = "Click to activate user: " + url;
        emailService.send(user.getEmail(), "Confirm registration", message);

    }




}
