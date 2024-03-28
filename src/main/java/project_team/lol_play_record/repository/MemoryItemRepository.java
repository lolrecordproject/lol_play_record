package project_team.lol_play_record.repository;

import org.springframework.stereotype.Repository;
import project_team.lol_play_record.domain.Item;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class MemoryItemRepository implements ItemRepository{

    private static Map<String, Item> store = new HashMap<>();

    @Override
    public void save(Item item) {
        store.put(item.getName(), item); // store에 저장
        System.out.println(store);
    }

    @Override
    public Item findByName(String name) {
        return store.get(name); // id에 해당하는 item을 찾아서 반환
    }

    @Override
    public List<Item> findAll() {
        return store.values().stream().toList(); // store에 있는 모든 value를 ArrayList에 담아서 반환
    }

//    @Override
//    public void updateByName(Long id, Item item) {
//        store.put(id, item); // store에 저장, hash map은 key가 같으면 덮어씌워지기 때문에 id는 그대로 두고 내용만 수정
//    }

    @Override
    public void deleteByName(String name) {
        store.remove(name); // id에 해당하는 item을 찾아서 삭제
    }
}
