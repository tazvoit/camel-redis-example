package org.mycompany.processors;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.component.redis.RedisClient;
import org.mycompany.model.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

public class RedisSaveProcessor implements Processor {
    protected static final Logger LOGGER = LoggerFactory.getLogger(RedisSaveProcessor.class);

    @Autowired
    RedisClient redisClient;

    @Override
    public void process(Exchange exchange) throws Exception {

        if(!redisClient.exists("fechaBaseDeDatos")){
            redisClient.set("fechaBaseDeDatos",new Date());
            LOGGER.info("Fecha de Base de datos Cargada"+ redisClient.get("fechaBaseDeDatos"));
        }

    }

}
