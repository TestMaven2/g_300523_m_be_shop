package de.telran.g_300523_m_be_shop.domain.entity;

import de.telran.g_300523_m_be_shop.domain.interfaces.Cart;
import de.telran.g_300523_m_be_shop.domain.interfaces.Product;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

public class CommonCart implements Cart {
    private UUID id;
    private List<Product> products;

    @Override
    public UUID getId() {
        return id;
    }

    @Override
    public List<Product> getProducts() {
        return products;
    }

    @Override
    public void addProduct(Product product) {
        products.add(product);
    }

    @Override
    public void addProducts(Product... productsToAdd) {
        Collections.addAll(products, productsToAdd);//замена циклу for each - статический метод Collections
    }

    @Override
    public List<Product> getActiveProducts() {
        return products.stream()
                .filter(el -> el.isActive())
                .toList();
    }

    @Override
    public void deleteProductById(UUID uuid) {
        products.removeIf(el -> el.getId().equals(uuid));
    }

    @Override
    public void deleteAllProducts() {
        products.clear();
    }

    @Override
    public double getTotalPrice() {
        return getActiveProducts().stream()
                .mapToDouble(Product::getPrice)
                .sum();
    }

    @Override
    public double getAveragePrice() {
        return getActiveProducts().stream()
                .mapToDouble(Product::getPrice)
                .average().orElse(0);//метод обьекта Optional, если в корзине будут продукты, то вернет среднюю стоимость, иначе вернет 0.
    }
}
