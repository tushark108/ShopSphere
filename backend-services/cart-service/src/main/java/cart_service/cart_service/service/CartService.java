package cart_service.cart_service.service;

import cart_service.cart_service.entity.Cart;
import cart_service.cart_service.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CartService {

    @Autowired
    private CartRepository cartRepository;

    public Cart addToCart(Cart cart) {

        Optional<Cart> existingCart =
                cartRepository.findByUserIdAndProductId(cart.getUserId(), cart.getProductId());

        if(existingCart.isPresent()) {
            Cart existing = existingCart.get();
            existing.setQuantity(existing.getQuantity() + cart.getQuantity());
            return cartRepository.save(existing);
        }

        return cartRepository.save(cart);
    }

    public void removeCartItem(Long id) {
        cartRepository.deleteById(id);
    }
    public List<Cart> getCartByUser(Long userId) {
        return cartRepository.findByUserId(userId);
    }
    public List<Cart> getAllCarts() {
        return cartRepository.findAll();
    }
}