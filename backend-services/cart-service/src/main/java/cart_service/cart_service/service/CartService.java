package cart_service.cart_service.service;

import cart_service.cart_service.client.ProductClient;
import cart_service.cart_service.entity.Cart;
import cart_service.cart_service.repository.CartRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class CartService {

    private final CartRepository cartRepository;
    private final ProductClient productClient;


    // ✅ Constructor Injection (BEST PRACTICE)
    public CartService(CartRepository cartRepository, ProductClient productClient) {
        this.cartRepository = cartRepository;
        this.productClient = productClient;
    }

    public Cart addToCart(Cart cart) {

        // 🔥 Step 1: Get product from Product Service
        Map<String, Object> product;

        try {
            product = productClient.getProductById(cart.getProductId());

            if (product == null || product.isEmpty()) {
                throw new RuntimeException("Product not found");
            }

        } catch (Exception e) {
            throw new RuntimeException("Product not found in Product Service");
        }

        // 🔥 Step 2: Validate STOCK
        Integer availableQty = (Integer) product.get("quantity");

        if (cart.getQuantity() > availableQty) {
            throw new RuntimeException("Insufficient stock available");
        }

        // 🔥 Step 3: Set price from product-service
        Double price = Double.valueOf(product.get("price").toString());
        cart.setPrice(price);

        // 🔥 Step 4: Existing cart logic
        Optional<Cart> existingCart =
                cartRepository.findByUserIdAndProductId(cart.getUserId(), cart.getProductId());

        if (existingCart.isPresent()) {
            Cart existing = existingCart.get();

            int newQty = existing.getQuantity() + cart.getQuantity();

            // 🔥 Re-check stock for updated quantity
            if (newQty > availableQty) {
                throw new RuntimeException("Total quantity exceeds stock");
            }

            existing.setQuantity(newQty);
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