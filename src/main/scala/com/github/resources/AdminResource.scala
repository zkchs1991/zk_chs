package com.github.resources

import java.util

import com.github.repository.AdminRepository
import com.github.utils.M
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.{RequestParam, RequestMethod, RequestMapping, RestController}

import scala.language.postfixOps

/**
  * Created by zk_chs on 16/4/13.
  */
@RestController
@RequestMapping(value = Array("/admin"))
class AdminResource @Autowired() (adminRepository: AdminRepository) {

  @RequestMapping(method = Array(RequestMethod GET, RequestMethod POST))
  def get(@RequestParam(value = "id", required = false) id: Int): util.Map[String, AnyRef] =  {
    if ( id == 0 ) M builder() put ("status", "fail") put ("msg", "id may not be null") buildMap
    else M builder() put("status", "success") put("result", adminRepository findOne id) buildMap
  }

}
