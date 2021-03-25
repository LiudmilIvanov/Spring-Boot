package softuni.workshop.service.services;

import java.io.FileNotFoundException;
import java.io.IOException;

import javax.xml.bind.JAXBException;

public interface CompanyService {

    void importCompanies() throws JAXBException, FileNotFoundException;

    boolean areImported();

    String readCompaniesXmlFile();
}
