package com.github.repository;

import com.github.utils.M;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * Created by zk_chs on 16/4/11.
 */
@RestController
@RequestMapping(value = "/session")
public class SessionResource {

    @RequestMapping(value = "/set", method = {RequestMethod.GET, RequestMethod.POST})
    public Map<String, Object> set (HttpServletRequest request){
        request.getSession().setAttribute("request Url", request.getRequestURL());
        return M.builder()
                .put("result", "success")
                .put("request Url", request.getRequestURL())
                .buildMap();
    }

    @RequestMapping(value = "/get", method = {RequestMethod.GET, RequestMethod.POST})
    public Map<String, Object> get (HttpServletRequest request){
        return M.builder()
                .put("sessionId", request.getSession().getId())
                .put("message", request.getSession().getAttribute("request Url"))
                .buildMap();
    }

}
