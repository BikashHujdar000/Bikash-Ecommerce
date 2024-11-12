package com.example.biki.ecom.ecommerce.bikash.Services.ServiceImpl;

import com.example.biki.ecom.ecommerce.bikash.Domain.OrderStatus;
import com.example.biki.ecom.ecommerce.bikash.Dtos.OrderDto;
import com.example.biki.ecom.ecommerce.bikash.Entities.Cart;
import com.example.biki.ecom.ecommerce.bikash.Entities.Order;
import com.example.biki.ecom.ecommerce.bikash.Entities.OrderItem;
import com.example.biki.ecom.ecommerce.bikash.Entities.User;
import com.example.biki.ecom.ecommerce.bikash.Exceptions.CartEmptyException;
import com.example.biki.ecom.ecommerce.bikash.Exceptions.ResourceNotFound;
import com.example.biki.ecom.ecommerce.bikash.Exceptions.UnauthorizedException;
import com.example.biki.ecom.ecommerce.bikash.Repositories.CartRepository;
import com.example.biki.ecom.ecommerce.bikash.Repositories.OrderRepository;
import com.example.biki.ecom.ecommerce.bikash.Repositories.UserRepository;
import com.example.biki.ecom.ecommerce.bikash.Services.AllServices.OrderService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private CartRepository cartRepository;

    // first i will implement this only
    @Override
    public OrderDto createOrder(Long userId) {


        // logic user fetch ,  fetch cart , and cart items , create new order , ser user ser status
        // order ITem list

        User user = this.userRepository.findById(userId).orElseThrow(() -> new ResourceNotFound("User", "userId", userId));
        Cart cart = user.getCart();

        // checking cart  is empty  or  do not have any cart items  then

        if (cart == null || cart.getCartItemsList().isEmpty()) {
            throw new CartEmptyException("Cart is empty");
        }

        // lets see what are in the order entity  id , user , status , date , total amount , order Item
        Order order = new Order();

        order.setUser(user);
        order.setStatus(OrderStatus.PENDING.toString());
        order.setOrderDate(LocalDateTime.now());


        List<OrderItem> orderItems = cart.getCartItemsList().stream().map(cartItem -> {

            OrderItem orderItem = new OrderItem();
            orderItem.setOrder(order);
            orderItem.setProduct(cartItem.getProduct());
            orderItem.setQuantity(cartItem.getQuantity());
            orderItem.setPrice(cartItem.getPrice());
            return orderItem;


        }).collect(Collectors.toList());
        order.setOrderItems(orderItems);


        double totalPrice = orderItems.stream().mapToDouble(orderItem -> orderItem.getPrice()).sum();


        order.setTotalAmount(totalPrice);
        Order savedOrder = this.orderRepository.save(order);

        // clear the selected items from the cart

       try
       {
           cart.getCartItemsList().clear();
           this.cartRepository.save(cart);

       } catch (RuntimeException ex)
       {
         throw  new UnauthorizedException("Cart ITem is not cleared");
       }








        return this.modelMapper.map(savedOrder, OrderDto.class);


    }

    @Override
    public List<OrderDto> getOrdersByUser(Long userId) {
        return List.of();
    }

    @Override
    public OrderDto getOrderById(Long orderId) {
        return null;
    }

    @Override
    public OrderDto updateOrderStatus(Long orderId, String status) {
        return null;
    }

    @Override
    public void cancelOrder(Long orderId) {

    }
}
