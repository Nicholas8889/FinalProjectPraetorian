

class Shoe {
    private String code;
    private String model;
    private String brand;
    private String color;
    private int price;

    public Shoe(String code, String model, String brand, String color, int price) {
        this.code = code;
        this.model = model;
        this.brand = brand;
        this.color = color;
        this.price = price;
    }

    public String getCode() {
        return code;
    }

    public String getModel() {
        return model;
    }

    public String getBrand() {
        return brand;
    }

    public String getColor() {
        return color;
    }

    public int getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return code + " | " + model + " | " + brand + " | " + color + " | " + price;
    }
}