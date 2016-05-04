package ru.analteam.gtracks.auxiliary;

import org.springframework.core.convert.converter.Converter;

import java.util.List;

/**
 * Created by dima-pc on 10.02.2016.
 */
public interface ListConverter<S, T> extends Converter<S, T> {
    List<T> convert(List<S> sourceList);
}
