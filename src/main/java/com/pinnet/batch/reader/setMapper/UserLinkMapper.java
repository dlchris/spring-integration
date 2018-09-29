package com.pinnet.batch.reader.setMapper;

import com.pinnet.domain.User;
import org.springframework.batch.item.file.LineMapper;

//自定义解析过程，分隔符可以自己定义
public class UserLinkMapper implements LineMapper<User> {

    public User mapLine(String s, int i) throws Exception {
        String[] strs = s.split(",");
        User user = new User();
        user.setId(Long.valueOf(strs[0]));
        user.setName(String.valueOf(strs[1]));
        user.setAge(Integer.valueOf(strs[2]));
        return user;
    }
}
