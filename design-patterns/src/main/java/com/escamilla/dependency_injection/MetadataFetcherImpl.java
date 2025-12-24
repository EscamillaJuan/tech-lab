package com.escamilla.dependency_injection;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.time.Instant;

public class MetadataFetcherImpl implements MetadataFetcher{
    @Override
    public Instant getLastModifiedTime(Path path) throws IOException {
        BasicFileAttributes attributes =
                Files.readAttributes(path, BasicFileAttributes.class);
        return attributes.lastModifiedTime().toInstant();
    }
}
