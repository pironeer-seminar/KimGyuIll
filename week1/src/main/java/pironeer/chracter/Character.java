package pironeer.chracter;

public class Character {
    private String name;
    private HairStyle hair;
    private Clothes clothes;
    private Shoes shoes;

    public Character(String name, HairStyle hair, Clothes clothes, Shoes shoes) {
        this.name = name;
        this.hair = hair;
        this.clothes = clothes;
        this.shoes = shoes;
    }

    public String getName() {
        return name;
    }

    public String getHair() {
        return hair.getDescription();
    }

    public String getClothes() {
        return clothes.getDescription();
    }

    public String getShoes() {
        return shoes.getDescription();
    }
}
