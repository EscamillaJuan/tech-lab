package com.escamilla.dependency_injection;

import com.google.inject.AbstractModule;

import java.time.Clock;

/**
 * The purpose of this module is to tell Guice (DI framework) which implementation
 * it should use for each dependency that ExpirationChecker needs to inject
 */
public class ExpirationCheckerModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(Clock.class).toInstance(Clock.systemUTC());
        bind(MetadataFetcher.class).to(MetadataFetcherImpl.class);
    }
}
