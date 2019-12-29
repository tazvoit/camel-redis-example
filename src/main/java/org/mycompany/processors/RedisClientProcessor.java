package org.mycompany.processors;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.component.redis.RedisClient;
import org.mycompany.model.ErrorResponse;
import org.mycompany.model.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.concurrent.TimeUnit;

public class RedisClientProcessor implements Processor {
    protected static final Logger LOGGER = LoggerFactory.getLogger(RedisClientProcessor.class);

    @Autowired
    RedisClient redisClient;

    @Autowired
    private ObjectMapper mapper = new ObjectMapper();
    @Override
    public void process(Exchange exchange) throws Exception {

        Response response = new Response();
        if(redisClient.exists("fechaBaseDeDatos")){
            response.setFecha(redisClient.get("fechaBaseDeDatos").toString());
            response.setObservacion("Fecha en base de datos");
            exchange.getIn().setBody(generateMessage(response));
        }else{
            throw new Exception("No se encontro la fecha");
        }
    }

    private String generateMessage(Response response) throws JsonProcessingException {
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(response);
    }
}
