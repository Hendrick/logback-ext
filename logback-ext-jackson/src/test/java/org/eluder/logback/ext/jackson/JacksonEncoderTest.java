package org.eluder.logback.ext.jackson;

import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.classic.spi.LoggingEvent;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class JacksonEncoderTest {

    private final LoggerContext context = new LoggerContext();
    private final Logger logger = context.getLogger(JacksonEncoderTest.class);
    
    private JacksonEncoder encoder;
    
    @Before
    public void start() {
        encoder = new JacksonEncoder();
        encoder.start();
    }
    
    @After
    public void stop() {
        encoder.stop();
        encoder = null;
    }
    
    @Test
    public void encodeLoggingEvent() throws Exception{
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        encoder.init(stream);
        encoder.doEncode(createLoggingEvent("Hellö JSON!"));
        String json = new String(stream.toByteArray(), "UTF-8");
        assertNotNull(json);
    }
    
    private ILoggingEvent createLoggingEvent(String message) {
        return new LoggingEvent("", logger, Level.DEBUG, message, null, null);
    }
}
