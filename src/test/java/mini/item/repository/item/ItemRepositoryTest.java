package mini.item.repository.item;

import mini.item.domain.item.Item;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;
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

        Item saved = itemRepository.save(item);

        Item findItem = itemRepository.findById(saved.getId());

        assertThat(saved).isEqualTo(findItem);
    }

    @Test
    void findAll() {
        Item item1 = new Item("item A", 10000, 10);
        Item item2 = new Item("item B", 10000, 10);

        itemRepository.save(item1);
        itemRepository.save(item2);

        List<Item> all = itemRepository.findAll();

        assertThat(all.size()).isEqualTo(2);
        assertThat(all).contains(item1, item2);
    }

    @Test
    void updateItem() {
        Item item = new Item("item A", 10000, 10);
        Item saved = itemRepository.save(item);
        Long id = saved.getId();



        ItemDTO updateDTO = new ItemDTO(item.getItemName(), 20000, 10);

        System.out.println("saved.getPrice() = " + saved.getPrice());


        itemRepository.update(id, updateDTO);
        Item updated = itemRepository.findById(id);

        System.out.println("saved.getPrice() = " + saved.getPrice());

        assertThat(saved.getPrice()).isEqualTo(updated.getPrice());

    }

}