package org.example.array;

import java.util.Arrays;

public class CustomArray {

  int length = 0;
  int[] items;

  protected CustomArray(int length, int[] items) {
    this.length = length;
    this.items = items;
  }

  public static CustomArray create() {
    int[] emptyArray = new int[5];
    return new CustomArray(0, emptyArray);
  }

  private void growthAllocationMemory() {
    items = Arrays.copyOf(items, Math.abs(length + (length / 2)));
  }

  public int get(int index) {
    return items[index];
  }

  public void push(int item) {
    if (length == items.length) {
      growthAllocationMemory();
    }
    items[length] = item;
    length++;
  }

  public void delete(int index) {
    for (int position = index; position <= length - 1; position++) {
      items[index] = items[index + 1];
    }
    length--;
  }

}
