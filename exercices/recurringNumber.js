const numbers = [1, 5, 2]; 

const firstRecurringNumbers = (list) => { 
  const exists = new Set();
  for (let i = 0; i < list.length; i++) { 
    if (exists.has(list[i])) {
      return list[i];
    }
    exists.add(list[i]);
  }
  return undefined;
}

console.log(firstRecurringNumbers(numbers));
