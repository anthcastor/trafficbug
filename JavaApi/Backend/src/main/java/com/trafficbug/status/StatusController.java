package com.trafficbug.status;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@RestController
public class StatusController {


    @GetMapping("/status")
    public ResponseEntity<String> getStatus() {
        LocalDateTime rightNow = LocalDateTime.now();
        String timeStamp = rightNow.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        return ResponseEntity.ok("TrafficBug is operational as of: " + timeStamp);
    }
}