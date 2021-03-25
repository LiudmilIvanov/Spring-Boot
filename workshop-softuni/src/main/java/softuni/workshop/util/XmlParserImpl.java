package softuni.workshop.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

public class XmlParserImpl implements XmlParser {

	@Override
	public <O> void exportToXMl(O object, String path) throws JAXBException {
		JAXBContext context = JAXBContext.newInstance(object.getClass());
		Marshaller marshaller = context.createMarshaller();
		
		marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
		marshaller.marshal(object, new File(path));
	}

	@SuppressWarnings("unchecked")
	@Override
	public <O> O importFromXml(Class<O> klass, String path) throws JAXBException, FileNotFoundException {
		JAXBContext context = JAXBContext.newInstance(klass);
		Unmarshaller unmarshaller = context.createUnmarshaller();
		return (O) unmarshaller.unmarshal(new FileReader(path));
	}
}
