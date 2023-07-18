package com.example.usersapi.shared;

import feign.Response;
import feign.codec.ErrorDecoder;
import lombok.AllArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

@AllArgsConstructor
@Component
public class FeignErrorDecoder implements ErrorDecoder {

    private Environment env;

    @Override
    public Exception decode(String methodKey, Response response) {
        switch (response.status()) {
            case 400: {
                //return new BadRequestException();
                break;
            }
            case 404: {
                if (methodKey.contains("getAlbums"))
                    return new ResponseStatusException(HttpStatus.valueOf(response.status()), env.getProperty("albums.exceptions.albums-not-found"));
            }
            default:
                return new Exception(response.reason());
        }
        return null;
    }
}
