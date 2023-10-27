package com.poly.controller.adminControllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.poly.entity.Account;
import com.poly.entity.Banner;
import com.poly.service.BannerService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import jakarta.validation.Valid;

@Controller
public class BannerAdminController {
	@Autowired
	BannerService bannerService;
	
	@PostMapping("creates")
	private String update(@Valid @ModelAttribute("banners") Optional<Banner> banner, Model model) {
		Banner save = bannerService.save(banner.get());
		model.addAttribute("banners", new Banner());
		model.addAttribute("List",bannerService.findAll());
		return "redirect:/admin/banners";
	}
	
	@GetMapping("edit/{BannerID}")
	private String edit(@PathVariable("BannerID") Integer BannerID, Model model, RedirectAttributes redirectAttributes) {
		Banner pc = bannerService.findByBannerID(BannerID).get();
		model.addAttribute("banners", pc);
		model.addAttribute("List",bannerService.findAll());
		return "admin/banner";
	}
	
	@GetMapping("reset")
	private String reset() {
		return "redirect:/admin/banners";
	}
	
	@GetMapping("delete/{BannerID}")
	private String delete(@PathVariable("BannerID") Integer BannerID) {
		bannerService.deleteByBannerID(BannerID);
		return "redirect:/admin/banners";
	}
	
//	@GetMapping("delete/{id}")
//	private String delete(@PathVariable("id") String id) {
//		studentServiceImpl.deleteById(id);
//		return "redirect:/index";
//	}
}
