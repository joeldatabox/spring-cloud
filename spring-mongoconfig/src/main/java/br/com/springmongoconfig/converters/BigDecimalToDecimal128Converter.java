package br.com.springmongoconfig.converters;


import org.bson.types.Decimal128;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

/**
 * Created by master on 16/06/17.
 */
@Component
public class BigDecimalToDecimal128Converter implements Converter<BigDecimal, Decimal128> {

    @Override
    public Decimal128 convert(final BigDecimal source) {
        return source == null ? null : Decimal128.parse(source.toPlainString());
    }
}