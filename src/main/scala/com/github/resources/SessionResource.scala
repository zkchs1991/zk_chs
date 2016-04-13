package com.github.resources

import java.util
import javax.servlet.http.HttpServletRequest

import com.github.utils.M
import org.springframework.web.bind.annotation.{RequestMethod, RequestMapping, RestController}

import scala.language.postfixOps

/**
  * Created by zk_chs on 16/4/13.
  */
@RestController
@RequestMapping(value = Array("/session"))
class SessionResource {

  @RequestMapping(value = Array("/set"), method = Array(RequestMethod GET, RequestMethod POST))
  def set(request: HttpServletRequest) = {
    request getSession() setAttribute ("session_msg", request getRequestURL)
    M builder() put("result", "success") put ("session_id", request getSession() getId) put ("session_msg", request getRequestURL) buildMap
  }

  @RequestMapping(value = Array("/get"), method = Array(RequestMethod GET, RequestMethod POST))
  def get(request: HttpServletRequest) =  {
    M builder() put ("result", "success") put ("session_id", request getSession() getId) put ("session_msg", request getSession() getAttribute "session_msg") buildMap
  }

}

class Animal
class Bird extends Animal {
  val animalList = List(new Animal, new Animal)
  new Bird :: animalList
  :: (new Bird, animalList)
}
