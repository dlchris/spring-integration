package com.pinnet.batch.writer;

import com.pinnet.domain.User;
import com.pinnet.util.SpringUtils;
import org.springframework.batch.item.ItemWriter;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

//数据写出
public class UserItemWriter implements ItemWriter<User>{

    private JdbcTemplate jdbcTemplate = SpringUtils.getBean("jdbcTemplate", JdbcTemplate.class);

    public void write(List<? extends User> list) throws Exception {
        String sql = "insert into user (id, name, age) values (?,?,?)";
        if (list != null && !list.isEmpty()) {
            list.stream().filter(user -> user.getId() != null).forEach(user -> {
                jdbcTemplate.update(sql, new Object[]{user.getId(), user.getName(), user.getAge()});
            });
            System.out.println(System.currentTimeMillis());
            System.out.println(list);
        }
    }
}
