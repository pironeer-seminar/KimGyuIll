package pironeer;

public enum DyingMessageType {
    HAIR("머리스타일은"),
    CLOTHES("옷은"),
    SHOES("신발은");

    private final String message;

    DyingMessageType(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
