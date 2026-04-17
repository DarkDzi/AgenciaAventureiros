package TP1.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableCaching
@EnableScheduling
public class Tp1PbApplication {

	public static void main(String[] args) {
		SpringApplication.run(Tp1PbApplication.class, args);
	}

}

//TODO arrumar handle para não usar só exeção runtime, arrumar jacksonconfig, tipos de cache, arrumar nome, conexões com o banco abrindo toda hora
// arrumar uso de dto painel tático
