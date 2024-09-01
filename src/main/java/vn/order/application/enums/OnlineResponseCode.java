package vn.order.application.enums;

public enum OnlineResponseCode {

    UnknownError(-99),
    SystemError(500),
    UnprocessableEntity(422),
    Successful(0),
    NotFound(400);
    private OnlineResponseCode(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public String getStringCode() {
        return this.toString();
    }

    private final int code;
}
