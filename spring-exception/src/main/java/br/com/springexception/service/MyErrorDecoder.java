package br.com.springexception.service;

import br.com.springexception.throwables.SpringBootException;
import com.fasterxml.jackson.databind.ObjectMapper;
import feign.Response;
import feign.codec.ErrorDecoder;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;

/**
 * @author Joel Rodrigues Moreira on 18/04/18.
 * e-mail: <a href="mailto:joel.databox@gmail.com">joel.databox@gmail.com</a>
 * @project spring-cloud
 */
public class MyErrorDecoder implements feign.codec.ErrorDecoder {

    private final ErrorDecoder defaultErrorDecoder = new Default();
    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public Exception decode(String methodKey, Response response) {
        if (response.status() >= 400 && response.status() <= 499) {
            try {
                throw objectMapper.readValue(response.body().asInputStream(), SpringBootException.class);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return defaultErrorDecoder.decode(methodKey, response);
    }
}