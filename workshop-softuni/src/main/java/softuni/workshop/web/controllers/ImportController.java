package softuni.workshop.web.controllers;

import java.io.FileNotFoundException;

import javax.xml.bind.JAXBException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import softuni.workshop.service.services.CompanyService;
import softuni.workshop.service.services.EmployeeService;
import softuni.workshop.service.services.ProjectService;

@Controller
@RequestMapping("/import")
public class ImportController extends BaseController {

	private final ProjectService projectService;
	private final EmployeeService employeeService;
	private final CompanyService companyService;
	
	
	@Autowired
	public ImportController(ProjectService projectService, EmployeeService employeeService,
			CompanyService companyService) {
		this.projectService = projectService;
		this.employeeService = employeeService;
		this.companyService = companyService;
	}

	@GetMapping("/xml")
	public ModelAndView xmls() {
		ModelAndView modelAndVeiw = new ModelAndView("xml/import-xml");
		
		boolean[] areImported = new boolean[] {
				this.companyService.areImported(), this.projectService.areImported(), 
				this.projectService.areImported()
		};
		
		modelAndVeiw.addObject("areImported", areImported);
		
		return modelAndVeiw;
	}
	
	@GetMapping("/companies")
	public ModelAndView companies() {
		ModelAndView modelAndView = new ModelAndView("xml/import-companies");
		
		String xmlContent = this.companyService.readCompaniesXmlFile();
		System.out.println();
		modelAndView.addObject("companies", xmlContent);
		return modelAndView;
	}
	
	@PostMapping("/companies")
	public ModelAndView companiesConfirm() throws FileNotFoundException, JAXBException {
		this.companyService.importCompanies();
		
		return this.redirect("/import/xml");
	}
	
	@PostMapping("/projects")
	public ModelAndView projectsConfirm() throws FileNotFoundException, JAXBException {
		this.projectService.importProjects();
		
		return this.redirect("/import/xml");
	}
	
	@GetMapping("/projects")
	public ModelAndView projects() {
		ModelAndView modelAndView = new ModelAndView("xml/import-projects");
		String xmlContent = this.projectService.readProjectsXmlFile();
		modelAndView.addObject("projects", xmlContent);
		
		return modelAndView;
	}
	
	@PostMapping("/employees")
	public ModelAndView employeesConfirm() throws FileNotFoundException, JAXBException {
		this.employeeService.importEmployees();
		
		return this.redirect("/import/xml");
	}
	
	@GetMapping("/employees")
	public ModelAndView employees() {
		ModelAndView modelAndView = new ModelAndView("xml/import-employees");
		String xmlContent = this.employeeService.readEmployeesXmlFile();
		modelAndView.addObject("employees", xmlContent);
		
		return modelAndView;
	}
}
