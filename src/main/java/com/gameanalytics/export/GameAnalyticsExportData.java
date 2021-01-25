package com.gameanalytics.export;

import com.gameanalytics.export.service.GameAnalyticService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.io.File;
import java.io.FileNotFoundException;

@SpringBootApplication
@EnableScheduling
@PropertySource("classpath:Config.properties")
public class GameAnalyticsExportData {

	private static final Logger logger = LoggerFactory.getLogger(GameAnalyticsExportData.class);

	@Autowired
    GameAnalyticService service;

	@Value("${filepath}")
	File filepath;

	@Scheduled(fixedDelay = 60000) //repeat every 60 seconds.
	public void exportData() throws FileNotFoundException {
		logger.debug("exportData-> looking for File in Directory: "+ filepath.getPath());
		service.downloadAndPersist();
	}

	public static void main(String[] args) {
		SpringApplication.run(GameAnalyticsExportData.class, args);
	}

}
