<template>
  <div class="orders">
    <div class="container">
      <table class="table table-bordered">
        <thead>
        <tr>
          <th>Number</th>
          <th>Customer name</th>
          <th>Address</th>
          <th>Payment method</th>
          <th>Ordered item</th>
        </tr>
        </thead>
        <tbody>
        <tr v-for="(o, idx1) in state.orders" :key="idx1">
          <td>{{ state.orders.length - idx1 }}</td>
          <td>{{ o.name }}</td>
          <td>{{ o.address }}</td>
          <td>{{ o.payment }}</td>
          <td>
            <div v-for="(i, idx2) in d.items" :key="idx2">{{ i.name }}</div>
          </td>
        </tr>
        </tbody>
      </table>
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
      orders: [],
    })

    axios.get("/api/orders").then(({data}) => {
      console.log(data);
      state.orders = data;

      for (let d of data) {
        if (d.items) {
          d.items = JSON.parse(d.items);
        }

        state.orders.push(d);
      }
    })

    return {state, lib}
  }
}
</script>

<style scoped>
.table {
  margin-top: 30px;
}

.table > tbody {
  border-top: 1px solid #eee;
}
</style>