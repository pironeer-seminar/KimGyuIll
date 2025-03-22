package pironeer.chracter;

public enum Clothes {
    SPORTS_WEAR("운동복을 입었어"),
    SUIT("양복을 입었어"),
    COAT("무스탕을 입었어"),
    SHIRT("셔츠를 입었어"),
    SKIRT("치마를 입었어"),
    DRESS("원피스를 입었어"),
    T_SHIRT("반팔티를 입었어");

    private final String description;

    Clothes(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
