package com.trafficbug.alert;

import com.trafficbug.model.AlertPayload;
import com.trafficbug.service.PushAlertService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/alert")
public class AlertController {
    private final PushAlertService pushAlertService;

    @Autowired
    public AlertController(PushAlertService pushAlertService) {
        this.pushAlertService = pushAlertService;
    }

    @PostMapping
    public ResponseEntity<String> sendAlert(@RequestBody AlertPayload payload) {
        pushAlertService.dispatchAlert(payload);
        return ResponseEntity.ok("Alert dispatched");
    }
}
