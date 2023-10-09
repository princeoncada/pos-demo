package service

import model.Product
import repository.ProductRepository
import service.base.EntityServiceImpl

class ProductService(
    productRepository: ProductRepository
): EntityServiceImpl<Product, String>(productRepository) {}