package com.app;

import com.app.domain.Link;
import com.app.domain.LinkDetails;
import com.app.message.ParseLinkDetails;
import com.app.repository.AccountRepository;
import com.app.repository.LinkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories
@ComponentScan
@EnableAutoConfiguration
@SpringBootApplication
public class PocketappApplication implements CommandLineRunner {

	@Autowired
	AccountRepository accountRepository;

	@Autowired
	LinkRepository linkRepository;

	@Override
	public void run(String... args) throws Exception {
		/*for(int i=0;i<1000;i++){
			Link link = new Link();
			link.setAccount(accountRepository.findOneByEmail("test user1"));
			link.setUrl("test url "+i);
			link.setCategory("all same");

			LinkDetails linkDetails = new LinkDetails();
			linkDetails.setTitle("test title");
			linkDetails.setDescription("test desc");

			link.setLinkDetails(linkDetails);
			linkRepository.save(link);
		}*/
	}

	public static void main(String[] args) {
		SpringApplication.run(PocketappApplication.class, args);
	}
}
