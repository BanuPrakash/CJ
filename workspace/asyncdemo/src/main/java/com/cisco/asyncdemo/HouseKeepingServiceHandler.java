package com.cisco.asyncdemo;

import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class HouseKeepingServiceHandler {

    @EventListener
    @Async
    public void processHouseKeeping(PatientDischargeEvent event) {
        System.out.println("Thread " + Thread.currentThread() + " handling house keeping  of " +
                event.getPatientName());
    }
}
