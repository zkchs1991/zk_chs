package com.github.utils;

import com.google.gson.Gson;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Created by zk_chs on 16/4/11.
 */
@Slf4j
public class GsonUtil {

    public static final Gson GSON = new Gson();

    public static void main(String[] args) {
        Person person = Person.builder().first_name("张").last_name("开").age(24).sex("男").build();
        String json = GsonUtil.GSON.toJson(person);
        /** {"first_name":"张","last_name":"开","age":24} */
        log.debug(json);
        person = GsonUtil.GSON.fromJson(json, Person.class);
        /** Person(first_name=张, last_name=开, age=24, sex=null) */
        log.debug(person.toString());
    }

}

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
class Person {

    private String first_name;
    private String last_name;
    private int age;
    private transient String sex;

}
