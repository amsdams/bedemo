package bedemo;

import java.time.LocalDate;
import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import bedemo.repository.RegelingRepo;
import bedemo.repository.WerkgeverRepo;
import bedemo.repository.WerknemerRepo;
import bedemo.service.domain.Regeling;
import bedemo.service.domain.Werkgever;
import bedemo.service.domain.Werknemer;
import lombok.extern.slf4j.Slf4j;

@SpringBootApplication
@Slf4j
public class BeDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(BeDemoApplication.class, args);
	}

	@Autowired
	WerkgeverRepo werkgeverRepo;

	@Autowired
	WerknemerRepo werknemerRepo;

	@Autowired
	RegelingRepo regelingRepo;

	// #okay for now, not for later
	// spring.jpa.properties.hibernate.enable_lazy_load_no_trans=true
	@Bean
	public CommandLineRunner demo() {
		return (args) -> {

			
			
			Werkgever werkgever1 = new Werkgever();
			werkgever1.setNaam("werkgever1 #1");
			werkgeverRepo.saveAndFlush(werkgever1);
			
			Werkgever werkgever2 = new Werkgever();
			werkgever2.setNaam("werkgever2 #2");
			werkgeverRepo.saveAndFlush(werkgever2);
			
			Werkgever werkgever3 = new Werkgever();
			werkgever3.setNaam("werkgever3 #3");
			werkgeverRepo.saveAndFlush(werkgever3);
			
			werkgeverRepo.findAll().stream().forEach(object -> log.info("Werkgever {}", object.toString()));
			
			
			Werknemer werknemer1 = new Werknemer();
			werknemer1.setNaam("werknemer1 #1");
			werknemer1.setAdres("adres");
			werknemer1.setWoonplaats("woonplaats");
			werknemer1.setGeboorteDatum(LocalDate.of(1980, 1, 1));
			werknemer1.setVoltijdSalaris(40000d);
			werknemer1.setDeeltijdPercentage(100d);
			werknemer1.setWerkgever(werkgever1);
			werknemerRepo.saveAndFlush(werknemer1);
			
			Werknemer werknemer2 = new Werknemer();
			werknemer2.setNaam("werknemer2 #2");
			werknemer2.setAdres("adres");
			werknemer2.setWoonplaats("woonplaats");
			werknemer2.setGeboorteDatum(LocalDate.of(1970, 1, 1));
			werknemer2.setVoltijdSalaris(40000d);
			werknemer2.setDeeltijdPercentage(100d);
			werknemer2.setWerkgever(werkgever1);
			werknemerRepo.saveAndFlush(werknemer2);
			
			
			werknemerRepo.findAll().stream().forEach(object -> log.info("Werknemer {}", object.toString()));

			Regeling regeling1  =new Regeling();
			regeling1.setNaam("regeling1 #1");
			regeling1.setVoltijdFranchise(13785d);
			regeling1.setPremiePercentage(1.657);
			regeling1.setWerkgever(werkgever1);
			regeling1.setJaarlijksRendementBeleggingen(3d);
			regelingRepo.saveAndFlush(regeling1);
			
			Regeling regeling2  =new Regeling();
			regeling2.setNaam("regeling2 #2");
			regeling2.setVoltijdFranchise(15599d);
			regeling2.setPremiePercentage(5.0);
			regeling2.setJaarlijksRendementBeleggingen(3d);
			regeling2.setWerkgever(werkgever1);
			regelingRepo.saveAndFlush(regeling2);
			
			regelingRepo.findAll().stream().forEach(object -> log.info("Regeling {}", object.toString()));
			
			
		};
	}

}
