package mini.item.web.controller;

import lombok.RequiredArgsConstructor;
import mini.item.domain.item.Item;
import mini.item.repository.item.ItemDTO;
import mini.item.repository.item.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/add")
    public String addForm() {
        return "shop/addForm";
    }

    /**
     *  V1
     */
//    @PostMapping("/add")
//    public String saveItem(@RequestParam String itemName,
//                           @RequestParam int price,
//                           @RequestParam int quantity,
//                           Model model) {
//
//        Item newItem = new Item();
//
//        newItem.setItemName(itemName);
//        newItem.setPrice(price);
//        newItem.setQuantity(quantity);
//
//        itemRepository.save(newItem);
//
//        model.addAttribute("item", newItem);
//
//        return "shop/item";
//    }

    /**
     * V2
     */
//    @PostMapping("/add")
//    public String saveItem(@ModelAttribute("item") Item item /**  Model model */) {
//        itemRepository.save(item);
////        model.addAttribute("item", item);
//        return "shop/item";
//    }

    /**
     * V3
     */
//    @PostMapping("/add")
//    public String saveItem(@ModelAttribute Item item) {
//        itemRepository.save(item);
//        return "shop/item";
//    }

    /**
     * V4
     */
    @PostMapping("/add")
    public String saveItem(Item item) {
        itemRepository.save(item);
//        return "shop/item";
        return "redirect:/shop/items/" + item.getId();
    }

    @GetMapping("/{itemId}/edit")
    public String editForm(@PathVariable Long itemId, Model model) {
        Item byId = itemRepository.findById(itemId);
        model.addAttribute("item", byId);
        return "shop/editForm";
    }

    @PostMapping("/{itemId}/edit")
    public String editItem(@PathVariable Long itemId ,@ModelAttribute("item") ItemDTO item) {
        itemRepository.update(itemId, item);
        return "redirect:/shop/items/{itemId}";
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
