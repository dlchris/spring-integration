package com.pinnet.batch.reader;

import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.MultiResourceItemReader;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.core.io.Resource;

//公共读取方式，默认提供file以及多个file的读取
public class CommonReader {

    //多个文件的时候调用的处理方式
    public static <T> FlatFileItemReader<T> setLineMapper(LineMapper<T>  lineMapper) {
        FlatFileItemReader<T> flatFileItemReader = new FlatFileItemReader<>();
        flatFileItemReader.setLineMapper(lineMapper);
        return flatFileItemReader;
    }
    //单文件处理
    public static <T> FlatFileItemReader<T> setLineMapper(LineMapper<T>  lineMapper, Resource resource) {
        FlatFileItemReader<T> flatFileItemReader = new FlatFileItemReader<>();
        flatFileItemReader.setResource(resource);
        flatFileItemReader.setLineMapper(lineMapper);
        return flatFileItemReader;
    }
    //默认的方式处理，自己设定解析字段，用于个file
    public static <T> FlatFileItemReader<T> setLineMapper(String[] colunms, FieldSetMapper<T> fieldSetMapper) {
        FlatFileItemReader<T> flatFileItemReader = new FlatFileItemReader<>();
        flatFileItemReader.setLineMapper(setDefaultLineMapper(colunms, fieldSetMapper));
        return flatFileItemReader;
    }
    //用于单文件的读取
    public static <T> FlatFileItemReader<T> setLineMapper(Resource resource, String[] colunms, FieldSetMapper<T> fieldSetMapper) {
        FlatFileItemReader<T> flatFileItemReader = new FlatFileItemReader<>();
        flatFileItemReader.setResource(resource);
        flatFileItemReader.setLineMapper(setDefaultLineMapper(colunms, fieldSetMapper));
        return flatFileItemReader;
    }
    //多个文件的处理方式
    public static <T> MultiResourceItemReader<T> setLineMapper(Resource[] resources, FlatFileItemReader<T> flatFileItemReader) {
        MultiResourceItemReader<T> resourcesItemReader = new MultiResourceItemReader();
        resourcesItemReader.setResources(resources);
        resourcesItemReader.setDelegate(flatFileItemReader);
        return resourcesItemReader;
    }
    //默认的字段读取方式
    private static <T> DefaultLineMapper<T> setDefaultLineMapper(String[] colunms, FieldSetMapper<T> fieldSetMapper) {
        DefaultLineMapper<T> defaultLineMapper = new DefaultLineMapper<>();
        DelimitedLineTokenizer lineTokenizer = new DelimitedLineTokenizer();
        lineTokenizer.setNames(colunms);
        defaultLineMapper.setLineTokenizer(lineTokenizer);
        defaultLineMapper.setFieldSetMapper(fieldSetMapper);
        return defaultLineMapper;
    }
}
