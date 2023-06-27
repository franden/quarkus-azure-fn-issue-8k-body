package io.quarkus.issue.boundary;

import io.quarkus.funqy.Funq;
import io.quarkus.issue.control.StringCreator;
import jakarta.inject.Inject;

import java.util.Map;

public class GreetFunqy {

    @Inject
    StringCreator stringCreator;

    @Funq("funqy")
    public String getStringFunqy(Map<String, Integer> params) {
        Integer times = params.get("times");
        return stringCreator.createLongString(times);
    }
}
