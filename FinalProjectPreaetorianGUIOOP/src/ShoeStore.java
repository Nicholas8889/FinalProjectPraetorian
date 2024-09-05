import java.util.ArrayList;


class ShoeStore {
    private ArrayList<Shoe> shoes = new ArrayList<>();
    private int shoeCounter = 0;

    public void addShoe(String model, String brand, String color, int price) {
        String code = brand.substring(0, 1).toUpperCase() + (++shoeCounter);
        shoes.add(new Shoe(code, model, brand, color, price));
    }

    public void updateShoe(String code, String model, String brand, String color, int price) {
        for (Shoe shoe : shoes) {
            if (shoe.getCode().equalsIgnoreCase(code)) {
                shoes.remove(shoe);
                shoes.add(new Shoe(code, model, brand, color, price));
                break;
            }
        }
    }

    public void deleteShoe(String code) {
        shoes.removeIf(shoe -> shoe.getCode().equalsIgnoreCase(code));
    }

    public void viewShoes() {
        if (shoes.isEmpty()) {
            System.out.println("There is no shoe!");
        } else {
            System.out.println("======================================================================");
            System.out.println("Kode Sepatu | Model Sepatu | Merk Sepatu | Warna Sepatu | Harga Sepatu");
            System.out.println("======================================================================");
            for (Shoe shoe : shoes) {
                System.out.println(shoe);
            }
        }
    }

    public ArrayList<Shoe> getShoes() {
        return shoes;
    }
    
}