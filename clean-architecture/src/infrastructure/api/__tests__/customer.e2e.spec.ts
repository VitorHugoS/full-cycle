import { app, sequelize } from "../express";
import request from "supertest";

describe("E2E test for customer", () => {

   beforeEach(async () => {
      await sequelize.sync({ force: true });
   });

   afterAll( async() => {
      await sequelize.close();
   });

   it("should create a customer", async () => { 
      const response =
         await request(app)
         .post("/customer")
         .send({
            name: "Jhon",
            address: {
               street: "rua",
               city: "cidade",
               number: 1234,
               zip: "123456"
            },
         });
      
      expect(response.status).toBe(200);
      expect(response.body.name).toBe("Jhon");
      expect(response.body.address.street).toBe("rua");
      expect(response.body.address.city).toBe("cidade");
      expect(response.body.address.number).toBe(1234);
      expect(response.body.address.zip).toBe("123456");

   });

   it("should not create a customer", async () => { 
      const response =
         await request(app)
         .post("/customer")
         .send({
            name: "Jhon",
         });
      
      expect(response.status).toBe(500);

   });

   it("should list customers", async () => { 
      const customer = await request(app)
         .post("/customer")
         .send({
            name: "Jhon",
            address: {
               street: "rua",
               city: "cidade",
               number: 1234,
               zip: "123456"
            },
         });
      expect(customer.status).toBe(200);

      const customer2 = await request(app)
         .post("/customer")
         .send({
            name: "Jhon 2",
            address: {
               street: "rua 2",
               city: "cidade 2",
               number: 12345,
               zip: "1234567"
            },
         });
      expect(customer2.status).toBe(200);
      
      const response = await request(app)
         .get("/customer")
         .send();
      
      expect(response.status).toBe(200);
      expect(response.body.customers.length).toBe(2);

   });
});