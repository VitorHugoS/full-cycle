package org.example.array;

import java.util.ArrayList;
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

  public String reverseArrayToString() {
    StringBuffer reversedString = new StringBuffer();
    for (int index = length; index >= 0; index--) {
      reversedString.append(items[index]);
    }
    return reversedString.toString();
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

  public CustomArray mergeTwoArrays(ArrayList array1, ArrayList array2) {
    CustomArray merged = CustomArray.create();

    int indexOfFirstArray = 0;
    int indexOfSecondArray = 0;
    int index = 0;


    while (array1 || array2) {
      if (array1.get(indexOfFirstArray) == array2.get(indexOfSecondArray)) {
        merged.push(array1.get(indexOfFirstArray));
        merged.push(array2.get(indexOfSecondArray));
        indexOfFirstArray++;
        indexOfSecondArray++;
      }else if (array1.get(indexOfFirstArray) > array2.get(indexOfSecondArray)) {
        merged.push(array2.get(indexOfSecondArray));
        indexOfSecondArray++;
      } else {
        merged.push(array1.get(indexOfFirstArray));
        indexOfFirstArray++;
      }
      index++;
    }
    return merged;
  }

}
