package service

import model.Supplier
import repository.SupplierRepository
import service.base.EntityServiceImpl

class SupplierService(
    supplierRepository: SupplierRepository
): EntityServiceImpl<Supplier, String>(supplierRepository) {}