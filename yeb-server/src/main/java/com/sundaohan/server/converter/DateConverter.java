package com.sundaohan.server.converter;



import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * @Auther sundaohan
 * @Package com.sundaohan.server.converter
 * @Title DateConverter
 * @Description TODO
 * @Date 2021/8/2 下午5:10
 */
@Component
public class DateConverter implements Converter<String, LocalDate> {
    @Override
    public LocalDate convert(String source) {
        try{
            return LocalDate.parse(source, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        }catch(Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
