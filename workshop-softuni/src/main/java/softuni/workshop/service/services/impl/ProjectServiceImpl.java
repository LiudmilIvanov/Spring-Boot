package softuni.workshop.service.services.impl;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;

import javax.xml.bind.JAXBException;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import softuni.workshop.data.dtos.ProjectDto;
import softuni.workshop.data.dtos.ProjectRootDto;
import softuni.workshop.data.entities.Project;
import softuni.workshop.data.repositories.CompanyRepository;
import softuni.workshop.data.repositories.ProjectRepository;
import softuni.workshop.service.models.ProjectViewModel;
import softuni.workshop.service.services.ProjectService;
import softuni.workshop.util.XmlParser;


@Service
public class ProjectServiceImpl implements ProjectService {
	
	private final String XML_PATH = "src/main/resources/files/xmls/projects.xml"; 
	private final XmlParser xmlParser;
	private final ProjectRepository projectRepository;
	private final ModelMapper modelMapper;
	private final CompanyRepository companyRepository;

	
	@Autowired
	public ProjectServiceImpl(XmlParser xmlParser, ProjectRepository projectRepository, ModelMapper modelMapper, CompanyRepository companyRepository) {
		this.xmlParser = xmlParser;
		this.projectRepository = projectRepository;
		this.modelMapper = modelMapper;
		this.companyRepository = companyRepository;
	}

	@Override
    public void importProjects() throws FileNotFoundException, JAXBException{
		ProjectRootDto projectRootDto = this.xmlParser.importFromXml(ProjectRootDto.class, XML_PATH);
	
			for (ProjectDto projectDto : projectRootDto.getProjectDtos()) {
				
				Project project = this.modelMapper.map(projectDto, Project.class);
				project.setCompany(this.companyRepository
						.findByName(projectDto.getCompanyDto().getName()));
				
				this.projectRepository.saveAndFlush(project);
				
			}
		
		
	}

    @Override
    public boolean areImported() {
       return this.projectRepository.count() > 0;
    }

    @Override
    public String readProjectsXmlFile() {
    	String xml = "";
    	try {
    		xml = String.join("\n", Files.readString(Path.of(XML_PATH)));
			
		} catch (IOException e) {
			e.printStackTrace();
		}
    	
    	return xml;
    }

    @Override
    public String exportFinishedProjects(){
    	StringBuilder sb = new StringBuilder();
    	
    	List<ProjectViewModel> projects = findAllFinishedProjects();
    	
    	for (ProjectViewModel project : projects) {
			sb.append(String.format("Project name: %s", project.getName()))
			.append(System.lineSeparator())
			.append(String.format("    Description: " + project.getDescription()))
			.append(System.lineSeparator())
			.append(String.format("    Salary name: %s", project.getPayment()))
			.append(System.lineSeparator());
		}
    	
    	return sb.toString();
    }

	@Override
	public List<ProjectViewModel> findAllFinishedProjects() {
		
		return this.projectRepository.findAllByFinishedTrue()
				.stream().map(p -> this.modelMapper.map(p, ProjectViewModel.class))
				.collect(Collectors.toList());
	}
}
