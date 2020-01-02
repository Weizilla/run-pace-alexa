package com.weizilla.runpacealexa.alexa;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.Response;

import java.util.Optional;

import static com.amazon.ask.request.Predicates.intentName;

public class OtherRequestHandler implements RequestHandler {
    @Override
    public boolean canHandle(HandlerInput handlerInput) {
        return ! handlerInput.matches(intentName("RunPaceIntent"));
    }

    @Override
    public Optional<Response> handle(HandlerInput handlerInput) {
        return makeGenericResponse(handlerInput);
    }

    public static Optional<Response> makeGenericResponse(HandlerInput handlerInput) {
        return handlerInput.getResponseBuilder()
            .withSpeech("Say: ask running pace what my pace is for 10 miles in 60 minutes")
            .build();
    }
}
