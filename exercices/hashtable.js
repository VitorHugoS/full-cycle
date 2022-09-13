class HashTable {
  constructor(size){
    this.data = new Array(size);
  }
  set(key, value) {
    const hash = this._hash(key);
    if (!this.data[hash]) {
      this.data[hash] = []
    }
    this.data[hash].push([key, value])
  }
  get(key){
    const hash = this._hash(key);
    const bucket = this.data[hash];
    if (bucket) {
      for (let i=0; i<bucket.length; i++) {
        if (bucket[i][0] === key) {
          return bucket[i][1];
        }
      }
    }
    return undefined;
  }
  _hash(key) {
    let hash = 0;
    for (let i =0; i < key.length; i++){
        hash = (hash + key.charCodeAt(i) * i) % this.data.length
    }
    return hash;
  }
   
   keys() { 
      const keysArrays = [];
      for (let i = 0; i < this.data.length; i++) { 
         if (this.data[i]) { 
            keysArrays.push(this.data[i][0]);
         }
      }
      return keysArrays;
   }
}

const myHashTable = new HashTable(3);
myHashTable.set('grapes', 10000)
myHashTable.get('grapes')
myHashTable.set('apples', 9)
myHashTable.get('apples')

console.table(myHashTable);
console.log(myHashTable.get('apples'));

console.table(myHashTable.keys())