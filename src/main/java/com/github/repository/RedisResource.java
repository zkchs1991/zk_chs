package com.github.repository;

import com.github.utils.M;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.RedisClusterConnection;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * Created by zk_chs on 16/4/11.
 */
@RestController
@RequestMapping(value = "/redis")
public class RedisResource {

    private RedisClusterConnection connection;
    /**
     * StringRedisTemplate只能操作String对象
     * 如果需要对对象进行操作的话,需要自行扩展RedisTemplate<K, V>
     * */
    @SuppressWarnings("SpringJavaAutowiringInspection")
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @RequestMapping(value = "/add", method = {RequestMethod.GET, RequestMethod.POST})
    public Map<String, Object> add (@RequestParam(value = "key") String key,
                       @RequestParam(value = "value") String value,
                       HttpServletRequest request){
//        connection.set(key.getBytes(), value.getBytes());
        stringRedisTemplate.opsForValue().set(key, value);
        return M.builder()
                .put("result", "success")
                .put(key, stringRedisTemplate.opsForValue().get(key))
                .buildMap();
    }

}
