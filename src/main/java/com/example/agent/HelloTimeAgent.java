package com.example.agent;

import com.google.adk.agents.BaseAgent;
import com.google.adk.agents.LlmAgent;
import com.google.adk.tools.Annotations.Schema;
import com.google.adk.tools.FunctionTool;

import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Map;

public class HelloTimeAgent {

    public static BaseAgent ROOT_AGENT = initAgent();

    private static final Map<String, String> CITY_TO_ZONE = Map.ofEntries(
        Map.entry("new york", "America/New_York"),
        Map.entry("los angeles", "America/Los_Angeles"),
        Map.entry("chicago", "America/Chicago"),
        Map.entry("london", "Europe/London"),
        Map.entry("paris", "Europe/Paris"),
        Map.entry("berlin", "Europe/Berlin"),
        Map.entry("tokyo", "Asia/Tokyo"),
        Map.entry("mumbai", "Asia/Kolkata"),
        Map.entry("navi mumbai", "Asia/Kolkata"),
        Map.entry("delhi", "Asia/Kolkata"),
        Map.entry("kolkata", "Asia/Kolkata"),
        Map.entry("bangalore", "Asia/Kolkata"),
        Map.entry("chennai", "Asia/Kolkata"),
        Map.entry("hyderabad", "Asia/Kolkata"),
        Map.entry("pune", "Asia/Kolkata"),
        Map.entry("dubai", "Asia/Dubai"),
        Map.entry("singapore", "Asia/Singapore"),
        Map.entry("shanghai", "Asia/Shanghai"),
        Map.entry("seoul", "Asia/Seoul"),
        Map.entry("sydney", "Australia/Sydney"),
        Map.entry("toronto", "America/Toronto"),
        Map.entry("moscow", "Europe/Moscow"),
        Map.entry("cairo", "Africa/Cairo"),
        Map.entry("istanbul", "Europe/Istanbul"),
        Map.entry("hong kong", "Asia/Hong_Kong"),
        Map.entry("bangkok", "Asia/Bangkok"),
        Map.entry("san francisco", "America/Los_Angeles")
    );

    private static final DateTimeFormatter TIME_FORMAT =
        DateTimeFormatter.ofPattern("hh:mm a");

    private static BaseAgent initAgent() {
        return LlmAgent.builder()
            .name("hello-time-agent")
            .description("Tells the current time in a specified city")
            .instruction("""
                You are a helpful assistant that tells the current time in a city.
                Use the 'getCurrentTime' tool for this purpose.
                """)
            .model("Gemini 2.5 Pro")
            .tools(FunctionTool.create(HelloTimeAgent.class, "getCurrentTime"))
            .build();
    }

    @Schema(description = "Get the current time for a given city")
    public static Map<String, String> getCurrentTime(
        @Schema(name = "city", description = "Name of the city to get the time for") String city) {

        String zone = CITY_TO_ZONE.get(city.toLowerCase().trim());

        if (zone == null) {
            return Map.of(
                "city", city,
                "error", "Unknown city. Known cities: " + CITY_TO_ZONE.keySet()
            );
        }

        LocalTime currentTime = LocalTime.now(ZoneId.of(zone));
        return Map.of(
            "city", city,
            "time", currentTime.format(TIME_FORMAT)
        );
    }
}