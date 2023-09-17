package mini.item.repository.item;

import lombok.extern.slf4j.Slf4j;
import mini.item.domain.item.Item;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Repository
public class ItemRepository {

    private static final Map<Long, Item> store = new HashMap<>(); // static;
    // 해당 repository 가 싱글톤으로 생성되기 때문에 여러개의 쓰레드에서 접근가능하게 될때는 HashMap을 사용하면 안된다.
    // 만일 사용하게된다면 ConcurrentHashMap 을 사용해야한다.
    // 위와 같은 이유로 Long 역시 atomicLong과 같은 타 타입을 사용해야한다.
    private static long sequence = 0L; // static인 이유는 해당 값이 메모리에 고정되어 있어야 하기 때문.

    public Item save(Item item) {
        item.setId(++sequence);
        store.put(item.getId(), item);

        log.info("store inserted Item = {}", store.size());
        log.info("inserted item = {}", item);

        return item;
    }

    public Item findById(Long id) {
        return store.get(id);
    }

    public List<Item> findAll() {
        return new ArrayList<>(store.values());
        // List<Item> 으로 반환하여도 괜찮으나 ex) return store.values();
        // ArrayList로 반환 시 해당 ArrayList 내 값이 변환되어도 List<Item> 에는
        // 영향을 주지 않기 때문에 해당 타입으로 감싸서 반환한 것이다.
    }

    public void update(Long itemId, ItemDTO updateDTO) {
        Item findItem = findById(itemId);
        findItem.setItemName(updateDTO.getItemName());
        findItem.setPrice(updateDTO.getPrice());
        findItem.setQuantity(updateDTO.getQuantity());
    }

    public void clearStore() {
        store.clear();
    }
}
