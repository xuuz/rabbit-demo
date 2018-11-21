package com.jikeh.controller;

import com.jikeh.service.CacheServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class CacheController {

	@Autowired
	private CacheServiceImpl cacheService;

	/**
	 * http://localhost:8088/getAd?adId=1
	 * @param adId
	 * @return
	 */
	@RequestMapping("/getAd")
	@ResponseBody
	public String getUserName(Long adId) {
		return cacheService.getAd(adId);
	}
	
}
