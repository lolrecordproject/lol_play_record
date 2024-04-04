package project_team.lol_play_record.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import project_team.lol_play_record.domain.Item;

import java.util.List;

public interface ItemRepository extends MongoRepository<Item, String> {

//    void save(Item item);
    Item findByName(String name);
    List<Item> findAll();
//    void updateByName(Long id, Item item);
    void deleteByName(String name);
    public String findProfileByName(String name);

}
