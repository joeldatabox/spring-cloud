package br.com.springmongoconfig.service.converters;

/**
 * Created by master on 16/06/17.
 */

import org.bson.types.Decimal128;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class Decimal128ToBigDecimalConverter implements Converter<Decimal128, BigDecimal> {

    @Override
    public BigDecimal convert(final Decimal128 source) {
        return source == null ? null : source.bigDecimalValue();
    }
}