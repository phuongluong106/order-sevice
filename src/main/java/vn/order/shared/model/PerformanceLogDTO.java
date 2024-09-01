package vn.order.shared.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PerformanceLogDTO {
    private String uri;
    private String operation;
    private int httpStatus;
    private long time;

    public PerformanceLogDTO(String uri, int httpStatus, long time) {
        this.uri = uri;
        this.httpStatus = httpStatus;
        this.time = time;
    }
}
