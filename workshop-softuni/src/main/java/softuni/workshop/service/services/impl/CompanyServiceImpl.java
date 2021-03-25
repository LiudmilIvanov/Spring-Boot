package softuni.workshop.service.services.impl;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import javax.xml.bind.JAXBException;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import softuni.workshop.data.dtos.CompaniesRootDto;
import softuni.workshop.data.dtos.CompanyDto;
import softuni.workshop.data.entities.Company;
import softuni.workshop.data.repositories.CompanyRepository;
import softuni.workshop.service.services.CompanyService;
import softuni.workshop.util.XmlParser;

@Service
public class CompanyServiceImpl implements CompanyService {

	private final CompanyRepository companyRepository;
	private final String XML_PATH = "src/main/resources/files/xmls/companies.xml"; 
	private final XmlParser xmlParser;
	private final ModelMapper modelMapper;
	
	@Autowired
    public CompanyServiceImpl(CompanyRepository companyRepository, XmlParser xmlParser,ModelMapper modelMapper) {
		this.companyRepository = companyRepository;
		this.xmlParser = xmlParser;
		this.modelMapper = modelMapper;
	}

	@Override
    public void importCompanies() throws JAXBException, FileNotFoundException {
	CompaniesRootDto companiesRootDto =  this.xmlParser.importFromXml(CompaniesRootDto.class, XML_PATH);
	
		for (CompanyDto companyDto : companiesRootDto.getCompanies()) {
			this.companyRepository.save(this.modelMapper.map(companyDto, Company.class));
		}
	
	}

    @Override
    public boolean areImported() {
    	return this.companyRepository.count() > 0;
    }

    @Override
    public String readCompaniesXmlFile()  {
    	String xml = "";
    	try {
    		xml = String.join("\n", Files.readString(Path.of(XML_PATH)));
			
		} catch (IOException e) {
			e.printStackTrace();
		}
    	
    	return xml;
    }
}
