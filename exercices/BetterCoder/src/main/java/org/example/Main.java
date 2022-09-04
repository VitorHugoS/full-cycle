package org.example;

import org.example.array.CustomArray;

public class Main {

  public static void main(String[] args) {

    CustomArray teste = CustomArray.create();
    teste.push(10);
    teste.push(11);
    teste.push(12);
    teste.push(13);
    teste.push(14);
    teste.push(15);
    teste.push(16);
    teste.push(17);
    teste.push(18);
    teste.push(19);
    teste.push(20);
    teste.push(21);

    teste.delete(11);

    System.out.println(teste);
  }
}