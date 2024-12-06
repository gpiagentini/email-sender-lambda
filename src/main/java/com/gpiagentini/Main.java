package com.gpiagentini;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.logging.LogLevel;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gpiagentini.services.EmailSenderService;
import com.gpiagentini.types.InputData;
import com.gpiagentini.types.Response;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Main implements RequestHandler<InputData, Map<String, String>> {

    private final String usernameAuthentication = System.getenv("MAIL_USERNAME");
    private final String passwordAuthentication = System.getenv("MAIL_PASSWORD");
    private final String recipient = System.getenv("MAIL_RECIPIENT");
    private final ObjectMapper objectMapper = new ObjectMapper();


    @Override
    public Map<String, String> handleRequest(InputData input, Context context) {
        var logger = context.getLogger();
        if (usernameAuthentication == null) {
            logger.log("Email username for authentication not found.", LogLevel.ERROR);
            return generateResponse("500", "Error occurred while sending e-mail.");
        }
        if (passwordAuthentication == null) {
            logger.log("Email password for authentication not found.", LogLevel.ERROR);
            return generateResponse("500", "Error occurred while sending e-mail.");
        }
        logger.log("New e-mail request from: " + input.name() + "( " + input.email() + " )", LogLevel.INFO);
        var emailSenderService = new EmailSenderService(usernameAuthentication, passwordAuthentication);
        try {
            emailSenderService.sendEmail(input.subject(), input.toString(), Objects.requireNonNull(recipient, "gmtpiagentini@gmail.com"));
            logger.log("Email sent successfully!", LogLevel.INFO);
            return generateResponse("200", "Email sent successfully!");
        } catch (Exception ex) {
            logger.log(ex.getMessage(), LogLevel.ERROR);
            System.err.println(ex);
            return generateResponse("500", "Error occurred while sending e-mail.");
        }
    }

    private Map<String, String> generateResponse(String statucCode, String message) {
        var response = new HashMap<String, String>();
        response.put("statusCode", statucCode);
        response.put("body", message);
        return response;
    }
}