package com.byteworks.devops.helpers;

import org.springframework.stereotype.Component;

@Component
public class RegistrationStatus {
    private boolean isSuccessful;

    public RegistrationStatus() {
    }

    public RegistrationStatus(boolean isSuccessful) {
        this.isSuccessful = isSuccessful;
    }

    public boolean isSuccessful() {
        return isSuccessful;
    }

    public void setSuccessful(boolean successful) {
        isSuccessful = successful;
    }
}
