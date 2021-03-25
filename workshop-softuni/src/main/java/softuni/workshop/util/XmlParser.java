package softuni.workshop.util;

import java.io.FileNotFoundException;

import javax.xml.bind.JAXBException;

import org.springframework.context.annotation.Bean;

public interface XmlParser {


	@Bean
	<O> void exportToXMl(O object, String path) throws JAXBException;

	@Bean
	<O> O importFromXml(Class<O> klass, String path) throws JAXBException, FileNotFoundException;
	
	
	
}
