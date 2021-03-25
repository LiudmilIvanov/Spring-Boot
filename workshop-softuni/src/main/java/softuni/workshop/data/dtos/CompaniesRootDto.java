package softuni.workshop.data.dtos;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement(name = "companies")
@XmlAccessorType(XmlAccessType.FIELD)
public class CompaniesRootDto {
	
	@XmlElement(name = "company")
	private List<CompanyDto> companies;
	
	public CompaniesRootDto() {

	}

	public List<CompanyDto> getCompanies() {
		return companies;
	}

	public void setCompanies(List<CompanyDto> companies) {
		this.companies = companies;
	}
	
	
}
