package project_team.lol_play_record.service;

import org.apache.commons.io.IOUtils;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.util.UriComponentsBuilder;
import project_team.lol_play_record.domain.Item;
import project_team.lol_play_record.dto.ItemDto;
import project_team.lol_play_record.dto.MatchDto;
import project_team.lol_play_record.dto.MatchGameDto;
import project_team.lol_play_record.repository.ItemRepository;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URL;
import java.util.List;

@Service
@PropertySource(ignoreResourceNotFound = false, value = "classpath:riotApiKey.properties")
public class ItemService {

    private final ItemRepository itemRepository;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Value("${riot.api.key}")
    private String mykey;

    public ItemService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    public void saveItem(ItemDto itemDto) {
        Item item = new Item(); // item 객체 생성 후 itemDto의 내용을 item에 저장
        item.setName(itemDto.getName());
        System.out.println(item.getName());
        String serverUrl = "https://kr.api.riotgames.com" + "/lol/summoner/v4/summoners/by-name/" + itemDto.getName().replaceAll(" ","%20") + "?api_key=" + mykey;
        URI uri = UriComponentsBuilder
                .fromUriString("https://kr.api.riotgames.com")
                .path("/lol/summoner/v4/summoners/by-name/" + itemDto.getName())
                .queryParam("api_key",mykey)
                .buildAndExpand(itemDto.getName().replaceAll(" ","%20"),"?api_key=" + mykey)
                .encode()
                .toUri();




        logger.info("item list : {}, {}", item.getName(), serverUrl);
        logger.info("uri : {}", uri);
        try {
            // RestTemplate 생성
            RestTemplate restTemplate = new RestTemplate();

            // Request Header 설정
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);

            // Request Body 설정
            JSONObject requestBody = new JSONObject();
            requestBody.put("key", "value");

            // Request Entity 생성
            HttpEntity entity = new HttpEntity(requestBody.toString(), headers);
            ResponseEntity<ItemDto> response = restTemplate.getForEntity(uri, ItemDto.class);
//            ResponseEntity response = restTemplate.exchange(serverUrl, HttpMethod.GET, entity, String.class);

            // Response Body 출력
            System.out.println(response.getBody());
            logger.info("response body : {}",response.getBody());
            logger.info("response item : {}",response.getBody().getName());
            if (response.getBody().getId() != null){
                item.setSummonerLevel(response.getBody().getSummonerLevel());
                item.setRevisionDate(response.getBody().getRevisionDate());
                item.setPuuid(response.getBody().getPuuid());
                item.setId(response.getBody().getId());
                item.setProfileIconId(response.getBody().getProfileIconId());
                item.setAccountId(response.getBody().getAccountId());
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        itemRepository.save(item); // itemRepository를 통해 item을 저장
        System.out.println(item.getSummonerLevel());
    }

    public ItemDto findItemByName(String name) {
        Item item = itemRepository.findByName(name); // itemRepository를 통해 id에 해당하는 item을 찾아서 반환

        ItemDto itemDto = new ItemDto(); // itemDto 객체 생성 후 item의 내용을 itemDto에 저장
        itemDto.setName(item.getName());
        itemDto.setSummonerLevel(item.getSummonerLevel());
        itemDto.setRevisionDate(item.getRevisionDate());
        itemDto.setPuuid(item.getPuuid());
        itemDto.setId(item.getId());
        itemDto.setProfileIconId(item.getProfileIconId());
        itemDto.setAccountId(item.getAccountId());

        return itemDto; // itemDto 반환
    }

    public List<ItemDto> findAllItem() {
        return itemRepository.findAll()
                .stream()
                .map(item -> {
                    ItemDto itemDto = new ItemDto(); // itemDto 객체 생성 후 item의 내용을 itemDto에 저장
                    itemDto.setSummonerLevel(item.getSummonerLevel());
                    itemDto.setRevisionDate(item.getRevisionDate());
                    itemDto.setPuuid(item.getPuuid());
                    itemDto.setId(item.getId());
                    itemDto.setProfileIconId(item.getProfileIconId());
                    itemDto.setAccountId(item.getAccountId());
                    itemDto.setName(item.getName());

                    return itemDto; // itemDto 반환
                })
                .toList(); // itemRepository를 통해 모든 item을 찾아서 반환
    }

//    public void updateItemByName(Long id, ItemDto itemDto) {
//        Item findItem = itemRepository.findByName(id); // itemRepository를 통해 id에 해당하는 item을 찾아서 반환
//        findItem.setName(itemDto.getName());
//
//        itemRepository.updateByName(id, findItem); // itemRepository를 통해 id에 해당하는 item을 찾아서 내용을 수정
//    }

    public void deleteItemByName(String name) {
        itemRepository.deleteByName(name); // itemRepository를 통해 id에 해당하는 item을 찾아서 삭제
    }

    public byte[] findProfileByName(String name) throws IOException {
        Item item = itemRepository.findByName(name);
        String IconUrl = "https://ddragon.leagueoflegends.com/cdn/10.6.1/img/profileicon/" + item.getProfileIconId() + ".png";
        InputStream imageStream = new URL(IconUrl).openStream();
        byte[] image = IOUtils.toByteArray(imageStream);
        imageStream.close();

        return image;
    }

    public List<MatchGameDto.ParticipantDto> findMatchGameByName(String name){
        Item item = itemRepository.findByName(name);
//        String active_gameUrl = "https://kr.api.riotgames.com/lol/spectator/v5/active-games/by-summoner";
        URI uri = UriComponentsBuilder
                .fromUriString("https://kr.api.riotgames.com")
                .path("/lol/spectator/v5/active-games/by-summoner/" + item.getPuuid())
                .queryParam("api_key",mykey)
                .build()
                .encode()
                .toUri();

        logger.info("uri : {}", uri);
        try {
            // RestTemplate 생성
            RestTemplate restTemplate = new RestTemplate();

            // Request Header 설정
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);

            // Request Body 설정
            JSONObject requestBody = new JSONObject();
            requestBody.put("key", "value");

            // Request Entity 생성
            HttpEntity entity = new HttpEntity(requestBody.toString(), headers);
            ResponseEntity<MatchGameDto> response = restTemplate.getForEntity(uri, MatchGameDto.class);
//            ResponseEntity response = restTemplate.exchange(serverUrl, HttpMethod.GET, entity, String.class);

            // Response Body 출력
            System.out.println(response.getBody());
            logger.info("response body : {}",response.getBody());
            if (response.getBody().getGameType() != null){
                System.out.println(response.getBody().getParticipants());
                return response.getBody().getParticipants();
            }


        } catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public List<MatchDto.ParticipantDto> findMatchGameByMatchId(String matchId){



        URI uri = UriComponentsBuilder
                .fromUriString("https://asia.api.riotgames.com")
                .path("/lol/match/v5/matches/" + matchId)
                .queryParam("api_key",mykey)
                .build()
                .encode()
                .toUri();

        logger.info("uri : {}", uri);
        try {
            // RestTemplate 생성
            RestTemplate restTemplate = new RestTemplate();

            // Request Header 설정
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);

            // Request Body 설정
            JSONObject requestBody = new JSONObject();
            requestBody.put("key", "value");

            // Request Entity 생성
            HttpEntity entity = new HttpEntity(requestBody.toString(), headers);
            System.out.println(1111);
            ResponseEntity<MatchDto> response = restTemplate.getForEntity(uri, MatchDto.class);
//            ResponseEntity response = restTemplate.exchange(serverUrl, HttpMethod.GET, entity, String.class);



            // Response Body 출력
            System.out.println(response.getBody());
            logger.info("response body : {}",response.getBody());
            if (response.getBody() != null){
                logger.info("infodto : {}", response.getBody().getInfo());
                logger.info("participants : {}", response.getBody().getInfo().participants);

                return response.getBody().getInfo().participants;
            }

        } catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }


}
