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

    @GetMapping("items/{id}")
    public ItemDto findItemById(@PathVariable("id") Long id) {
        return itemService.findItemById(id);
    }

    @GetMapping("items/list")
    public List<ItemDto> findAllItem() {
        return itemService.findAllItem();
    }

    @PatchMapping("items/{id}")
    public void updateItemById(@PathVariable("id") Long id, @RequestBody ItemDto itemDto) {
        itemService.updateItemById(id, itemDto);
    }

    @DeleteMapping("items/{id}")
    public void deleteItemById(@PathVariable("id") Long id) {
        itemService.deleteItemById(id);
    }
}
