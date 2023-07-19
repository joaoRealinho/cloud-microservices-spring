package com.example.usersapi.data;

import com.example.usersapi.model.AlbumResponseModel;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;

@FeignClient(name = "albums-ws")
public interface AlbumsServiceClient {

    //@GetMapping(value = "/users/{id}/albumss") //To get 404
    @GetMapping(value = "/users/{id}/albums")
    @Retry(name = "album-ws")  //don't need to put fallbackMethod because circuitBreaker already has it
    @CircuitBreaker(name = "albums-ws", fallbackMethod = "getAlbumsFallBack")
    List<AlbumResponseModel> getAlbums(@PathVariable String id);


    default List<AlbumResponseModel> getAlbumsFallBack(String id, Throwable exception) {
        System.out.println("Param =" + id);
        System.out.println("Exception took place: " + exception.getMessage());
        return new ArrayList<>();
    }

}