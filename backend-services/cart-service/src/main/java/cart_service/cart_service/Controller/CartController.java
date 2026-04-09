package cart_service.cart_service.Controller;

import cart_service.cart_service.entity.Cart;
import cart_service.cart_service.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    @PostMapping("/add")
    public Cart addToCart(@RequestBody Cart cart) {
        return cartService.addToCart(cart);
    }

    @GetMapping
    public List<Cart> getAllCarts() {
        return cartService.getAllCarts();
    }
    @DeleteMapping("/{id}")
    public String removeCartItem(@PathVariable Long id) {
        cartService.removeCartItem(id);
        return "Item removed from cart";
    }
    @GetMapping("/user/{userId}")
    public List<Cart> getCartByUser(@PathVariable Long userId) {
        return cartService.getCartByUser(userId);
    }
}