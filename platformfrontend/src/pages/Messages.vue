<template>
  <div class="messages">
    <div class="container">
      <table class="table table-bordered">
        <thead>
        <tr>
          <th>Seller's item</th>
          <th>Negotiation Price</th>
          <th>Sender ID</th>
          <th>Text</th>
        </tr>
        </thead>
        <tbody>
        <tr v-for="(o, idx1) in state.messages" :key="idx1">
          <td>
            <div v-for="(i, idx2) in o.items" :key="idx2">{{ i.name }}</div>
          </td>
          <td>{{ o.negotiationPrice }}</td>
          <td>{{ o.sender }}</td>
          <td>{{ o.text }}</td>
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
      messages: []
    })

    axios.get("/api/messageHistory").then(({data}) => {
      state.messages = data;

      for (let message of state.messages) {
        if (message.items) {
          message.items = JSON.parse(message.items);
        }
      }
    });

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