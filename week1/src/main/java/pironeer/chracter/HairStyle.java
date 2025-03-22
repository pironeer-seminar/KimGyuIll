package pironeer.chracter;

public enum HairStyle {
    LONG_HAIR("장발이야"),
    SHORT_HAIR("단발머리야"),
    CURLY("파마를 했어"),
    BALD("스님 머리야"),
    TWO_BLOCK("투블럭을 했어"),
    WAIST_LONG("허리까지 머리카락이 있어");

    private final String description;

    HairStyle(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
