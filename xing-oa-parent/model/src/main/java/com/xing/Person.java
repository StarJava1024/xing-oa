package com.xing;

import com.xing.common.annotation.PrivacyEncrypt;
import com.xing.common.enums.PrivacyTypeEnum;
import lombok.Builder;
import lombok.Data;
import lombok.Singular;

import java.util.List;

/**
 * @Author: Wang Xing
 * @Date: 14:53 2023/9/8
 */
@Data
@Builder
public class Person {
    private String name;
    @Builder.Default
    private String city = "北京";
    @PrivacyEncrypt(type = PrivacyTypeEnum.PHONE)
    private String phone;

    @Singular
    private List<String> interests;
    @Singular("tag")
    private List<String> tagList;
}

class test {
    public static void main(String[] args) {
        Person person = Person.builder()
                .name("张三")
                .phone("18709822197")
                // .phone(DesensitizedUtil.mobilePhone("18709822197"))
                .interest("1").interest("2")
                .tag("sign 1").tag("sign 2")
                .build();
        System.out.println(person);
    }
}
