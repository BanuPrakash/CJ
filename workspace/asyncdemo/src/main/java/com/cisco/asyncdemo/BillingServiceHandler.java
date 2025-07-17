package com.cisco.asyncdemo;

import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class BillingServiceHandler {

    @EventListener
    @Async
    public void processBill(PatientDischargeEvent event) {
        System.out.println("Thread " + Thread.currentThread() + " handling bill process of " +
                event.getPatientName());
    }
}
