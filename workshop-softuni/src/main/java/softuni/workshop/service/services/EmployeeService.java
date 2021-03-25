package softuni.workshop.service.services;

import java.io.FileNotFoundException;
import java.util.List;

import javax.xml.bind.JAXBException;

import softuni.workshop.service.models.EmployeeViewModel;

public interface EmployeeService {

    void importEmployees() throws FileNotFoundException, JAXBException;

    boolean areImported();

    String readEmployeesXmlFile();

    String exportEmployeesWithAgeAbove();
    
    List<EmployeeViewModel> findAllByAge();
}
