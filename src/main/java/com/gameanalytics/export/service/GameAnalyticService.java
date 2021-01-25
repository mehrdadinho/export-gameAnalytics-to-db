package com.gameanalytics.export.service;

import com.gameanalytics.export.model.GameAnalytic;
import com.gameanalytics.export.repository.GameAnalyticRepository;
import com.gameanalytics.export.utility.CompressFileGzip;
import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by m.peykari on 6/14/2020.
 */
@PropertySource("classpath:Config.properties")
@Service
public class GameAnalyticService {

    private static final Logger logger = LoggerFactory.getLogger(GameAnalyticService.class);

    @Autowired
    GameAnalyticRepository gameAnalyticRepository;

    @Autowired
    CompressFileGzip gZipFile;

    @Value("${filepath}")
    File filepath;

    @Value("${downloadFileFormat}")
    String downloadFileFormat;

    @Value("${jsonFormat}")
    String jsonFormat;

    @Value("${savedFolder}")
    String savedFolder;

    @Value("${downloadFilePath}")
    File downloadFilePath;

    public void downloadAndPersist() throws FileNotFoundException {
        logger.debug("downloadAndPersist-> start checking...");

        //read all files in download folder
        String[] listOfDownloadFiles = downloadFilePath.list();
        if (listOfDownloadFiles == null){
            throw new FileNotFoundException("FTP path not found");
        }
        logger.debug("downloadAndPersist-> download files found: "+ (listOfDownloadFiles.length-1));

        for (String downloadItem : listOfDownloadFiles) {
            File linkFile = new File(downloadFilePath + File.separator + downloadItem);
            if (linkFile.exists() && downloadItem.endsWith("txt")) {
                File downloadLinks = new File(downloadFilePath + File.separator + downloadItem);
                logger.info("downloadAndPersist-> downloading JSON from file: " + downloadLinks.getPath());
                List<String> linkList = new ArrayList<>();
                try (Stream<String> downloadStream = Files.lines(Paths.get(downloadFilePath + File.separator + downloadItem))) {
                    linkList = downloadStream.flatMap(line -> Stream.of(line)).collect(Collectors.toList());
                    for (String downloadUrl : linkList) {
                        Integer count = gameAnalyticRepository.countDistinctByLinkUrl(downloadUrl);
                        if (count == null || count < 1){
                        try {
                            URL website = new URL(downloadUrl);
                            ReadableByteChannel rbc = Channels.newChannel(website.openStream());
                            String fileName = downloadUrl.substring(downloadUrl.lastIndexOf('/') + 1, downloadUrl.length());
                            String fileNameWithoutExtn = fileName.substring(0, fileName.lastIndexOf('.'));
                            FileOutputStream fos = new FileOutputStream(filepath + File.separator + fileNameWithoutExtn + downloadFileFormat);
                            fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
                            fos.close();
                            //read file into stream
                            String[] listOfDirectory = filepath.list();
                            if (listOfDirectory == null) {
                                throw new FileNotFoundException("FTP path not found");
                            }
                            logger.debug("downloadAndPersist-> files found: " + listOfDirectory.length);

                            for (String fileItem : listOfDirectory) {

                                File zipFile = new File(filepath + File.separator + fileItem);
                                if (zipFile.exists() && fileItem.endsWith(downloadFileFormat)) {

                                    String gzip_filepath = zipFile.getAbsolutePath();
                                    String decompressed_filepath = filepath + File.separator + zipFile.getName() + jsonFormat;
                                    gZipFile.unGunzipFile(gzip_filepath, decompressed_filepath);
                                    File file = new File(filepath + File.separator + zipFile.getName() + jsonFormat);
                                    logger.info("downloadAndPersist-> processing file: " + file.getPath());
                                    List<String> list = new ArrayList<>();

                                    try (Stream<String> stream = Files.lines(Paths.get(filepath + File.separator + fileItem + jsonFormat))) {
                                        list = stream.flatMap(line -> Stream.of(line)).collect(Collectors.toList());

                                        for (int i = 0; i < list.size(); i++) {

                                            Gson g = new Gson();
                                            GameAnalytic entity = g.fromJson(list.get(i), GameAnalytic.class);
                                            entity.setFile_name(file.getName());
                                            entity.setLinkUrl(downloadUrl);
                                            entity.setDateKey(new Date());

                                            if (entity.getData() != null) {
                                                entity.getData().setGameAnalytic(entity);
                                            }
                                            if (entity.getUser_meta() != null) {
                                                entity.getUser_meta().setGameAnalytic(entity);
                                            }
                                            if (entity.getUser_meta() != null && entity.getUser_meta().getRevenue() != null) {
                                                entity.getUser_meta().getRevenue().setUser_meta(entity.getUser_meta());
                                            }

                                            if (entity.getData().getMessage() != null && entity.getData().getMessage().length() > 255) {
                                                entity.getData().setMessage(entity.getData().getMessage().substring(0, 254));
                                            }

                                            if (entity.getData().getReason() != null && entity.getData().getReason().length() > 255) {
                                                entity.getData().setReason(entity.getData().getReason().substring(0, 254));
                                            }

                                            gameAnalyticRepository.save(entity);

                                        }
                                        try {
                                            File newDir = new File(file.getParent() + File.separator + savedFolder);
                                            newDir.mkdir();
                                            logger.debug("downloadAndPersist-> create directory: " + newDir.getPath());
                                            Files.copy(Paths.get(zipFile.getPath()), Paths.get(zipFile.getParent() + File.separator + savedFolder + File.separator  + zipFile.getName()));
                                            logger.debug("downloadAndPersist-> file: " + file.getName() + " successfully copied");
                                            zipFile.delete();
                                            file.delete();
                                            logger.info("downloadAndPersist-> file: " + file.getName() + " successfully deleted");
                                        } catch (IOException e) {
                                            logger.error("downloadAndPersist-> IOException occur: " + e.getMessage());
                                            e.printStackTrace();
                                        }
                                    } catch (IndexOutOfBoundsException e) {
                                        logger.error("downloadAndPersist-> IndexOutOfBoundsException occur: " + e.getMessage());
                                        e.printStackTrace();
                                    } catch (IOException e) {
                                        logger.error("downloadAndPersist-> IOException occur: " + e.getMessage());
                                        e.printStackTrace();
                                    } catch (Exception e) {
                                        logger.error("downloadAndPersist-> Exception occur: " + e.getMessage());
                                        e.printStackTrace();
                                    }

                                }
                            }
                        } catch (MalformedURLException e) {
                            e.printStackTrace();
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }else{
                            logger.warn("downloadAndPersist-> Duplicate LinkUrl Found: " + downloadUrl);
                        }
                    }
                } catch (IOException e) {
                    logger.error("downloadAndPersist-> IOException occur: " + e.getMessage());
                    e.printStackTrace();
                }

                try {
                    File savedLinkDir = new File(downloadLinks.getParent() + File.separator + savedFolder);
                    savedLinkDir.mkdir();
                    logger.debug("downloadAndPersist-> create directory: " + savedLinkDir.getPath());
                    Files.copy(Paths.get(downloadLinks.getPath()), Paths.get(downloadLinks.getParent() + File.separator + savedFolder+ File.separator + downloadLinks.getName()));
                    logger.debug("downloadAndPersist-> file: " + downloadLinks.getName() + " successfully copied");
                    downloadLinks.delete();
                    logger.info("downloadAndPersist-> file: " + downloadLinks.getName() + " successfully deleted");
                } catch (IOException e) {
                    logger.error("downloadAndPersist-> IOException occur: " + e.getMessage());
                    e.printStackTrace();
                }

            }


        }
    }

//    public static void main(String[] args) throws MalformedURLException {
//        String s = "{\"user_meta\":{\"revenue\":{},\"origin\":\"organic\",\"is_converting\":\"false\",\"install_ts\":1588409438,\"install_hour\":1588406400,\"first_build\":\"1.0\",\"cohort_week\":1587945600,\"cohort_month\":1588291200},\"ip\":\"217.138.199.0\",\"game_id\":95065,\"first_in_batch\":true,\"data\":{\"v\":2,\"user_id\":\"8f09d7e3-f150-41df-9e3b-0865b62ea76b\",\"session_num\":7,\"session_id\":\"2e281966-8db0-460a-94cd-4861f1e5e7eb\",\"sdk_version\":\"unity 6.0.9\",\"platform\":\"android\",\"os_version\":\"android 9\",\"manufacturer\":\"HUAWEI\",\"google_aid_src\":\"service\",\"google_aid\":\"8f09d7e3-f150-41df-9e3b-0865b62ea76b\",\"engine_version\":\"unity 2019.4.0\",\"device\":\"JKM-LX1\",\"custom_02\":\"80\",\"connection_type\":\"wwan\",\"client_ts\":1607379647,\"category\":\"user\",\"build\":\"1.30\",\"android_channel_id\":\"com.farsitel.bazaar\",\"android_bundle_id\":\"com.GarajGames.TarahBashi\",\"android_app_version\":\"1.30\",\"android_app_signature\":\"414200fd4c8f6c90de278359517adad84a739f3b\",\"android_app_build\":\"30\"},\"country_code\":\"CZ\",\"arrival_ts\":1607379648}";
//        Gson g = new Gson();
//        GameAnalytic entity = g.fromJson(s, GameAnalytic.class);
//        System.out.println("entity loaded.");

//        File file = new File("https://go-export.gameanalytics.com/export-data/95065-2020-12-07-3,353.json.gz?e=1609055104&s=vsfyprbKBA9iDSm4Rrk6b5BoWzQ%3D");
//        String url ="https://go-export.gameanalytics.com/export-data/95065-2020-12-07-3,353.json.gz?e=1609055104&s=vsfyprbKBA9iDSm4Rrk6b5BoWzQ%3D";
//        String fileName = url.substring( url.lastIndexOf('/')+1, url.length() );
//        String fileNameWithoutExtn = fileName.substring(0, fileName.lastIndexOf('.'));
////        System.out.println(FilenameUtils.getBaseName(url.getPath())); // -> file
////        System.out.println(FilenameUtils.getExtension(url.getPath())); // -> xml
////        System.out.println(FilenameUtils.getName(url.getPath())); // -> file.xml
//        System.out.println("hello");
//        System.out.println(fileName);
//        System.out.println(fileNameWithoutExtn);
//
//    }

}
