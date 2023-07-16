<template>
  <div class="cart">
    <div class="container">
      <ul>
        <li v-for="(i, idx) in state.items" :key="idx">
          <img :src="i.imgPath"/>
          <span class="name">{{ i.name }}</span>
          <span class="price"> EUR {{ lib.getNumberFormatted(i.price - i.price * i.discountPer / 100) }}</span>
          <i class="fa fa-trash" @click="remove(i.id)"></i>
        </li>
      </ul>
      <router-link to="/order" class="btn btn-primary">Chat with Seller</router-link>
    </div>
  </div>
</template>

<script>
import {reactive} from "vue";
import axios from "axios";
import lib from "@/script/lib";

export default {
  setup() {
    const state = reactive({
      items: []
    })

    // Load the cart items from the server using an Axios GET request
    const load = () => {
      axios.get("/api/cart/items").then(({data}) => {
        console.log(data);
        state.items = data;
      })
    };

    // Remove a cart item from the server using an Axios DELETE request
    const remove = (itemId) => {
      axios.delete(`/api/cart/items/${itemId}`).then(() => {
        load();
      })
    }

    // Load the cart items when the component is mounted
    load();

    // Return the state object, lib object, and remove function
    return {state, lib, remove}
  }
}
</script>

<style scoped>
.cart ul {
  list-style: none;
  margin: 0;
  padding: 0;
}

.cart ul li {
  border: 1px solid #eee;
  margin-top: 25px;
  margin-bottom: 25px;
}

.cart ul li img {
  width: 150px;
  height: 150px;
}

.cart ul li .name {
  margin-left: 25px;
}

.cart ul li .price {
  margin-left: 25px;
}

.cart ul li i {
  float: right;
  font-size: 20px;
  margin-top: 65px;
  margin-right: 50px;
}

.cart .btn {
  width:300px;
  display:block;
  margin:0 auto;
  padding:30px 50px;
  font-size: 15px;
}
</style>