import { app, sequelize } from "../express";
import request from "supertest";

describe("E2E test for product", () => {

   beforeEach(async () => {
      await sequelize.sync({ force: true });
   });

   afterAll( async() => {
      await sequelize.close();
   });

   it("should create a product", async () => { 
      const response =
         await request(app)
         .post("/product")
         .send({
            type: "a",
            name: "teste",
            price: 1000,
         });
      
      expect(response.status).toBe(200);
   });

   it("should not create a product", async () => { 
      const response =
         await request(app)
         .post("/product")
         .send({
            name: "teste",
         });
      
      expect(response.status).toBe(500);

   });

   it("should list products", async () => { 
      const product = await request(app)
         .post("/product")
         .send({
            type: "a",
            name: "teste",
            price: 1000,
         });
      expect(product.status).toBe(200);

      const product2 = await request(app)
         .post("/product")
         .send({
            type: "a",
            name: "teste",
            price: 2000,
         });
      expect(product2.status).toBe(200);
      
      const response = await request(app)
         .get("/product")
         .send();

      expect(response.status).toBe(200);
      expect(response.body.products.length).toBe(2);

   });
});