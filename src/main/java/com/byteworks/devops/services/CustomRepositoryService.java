package com.byteworks.devops.services;

import com.byteworks.devops.entities.*;
import com.byteworks.devops.models.Cart;
import com.byteworks.devops.models.CartInformation;
import com.byteworks.devops.repository.CustomRepository;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.CriteriaUpdate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class CustomRepositoryService implements CustomRepository {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Boolean CUSTOMER_EXIST(String username) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Object[]> criteriaQuery = builder.createQuery(Object[].class);
        Root<Customer> root = criteriaQuery.from(Customer.class);
        criteriaQuery.select(root.get("customerUsername")).where(builder.equal(root.get("customerUsername"), username));
        TypedQuery<Object[]> query = entityManager.createQuery(criteriaQuery);
        List list = query.getResultList();
        return list.size() > 0;
    }

    @Override
    public List<String> FIND_CUSTOMER_ROLE(String username) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Object[]> criteriaQuery = builder.createQuery(Object[].class);
        Root<Permission> root = criteriaQuery.from(Permission.class);
        criteriaQuery.select(root.get("role")).where(builder.equal(root.get("username"), username));
        TypedQuery<Object[]> query = entityManager.createQuery(criteriaQuery);
        return (List) query.getResultList();
    }

    @Override
    public Customer FIND_CUSTOMER_BY_USERNAME(String username) {
        boolean isCustomerExist = CUSTOMER_EXIST(username);
        if (isCustomerExist) {
            CriteriaBuilder builder = entityManager.getCriteriaBuilder();
            CriteriaQuery<Customer> criteriaQuery = builder.createQuery(Customer.class);
            Root<Customer> root = criteriaQuery.from(Customer.class);
            criteriaQuery.select(root).where(builder.equal(root.get("customerUsername"), username));
            TypedQuery<Customer> query = entityManager.createQuery(criteriaQuery);
            return query.getSingleResult();
        }
        return null;
    }

    @Override
    public void UPDATE_CUSTOMER_PERMISSION(String customer) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaUpdate<Permission> criteriaUpdate = builder.createCriteriaUpdate(Permission.class);
        Root<Permission> root = criteriaUpdate.from(Permission.class);
        criteriaUpdate.set(root.get("role"), "ADMIN").where(builder.equal(root.get("username"), customer));
        entityManager.createQuery(criteriaUpdate).executeUpdate();
    }

    @Override
    public void SAVE_CUSTOMER_ORDER(Cart cartInformation) {

        int orderNumber = this.getMaxCustomerOrderNumber() + 1;
        CustomerOrder customerOrder = new CustomerOrder();

        customerOrder.setCustomerOrderId(UUID.randomUUID().toString());
        customerOrder.setCustomerOrderNumber(orderNumber);
        customerOrder.setAmountPaid(cartInformation.getAmountTotal());

        Customer customerInfo = cartInformation.getCustomer();
        customerOrder.setCustomerName(customerInfo.getCustomerName());
        customerOrder.setCustomerPhoneNumber(customerInfo.getCustomerPhoneNumber());
        customerOrder.setCustomerLocation(customerInfo.getCustomerLocation());
        entityManager.persist(customerOrder);

        List<CartInformation> cartInformationList = cartInformation.getCartInformation();

        for (CartInformation cartInfo : cartInformationList) {
            CustomerOrderDetails customerOrderDetails = new CustomerOrderDetails();
            customerOrderDetails.setCustomerOrderDetailsId(UUID.randomUUID().toString());
            customerOrderDetails.setCustomerOrder(customerOrder);
            customerOrderDetails.setAmountPaid(cartInfo.getAmount());
            customerOrderDetails.setPriceOfFood(cartInfo.getFoodInformation().getFoodPrice());
            customerOrderDetails.setQuanityOfFood(cartInfo.getQuantity());

            String code = cartInfo.getFoodInformation().getFoodCode();
            FoodInformation foodInformation = this.FIND_FOOD(code);
            customerOrderDetails.setFood(foodInformation);

            entityManager.persist(customerOrderDetails);
        }
        cartInformation.setOrderNumber(orderNumber);
    }

    @Override
    public CustomerOrder GET_CUSTOMER_ORDER(String orderId) {
        return null;
    }

    @Override
    public FoodInformation FIND_FOOD(String foodCode) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<FoodInformation> foodCriteriaQuery = builder.createQuery(FoodInformation.class);
        Root<FoodInformation> root = foodCriteriaQuery.from(FoodInformation.class);
        foodCriteriaQuery.select(root).where(builder.equal(root.get("code"), foodCode));
        TypedQuery<FoodInformation> query = entityManager.createQuery(foodCriteriaQuery);
        return query.getSingleResult();
    }

    private int getMaxCustomerOrderNumber() {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Integer> query = builder.createQuery(Integer.class);
        Root<CustomerOrder> root = query.from(CustomerOrder.class);
        query.select(builder.max(root.get("customer_order_number")));
        TypedQuery<Integer> orderQuery = entityManager.createQuery(query);
        Integer orderNumber = orderQuery.getSingleResult();
        if (orderNumber == null) {
            return 0;
        }
        return orderNumber;

    }
}
