package org.escamilla.proxy;

import java.time.Duration;
import java.time.Instant;
import java.time.ZoneId;

public interface Client {
    String getName();
    long getId();
    int getQuarterlyBudget();
    int getNumEmployees();
    Instant getContractStart();
    Duration getContractLength();
    ZoneId getTimeZone();
    String getBillingAddress();
}
