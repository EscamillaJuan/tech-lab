package com.escamilla.dependency_injection;

import javax.inject.Inject;
import java.io.IOException;
import java.nio.file.Path;
import java.time.Clock;
import java.time.Duration;
import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

public class ExpirationChecker {
    private final MetadataFetcher metadataFetcher;
    private final Clock clock;

    /**
     * This constructor uses @javax.inject.Inject and receive parameters for the clock
     * and MetadataFetcher, implementation does not depend on this class, so we can
     * mock a clock in test or implement a production clock
     */
    @Inject
    ExpirationChecker(MetadataFetcher metadataFetcher, Clock clock) {
        this.metadataFetcher = metadataFetcher;
        this.clock = clock;
    }

    public List<Path> getExpiredFiles(List<Path> paths, Duration expiration) {
        return paths.stream()
                .filter((path -> isExpired(path, expiration)))
                .collect(Collectors.toList());
    }

    /**
     * This method uses clock.instant() instead of Instant.now()
     * this allows us to use a custom implementation of the clock instance
     * for testing purposes
     */
    private boolean isExpired(Path path, Duration expiration) {
        Instant now = clock.instant();
        try {
            Instant modifiedTime = metadataFetcher.getLastModifiedTime(path);
            return now.isAfter(modifiedTime.plus(expiration));
        } catch (IOException e) {
            return false;
        }
    }
}
