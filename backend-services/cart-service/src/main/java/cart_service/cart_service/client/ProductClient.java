package cart_service.cart_service.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@FeignClient(name = "product-service", url = "http://localhost:8087")
public interface ProductClient {

    @GetMapping("/product/{id}")
    Map<String, Object> getProductById(@PathVariable Long id);
}