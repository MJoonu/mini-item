package mini.item.domain.item;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import mini.item.repository.item.ItemDTO;

// @Data // getter setter tostring constructor 등 다 만들기 때문에 좀 위험
@Getter @Setter @ToString
public class Item {
    private Long id;
    private String itemName;
    private Integer price;
    private Integer quantity;

    public Item() {
    }

    public Item(String itemName, Integer price, Integer quantity) {
        this.itemName = itemName;
        this.price = price;
        this.quantity = quantity;
    }

    public Item(ItemDTO dto) {
        this.itemName = dto.getItemName();
        this.price = dto.getPrice();
        this.quantity = dto.getQuantity();
    }
}
