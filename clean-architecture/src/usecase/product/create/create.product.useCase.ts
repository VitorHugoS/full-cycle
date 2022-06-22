import Product from "../../../domain/product/entity/product";
import ProductInterface from "../../../domain/product/entity/product.interface";
import ProductFactory from "../../../domain/product/factory/product.factory";
import ProductRepositoryInterface from "../../../domain/product/repository/product-repository.interface";
import ProductModel from "../../../infrastructure/product/repository/sequelize/product.model";
import { InputCreateProductDto, OutputCreateProductDto } from "./create.product.dto";

export default class CreateProductUseCase {
   private productRepository: ProductRepositoryInterface;

   constructor(productRepository: ProductRepositoryInterface) {
      this.productRepository = productRepository;
   }
   
   async execute(input: InputCreateProductDto): Promise<OutputCreateProductDto> {
      const product = ProductFactory.create(input.type, input.name, input.price);
      this.productRepository.create(InputMapper.toDatabase(product));
      return {
         id: product.id,
         name: product.name,
         price: product.price
      };
   }
}

class InputMapper {
   static toDatabase(product: ProductInterface): Product {
      return new Product(product.id, product.name, product.price);
   }
}