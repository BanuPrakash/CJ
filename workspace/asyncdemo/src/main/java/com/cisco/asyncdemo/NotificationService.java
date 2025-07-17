package com.cisco.asyncdemo;

import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {

    @EventListener
    @Async
    public void processNotification(PatientDischargeEvent event) {
        System.out.println("Thread " + Thread.currentThread() + " send notification to " +
                event.getPatientName());
    }
}
