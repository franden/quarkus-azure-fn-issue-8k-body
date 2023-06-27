package io.quarkus.issue.control;

import jakarta.enterprise.context.ApplicationScoped;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.charset.StandardCharsets;

@ApplicationScoped
public class StringCreator {

    @ConfigProperty(name = "quarkus.http.limits.max-body-size")
    String maxBodySize;

    @ConfigProperty(name = "quarkus.http.limits.max-chunk-size")
    String maxChunkSize;

    public String createLongString(Integer times) {
        String basicString = "123456789asdfghjkl3245ffffff23t4rg4rt45zrt57h5g4jhg54kk4jj5k4hj3lk45jh4l5hjk4öl5kjl4ö5jk3lö453lkä4lö";
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < times; i++) {
            sb.append(basicString);
        }
        String finalString = sb.toString();
        Logger logger = LoggerFactory.getLogger(getClass());
        logger.info("Built string with {} chars. bytes: {}", finalString.length(), finalString.getBytes(StandardCharsets.UTF_8).length);

        logger.info("java.version: {}", System.getProperty("java.version"));
        logger.info("MaxBodySize: {}", maxBodySize);
        logger.info("MaxChunkSize: {}", maxChunkSize);
        return finalString;
    }
}
