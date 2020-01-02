package com.weizilla.runpacealexa.alexa;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.impl.IntentRequestHandler;
import com.amazon.ask.model.IntentRequest;
import com.amazon.ask.model.Response;
import com.amazon.ask.model.Slot;
import com.google.common.base.Strings;
import com.weizilla.distance.Distance;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.util.Map;
import java.util.Optional;

import static com.amazon.ask.request.Predicates.intentName;

public class RunPaceIntentHandler implements IntentRequestHandler {
    private static final Logger logger = LoggerFactory.getLogger(RunPaceIntentHandler.class);

    @Override
    public boolean canHandle(HandlerInput handlerInput, IntentRequest intentRequest) {
        return handlerInput.matches(intentName("RunPaceIntent"));
    }

    @Override
    public Optional<Response> handle(HandlerInput handlerInput, IntentRequest intentRequest) {
        Map<String, Slot> slots = intentRequest.getIntent().getSlots();
        String durationStr = slots.get("duration").getValue();
        String distanceUnit = slots.get("distanceUnit").getValue();
        String distanceValue = slots.get("distanceValue").getValue();
        logger.info("Handling request: duration={} distanceValue={} distanceUnit={}",
            durationStr, distanceValue, distanceUnit);

        if (Strings.isNullOrEmpty(durationStr) || Strings.isNullOrEmpty(distanceUnit) || Strings.isNullOrEmpty(distanceValue)) {
            return OtherRequestHandler.makeGenericResponse(handlerInput);
        }

        Duration duration = Duration.parse(durationStr);
        Distance distance = Distance.parse(distanceValue + " " + distanceUnit);

        double secondsPerMile = duration.getSeconds() / distance.toMiles();
        int min = (int) (secondsPerMile / 60.0);
        int sec = (int) (secondsPerMile % 60);

        return handlerInput.getResponseBuilder()
            .withSpeech("You run at " + min + " minutes " + sec + " seconds per mile")
            .build();
    }
}
