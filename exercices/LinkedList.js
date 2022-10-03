class LinkedList {
   constructor(value) { 
      this.head = {
         value: value,
         next: null
      }
      this.tail = this.head;
      this.length = 1;
   }
   append(value) { 
      const node = this.node(value);
      this.tail.next = node;
      this.tail = node;
      this.length++;
      return this;
   }
   prepend(value) {
      const node = this.node(value);
      node.next = this.head;
      this.head = node;
      this.length++;
      return this;
   }
   print() { 
      const printList = [];
      let currentNode = this.head;
      while (currentNode !== null) {
         printList.push(currentNode.value);
         currentNode = currentNode.next;
      }
      console.log(printList);
   }
   insert(index, value) { 
      const undefinedIndex = index >= this.length;
      if (undefinedIndex) return this.append(value);

      const findIndex = index - 1;
      if (findIndex < 0) {
         this.prepend(value);
         this.print();
         return;
      }
      
      const newNode = this.node(value);

      const leader = this.traverseToIndex(findIndex);
      newNode.next = leader.next;
      leader.next = newNode;
      this.length++;
      this.print();
   }
   delete(index) {
      const undefinedIndex = index >= this.length;
      if (undefinedIndex) return;

      const findIndex = index - 1;
      if (findIndex < 0) {
         const second = this.head.next;
         this.head = second;
         return;
      }
      
      const leader = this.traverseToIndex(findIndex);
      const node = leader.next.next;
      leader.next = node;
      this.length--;
      this.print();
   }
   traverseToIndex(index) { 
      let counter = 0;
      let currentNode = this.head;
      while (counter !== index) { 
         currentNode = currentNode.next;
         counter++;
      }
      return currentNode;
   }
   node(value) { 
      return {
         value: value,
         next: null
      };
   }
}

const tail = new LinkedList(1).append(12).append(16).append(15).prepend(9);
tail.print();
tail.insert(0, 10);
tail.delete(2);
tail.print();






