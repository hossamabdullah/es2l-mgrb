/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hossam.common.serializer;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.JsonSerializer;
import org.codehaus.jackson.map.SerializerProvider;

/**
 *
 *	use this class to view date in an appropriate 
 *	format through putting
 *  "@JsonSerialize(using = CustomDateSerializer.class)"
 *  above the getter of the Date attribute
 *
 * @author Hossam ElDeen
 */
public class CustomDateSerializer extends JsonSerializer<Date> {

    @Override
    public void serialize(Date value, JsonGenerator gen, SerializerProvider arg2) throws IOException, JsonProcessingException {
        String formattedDate = value.getTime()+"";
        gen.writeString(formattedDate);
    }

}
