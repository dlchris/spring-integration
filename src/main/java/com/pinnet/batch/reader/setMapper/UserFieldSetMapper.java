package com.pinnet.batch.reader.setMapper;

import com.pinnet.domain.User;
import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.validation.BindException;

//默认的读取方式，通过设定colunms实现
public class UserFieldSetMapper implements FieldSetMapper<User> {

    public User mapFieldSet(FieldSet fieldSet) throws BindException {
        User user = null;
        try {
            user = new User();
            user.setId(fieldSet.readLong("id"));
            user.setName(fieldSet.readString("name"));
            user.setAge(fieldSet.readInt("age"));
        } catch (Exception e) {
            return new User();
        }
        return user;
    }
}
