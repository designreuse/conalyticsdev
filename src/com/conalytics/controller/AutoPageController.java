package com.conalytics.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.conalytics.domain.Auto;
import com.conalytics.domain.Company;
import com.conalytics.services.AutoService;
import com.conalytics.services.CompanyService;

@Controller
@SessionAttributes("currentMenuItem")
public class AutoPageController {

	@Autowired
	AutoService autoService;

	@Autowired
	CompanyService companyService;
	
	@RequestMapping("home")
	public ModelAndView home(@ModelAttribute Auto auto) {
		return new ModelAndView("mainMenu");
	}
	
	@RequestMapping("homePage")
	public ModelAndView homePage(@ModelAttribute Auto auto) {
		return new ModelAndView("homePage");
	}
	
	@RequestMapping("registerAuto")
	public ModelAndView registerAuto(@ModelAttribute Auto auto) {
		Map<Double, String> companyMap = new HashMap<Double, String>();
		List<Company> companyList = companyService.getCompanyList();
		for(Company company : companyList) {
			companyMap.put(company.getCompanyId(), company.getCompanyName());
		}
		ModelAndView modelAndView = new ModelAndView("registerAuto", "companyMap", companyMap);
		return modelAndView;
	}

	@RequestMapping("insertAuto")
	public String inserData(@ModelAttribute Auto auto) {
		if (auto != null)
			autoService.insertData(auto);
		return "redirect:/getAutoList";
	}

	@RequestMapping("getAutoList")
	public ModelAndView getAutoLIst() {
		List<Auto> autoList = autoService.getAutoList();
		ModelAndView modelAndView = new ModelAndView("autoList", "autoList", autoList);
		modelAndView.addObject("currentMenuItem", "Vehicle List");
		return modelAndView;
	}

	@RequestMapping("editAuto")
	public ModelAndView editAuto(@RequestParam Double id,
			@ModelAttribute Auto auto) {

		auto = autoService.getAuto(id);
		Map<String, Object> map = new HashMap<String, Object>();
			map.put("auto", auto);
		return new ModelAndView("editAuto", "map", map);

	}

	@RequestMapping("updateAuto")
	public String updateAuto(@ModelAttribute Auto auto) {
		autoService.updateData(auto);
		return "redirect:/getAutoList";

	}

	@RequestMapping("deleteAuto")
	public String deleteAuto(@RequestParam Double id) {
		System.out.println("id = " + id);
		autoService.deleteData(id);
		return "redirect:/getAutoList";
	}
}