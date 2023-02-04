package io.getarrays.server.resource;

import io.getarrays.server.enumeration.Status;
import io.getarrays.server.model.Response;
import io.getarrays.server.model.Server;
import io.getarrays.server.service.implementation.ServerServiceImpl;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.jpa.support.PageableUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;

import static io.getarrays.server.enumeration.Status.SERVER_UP;
import static java.time.LocalDateTime.now;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.MediaType.IMAGE_PNG_VALUE;


@RestController
@RequestMapping("/server")
@RequiredArgsConstructor
public class ServerResource {
    private final ServerServiceImpl serverService;

    @GetMapping("/list")
    public ResponseEntity<Response> getService() {
        return  ResponseEntity.ok(Response.builder()
                .timeStamp(now()).data(Map.of("server",serverService.List(30)))
                .message("Server retrieved")
                .status(OK)
                .statusCode(OK.value())
                .build());
    }
    @GetMapping("/ping/{ipAddress}")

    public ResponseEntity<Response> pinngServer(@PathVariable("ipAddress") String ipAddress) throws IOException {
        Server server = serverService.ping(ipAddress);
        return  ResponseEntity.ok(Response.builder()
                .timeStamp(now()).data(Map.of("server",server))
                .message(server.getStatus() == SERVER_UP? "Ping Success":"Ping faild")
                .status(OK)
                .statusCode(OK.value())
                .build());

    }

    @PostMapping("/save")
    public  ResponseEntity<Response> saveServer(@RequestBody @Valid Server server){
        return  ResponseEntity.ok(Response.builder()
                .timeStamp(now()).data(Map.of("server",serverService.create(server)))
                .message("Server Created")
                .status(CREATED)
                .statusCode(CREATED.value())
                .build());


    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Response> getServer(@PathVariable("id") Long id){
        return  ResponseEntity.ok(Response.builder()
                .timeStamp(now()).data(Map.of("server",serverService.get(id)))
                .message("Server Retrive")
                .status(OK)
                .statusCode(OK.value())
                .build());



    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Response> deleteServer(@PathVariable("id") Long id){
        return  ResponseEntity.ok(Response.builder()
                .timeStamp(now()).data(Map.of("server",serverService.delete(id)))
                .message("Server deeleted")
                .status(OK)
                .statusCode(OK.value())
                .build());



    }

    @GetMapping(path = "/image/{fileName}", produces = IMAGE_PNG_VALUE)
    public byte[] getServerImage(@PathVariable("fileName") String fileName) throws IOException {
        return Files.readAllBytes(Paths.get(System.getProperty("user.home") + "/Downloads/image/" + fileName));

    }


}
