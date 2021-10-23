package GoodsData;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Goods {
    private String name;
    private String price;

    @Override
    public String toString() {
        return this.name + "\n" + this.price + "\n";
    }
}
