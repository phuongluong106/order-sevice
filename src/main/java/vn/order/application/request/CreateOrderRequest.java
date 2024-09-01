package vn.order.application.request;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
@Valid
public class CreateOrderRequest extends ApiRequest{
    private Integer shopId;
    private List<Integer> menuIds;
    private Integer customerId;
}
