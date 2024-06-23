package com.Authentication.Auth.Config;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.integration.support.json.Jackson2JsonObjectMapper;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.Serial;
import java.io.Serializable;
import java.nio.charset.StandardCharsets;
class CustomError
{
    private HttpStatus status;
    private String error;
    private String message;

    public CustomError(HttpStatus status, String error, String message) {
        this.status = status;
        this.error = error;
        this.message = message;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
@Component
public class CustomAccessEntryPoint implements AuthenticationEntryPoint, Serializable {
    @Serial
    private static final long serialVersionUID = -8970718410437077606L;

      // the Jackson object mapper bean we created in the config
    private final Jackson2JsonObjectMapper jackson2JsonObjectMapper;

    public CustomAccessEntryPoint(Jackson2JsonObjectMapper jackson2JsonObjectMapper) {
        this.jackson2JsonObjectMapper = jackson2JsonObjectMapper;
    }

    @Override
    public void commence(HttpServletRequest request,
                         HttpServletResponse response,
                         AuthenticationException e) throws IOException {


        CustomError error = new CustomError(HttpStatus.FORBIDDEN, "User Does not exists", "No user with the given email found");


        try {
            String json = jackson2JsonObjectMapper.toJson(error);
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.setContentType(MediaType.APPLICATION_JSON_VALUE);
            response.setCharacterEncoding(StandardCharsets.UTF_8.toString());
            response.getWriter().write(json);
        } catch (Exception e1) {
            e1.printStackTrace();
        }

    }
}
