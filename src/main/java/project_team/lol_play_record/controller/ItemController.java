package project_team.lol_play_record.controller;

import org.springframework.web.bind.annotation.*;
import project_team.lol_play_record.dto.ItemDto;
import project_team.lol_play_record.service.ItemService;

import java.util.List;

@RestController
public class ItemController {

    private final ItemService itemService;

    // 의존성 생성자 주입
    public ItemController(ItemService itemService) {
        this.itemService = itemService;
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
