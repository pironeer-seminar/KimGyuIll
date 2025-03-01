package pironeer.chracter;

public enum Shoes {
    NIKE("나이키를 신었어"),
    LEATHER_SHOES("구두를 신었어"),
    BAREFOOT("아무것도 안 신고 있었어"),
    SLIPPERS("슬리퍼 신었어"),
    BOOTS("부츠를 신었어"),
    CROCS("크록스를 신었어"),
    HEELS("힐을 신었어");

    private final String description;

    Shoes(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
