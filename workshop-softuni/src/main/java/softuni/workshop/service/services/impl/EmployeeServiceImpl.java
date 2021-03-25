package softuni.workshop.service.services.impl;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.xml.bind.JAXBException;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import softuni.workshop.data.dtos.EmployeeDto;
import softuni.workshop.data.dtos.EmployeeRootDto;
import softuni.workshop.data.entities.Employee;
import softuni.workshop.data.repositories.EmployeeRepository;
import softuni.workshop.data.repositories.ProjectRepository;
import softuni.workshop.service.models.EmployeeViewModel;
import softuni.workshop.service.services.EmployeeService;
import softuni.workshop.util.XmlParser;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	private final EmployeeRepository employeeRepository;
	private final String XML_PATH = "src/main/resources/files/xmls/employees.xml"; 
	private final XmlParser xmlParser;
	private final ModelMapper modelMapper;
	private final ProjectRepository projectRepository;
	
	@Autowired
	public EmployeeServiceImpl(EmployeeRepository employeeRepository, XmlParser xmlParser, ModelMapper modelMapper,
			ProjectRepository projectRepository) {
		super();
		this.employeeRepository = employeeRepository;
		this.xmlParser = xmlParser;
		this.modelMapper = modelMapper;
		this.projectRepository = projectRepository;
	}
	
	
	@Override
    public void importEmployees() throws FileNotFoundException, JAXBException {
		EmployeeRootDto employeeRootDto = this.xmlParser.importFromXml(EmployeeRootDto.class, XML_PATH);
	
		for (EmployeeDto employeeDto : employeeRootDto.getEmployeeDtos()) {
			
			Employee employee = this.modelMapper.map(employeeDto, Employee.class);
			employee.setProject(this.projectRepository.findByName(employeeDto.getProjectDto().getName()));
			
			this.employeeRepository.saveAndFlush(employee);
			
		}
		
	}


	@Override
    public boolean areImported() {
       return this.employeeRepository.count() > 0;
    }

    @Override
    public String readEmployeesXmlFile() {
    	String xml = "";
    	try {
    		xml = String.join("\n", Files.readString(Path.of(XML_PATH)));
			
		} catch (IOException e) {
			e.printStackTrace();
		}
    	
    	return xml;
    }

    @Override
    public String exportEmployeesWithAgeAbove() {
        return findAllByAge().stream()
        		.map(e -> e.toString())
        		.collect(Collectors.joining("\n"));
    }


	@Override
	public List<EmployeeViewModel> findAllByAge() {
		return this.employeeRepository
				.findAllByAgeGreaterThan(25)
				.stream()
				.map(e -> this.modelMapper.map(e, EmployeeViewModel.class))
				.collect(Collectors.toList());
		
	}
}
