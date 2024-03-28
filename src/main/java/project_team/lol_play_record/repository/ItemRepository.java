package project_team.lol_play_record.repository;

import project_team.lol_play_record.domain.Item;

import java.util.List;

public interface ItemRepository {

    void save(Item item);
    Item findByName(String name);
    List<Item> findAll();
//    void updateByName(Long id, Item item);
    void deleteByName(String name);

}
