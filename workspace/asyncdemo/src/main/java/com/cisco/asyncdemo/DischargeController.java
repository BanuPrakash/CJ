package com.cisco.asyncdemo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/discharge")
public class DischargeController {
    @Autowired
    private ApplicationEventPublisher eventPublisher;

    @GetMapping ()
    public String discharge(@RequestParam("id") String patientId, @RequestParam("name") String patientName) {
        System.out.println("Discharge initiated");
        eventPublisher.publishEvent(new PatientDischargeEvent(this, patientId, patientName));
//        processBill();
//        processHouseKeeping();
        return "Patient Discharged!!!";
    }
}
