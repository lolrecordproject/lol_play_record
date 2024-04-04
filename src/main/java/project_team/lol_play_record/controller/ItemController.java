package project_team.lol_play_record.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project_team.lol_play_record.dto.ItemDto;
import project_team.lol_play_record.service.ItemService;
import org.apache.commons.io.IOUtils;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@RestController
public class ItemController {

    private final ItemService itemService;

    // 의존성 생성자 주입
    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @GetMapping("/api/test")
    public String app() {
        return "테스트입니다.";
    }

    @GetMapping("front")
    public String index() {
        return "프론트";
    }

    @GetMapping(value = "items/profile/{name}",
            produces = MediaType.IMAGE_PNG_VALUE)
    public ResponseEntity<byte[]> profile(@PathVariable("name") String name) throws IOException {

//        String url = itemService.findProfileByName(name);
//        System.out.println(url);
//        InputStream in = getClass().getResourceAsStream(url);
//        return IOUtils.toByteArray(in); //아파치 의존성 추가해야 해결됨

        byte[] res = itemService.findProfileByName(name);

        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @PostMapping("items")
    public void save(ItemDto itemDto) {
        System.out.println(itemDto.toString());
        itemService.saveItem(itemDto);
    }

    @GetMapping("items/{name}")
    public ItemDto findItemByName(@PathVariable("name") String name) {
        return itemService.findItemByName(name);
    }

    @GetMapping("items/list")
    public List<ItemDto> findAllItem() {
        return itemService.findAllItem();
    }

//    @PatchMapping("items/{name}")
//    public void updateItemByName(@PathVariable("id") String name, @RequestBody ItemDto itemDto) {
//        itemService.updateItemByName(name, itemDto);
//    }

    @DeleteMapping("items/{name}")
    public void deleteItemByName(@PathVariable("name") String name) {
        itemService.deleteItemByName(name);
    }
}
