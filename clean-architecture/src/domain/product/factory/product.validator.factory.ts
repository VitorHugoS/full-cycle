import ValidatorInterface from "../../@shared/validator/validator.interface";
import ProductBYupValidator from "../../customer/validator/product-b.yup.validator";
import ProductYupValidator from "../../customer/validator/product.yup.validator";
import Product from "../entity/product";
import ProductB from "../entity/product-b";

export default class ProductValidatorFactory {
  static create(): ValidatorInterface<Product> {
    return new ProductYupValidator();
  }

  static createB(): ValidatorInterface<ProductB> {
    return new ProductBYupValidator();
  }
}
