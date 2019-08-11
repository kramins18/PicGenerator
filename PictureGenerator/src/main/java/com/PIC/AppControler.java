package com.PIC;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class AppControler {
	@RequestMapping("/test")
	@ResponseBody
	public String createImage(@RequestParam String top, @RequestParam String bot, @RequestParam String url) {
		Render.downloadImage(top, bot, url);
		return Render.downloadImage(top, bot, url);
	}
}