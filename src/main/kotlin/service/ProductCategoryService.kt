package service

import model.ProductCategory
import repository.ProductCategoryRepository
import service.base.EntityServiceImpl

class ProductCategoryService(
    productCategoryRepository: ProductCategoryRepository
): EntityServiceImpl<ProductCategory, String>(productCategoryRepository) {}