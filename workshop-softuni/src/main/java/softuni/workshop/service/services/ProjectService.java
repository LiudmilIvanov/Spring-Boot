package softuni.workshop.service.services;

import java.io.FileNotFoundException;
import java.util.List;

import javax.xml.bind.JAXBException;

import softuni.workshop.service.models.ProjectViewModel;

public interface ProjectService {

    void importProjects() throws FileNotFoundException, JAXBException;

    boolean areImported();

    String readProjectsXmlFile();

    String exportFinishedProjects();
    
    List<ProjectViewModel> findAllFinishedProjects();
}
