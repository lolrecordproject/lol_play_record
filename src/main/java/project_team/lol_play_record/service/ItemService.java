package project_team.lol_play_record.service;

import org.springframework.stereotype.Service;
import project_team.lol_play_record.domain.Item;
import project_team.lol_play_record.dto.ItemDto;
import project_team.lol_play_record.repository.ItemRepository;

import java.util.List;

@Service
public class ItemService {

    private final ItemRepository itemRepository;

    public ItemService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    public void saveItem(ItemDto itemDto) {
        Item item = new Item(); // item 객체 생성 후 itemDto의 내용을 item에 저장
        item.setName(itemDto.getName());

        itemRepository.save(item); // itemRepository를 통해 item을 저장
    }

    public ItemDto findItemById(Long id) {
        Item item = itemRepository.findById(id); // itemRepository를 통해 id에 해당하는 item을 찾아서 반환

        ItemDto itemDto = new ItemDto(); // itemDto 객체 생성 후 item의 내용을 itemDto에 저장
        itemDto.setId(item.getId());
        itemDto.setName(item.getName());

        return itemDto; // itemDto 반환
    }

    public List<ItemDto> findAllItem() {
        return itemRepository.findAll()
                .stream()
                .map(item -> {
                    ItemDto itemDto = new ItemDto(); // itemDto 객체 생성 후 item의 내용을 itemDto에 저장
                    itemDto.setId(item.getId());
                    itemDto.setName(item.getName());

                    return itemDto; // itemDto 반환
                })
                .toList(); // itemRepository를 통해 모든 item을 찾아서 반환
    }

    public void updateItemById(Long id, ItemDto itemDto) {
        Item findItem = itemRepository.findById(id); // itemRepository를 통해 id에 해당하는 item을 찾아서 반환
        findItem.setName(itemDto.getName());

        itemRepository.updateById(id, findItem); // itemRepository를 통해 id에 해당하는 item을 찾아서 내용을 수정
    }

    public void deleteItemById(Long id) {
        itemRepository.deleteById(id); // itemRepository를 통해 id에 해당하는 item을 찾아서 삭제
    }

}
