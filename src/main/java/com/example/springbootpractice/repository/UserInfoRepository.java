package com.example.springbootpractice.repository;

import com.example.springbootpractice.entity.UserInfo;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Repository
public class UserInfoRepository {

    private Map<String, UserInfo> userInfos = new HashMap<>();

    {
        userInfos.put("Yury", new UserInfo(1, "Yury", "yury@gmail.com", "$2a$12$SV0XMtTM1ZRxRF5voPUZ4OvqHu0AGZyR5XYwo9Onbxw.v3MtlpqXW"));
    }

    public Optional<UserInfo> findByName(String username) {
        return Optional.ofNullable(userInfos.get(username));
    }

    public void save(UserInfo userInfo) {
        userInfos.put(userInfo.getName(), userInfo);
    }


}
