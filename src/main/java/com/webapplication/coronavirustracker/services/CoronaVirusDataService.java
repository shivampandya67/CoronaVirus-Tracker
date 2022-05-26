package com.webapplication.coronavirustracker.services;

import java.io.IOException;
import java.io.StringReader;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import com.webapplication.coronavirustracker.models.LocationStats;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class CoronaVirusDataService {

    private static String VIRUS_DATA_URL = "https://raw.githubusercontent.com/CSSEGISandData/COVID-19/master/csse_covid_19_data/csse_covid_19_time_series/time_series_covid19_confirmed_global.csv";

    private List<LocationStats> allStats = new ArrayList<>();

    /**
     * @return List<LocationStats> return the allStats
     */
    public List<LocationStats> getAllStats() {
        return allStats;
    }

    @PostConstruct
    @Scheduled(cron = "* * 1 * * *")    
    public void fetchVirusData() throws IOException, InterruptedException {

        List<LocationStats> newStats = new ArrayList<>();

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = (HttpRequest) HttpRequest.newBuilder().uri(URI.create(VIRUS_DATA_URL)).build();

        HttpResponse<String> httpResponse = client.send(request, HttpResponse.BodyHandlers.ofString());
        // System.out.println(httpResponse.body());

        StringReader csvBodyReader = new StringReader(httpResponse.body());

        try (CSVParser csvParser = new CSVParser(csvBodyReader,
                CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim())) {
            for (CSVRecord csvRecord : csvParser) {
                LocationStats locationStat = new LocationStats();
                locationStat.setState(csvRecord.get(0));
                locationStat.setCountry(csvRecord.get(1));

                int latestCases = Integer.parseInt(csvRecord.get(csvRecord.size() - 1));
                int prevDayCases = Integer.parseInt(csvRecord.get(csvRecord.size() - 2));
                locationStat.setLatestTotalCases(latestCases);
                locationStat.setDiffFromPreviousDay(latestCases-prevDayCases);
                // System.out.println(locationStat);
                newStats.add(locationStat);
            }
            this.allStats = newStats;
        }
    }

}
