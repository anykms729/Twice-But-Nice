<template>
  <div>
    <h1>Add Item</h1>
    <form @submit.prevent="addItem">
      <div>
        <label for="name">Name:</label>
        <input type="text" id="name" v-model="item.name" required>
      </div>
      <div>
        <label for="imgPath">Image Path:</label>
        <input type="text" id="imgPath" v-model="item.imgPath">
      </div>
      <div>
        <label for="price">Price:</label>
        <input type="number" id="price" v-model="item.price" required>
      </div>
      <div>
        <label for="discountPer">Discount Percentage:</label>
        <input type="number" id="discountPer" v-model="item.discountPer" required>
      </div>
      <button type="submit">Add Item</button>
    </form>
  </div>
</template>

<script>
import axios from "axios";

export default {
  data() {
    return {
      item: {
        name: "",
        imgPath: "",
        price: 0,
        discountPer: 0
      }
    };
  },
  methods: {
    addItem() {
      axios
          .post("/api/items", this.item)
          .then(response => {
            console.log("Item added successfully:", response.data);
            // Reset the form fields
            this.item = {
              name: "",
              imgPath: "",
              price: 0,
              discountPer: 0
            };
          })
          .catch(error => {
            console.error("Error adding item:", error);
          });
    }
  }
};
</script>
