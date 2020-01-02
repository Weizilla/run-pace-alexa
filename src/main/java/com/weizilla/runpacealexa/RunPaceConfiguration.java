package com.weizilla.runpacealexa;

import com.amazon.ask.Skill;
import com.amazon.ask.Skills;
import com.amazon.ask.servlet.SkillServlet;
import com.weizilla.runpacealexa.alexa.OtherRequestHandler;
import com.weizilla.runpacealexa.alexa.RunPaceIntentHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RunPaceConfiguration {
    private static final Logger logger = LoggerFactory.getLogger(RunPaceConfiguration.class);
    private static final String ENDPOINT = "/alexa";

    @Bean
    public ServletRegistrationBean<SkillServlet> register(Skill skill) {
        logger.info("Skill endpoint: {}", ENDPOINT);
        SkillServlet skillServlet = new SkillServlet(skill);
        return new ServletRegistrationBean<>(skillServlet, ENDPOINT);
    }

    @Bean
    public Skill skill(@Value("${skill.id}") String skillId) {
        logger.info("Skill id: {}", skillId);
        return Skills.standard()
            .withSkillId(skillId)
            .addRequestHandlers(
                new RunPaceIntentHandler(),
                new OtherRequestHandler())
            .build();
    }
}
