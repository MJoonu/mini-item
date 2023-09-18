package mini.item.web.controller;

import lombok.RequiredArgsConstructor;
import mini.item.domain.item.Item;
import mini.item.repository.item.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.PostConstruct;
import java.util.List;

@Controller
@RequestMapping("/shop/items")
@RequiredArgsConstructor
public class ItemController {

    private final ItemRepository itemRepository;

    @GetMapping
    public String items(Model model) {
        List<Item> items = itemRepository.findAll();
        model.addAttribute("items", items);
        return "shop/items";
    }

    @GetMapping("/{itemId}")
    public String item(@PathVariable long itemId, Model model) {
        Item byId = itemRepository.findById(itemId);
        model.addAttribute("item", byId);
        return "shop/item";
    }


    /**
     * Test Data
     */
    @PostConstruct
    public void init() {
        itemRepository.save(new Item("itemA", 11000, 10));
        itemRepository.save(new Item("itemB", 22000, 20));
    }
}
