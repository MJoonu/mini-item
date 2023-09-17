package mini.item.repository.item;

import mini.item.domain.item.Item;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ItemRepositoryTest {

    ItemRepository itemRepository = new ItemRepository();

    @AfterEach
    void afterEach() {
        itemRepository.clearStore();
    }

    @Test
    void save() {
        Item item = new Item("item A", 10000, 10);
    }

    @Test
    void findAll() {

    }

    @Test
    void updateItem() {

    }

}