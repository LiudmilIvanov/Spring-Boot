package softuni.workshop.web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import softuni.workshop.service.services.CompanyService;
import softuni.workshop.service.services.EmployeeService;
import softuni.workshop.service.services.ProjectService;

@Controller
public class HomeController extends BaseController {
		
	private final EmployeeService employeeService;
	private final ProjectService projectService;
	private final CompanyService companyService;
	
	
	@Autowired
	public HomeController(EmployeeService employeeService, ProjectService projectService,
			CompanyService companyService) {
		this.employeeService = employeeService;
		this.projectService = projectService;
		this.companyService = companyService;
	}

	@GetMapping("/")
	public ModelAndView index() {
		return this.view("index");
	}
	
	@GetMapping("/home")
	public ModelAndView home() {
		ModelAndView modeAndView = new ModelAndView("home");
		boolean areImported = this.companyService.areImported() && 
				this.projectService.areImported() && this.employeeService.areImported();
		modeAndView.addObject("areImported", areImported);
		
		return modeAndView;
	}
	
}
