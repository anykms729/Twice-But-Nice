<template>
  <div class="send-message-modal">
    <div class="modal-dialog">
      <div class="modal-content">
        <div class="modal-header">
          <h5 class="modal-title">Send Message to Seller</h5>
          <button type="button" class="btn-close" @click="closeModal"></button>
        </div>
        <div class="modal-body">
          <!-- Display the item list section from cart.vue -->
          <div class="col-md-1 col-lg-10 order-md-last">
            <h4 class="d-flex justify-content-between align-items-center mb-3">
              <span class="text-success">Cart</span>
              <span class="badge bg-success rounded-pill">{{ state.items.length }}</span>
            </h4>
            <ul class="list-group mb-3">
              <li class="list-group-item d-flex justify-content-between lh-sm" v-for="(i, idx) in state.items" :key="idx">
                <div>
                  <h6 class="my-0">{{ i.name }}</h6>
                </div>
                <span class="text-muted">EUR {{ lib.getNumberFormatted(i.price - i.price * i.discountPer / 100) }}</span>
              </li>
            </ul>
            <h3 class="text-center total-price">EUR {{ lib.getNumberFormatted(computedPrice) }}</h3>
          </div>

          <!-- Add input section for negotiation price -->
          <div class="form-group">
            <label for="negotiationPrice">Negotiation Price:</label>
            <input
                type="number"
                id="negotiationPrice"
                v-model="state.form.negotiationPrice"
                class="form-control"
                placeholder="Enter your negotiation price"
            />
          </div>

          <!-- Add input section for message to seller -->
          <div class="form-group">
            <label for="messageToSeller">Message to Seller:</label>
            <textarea
                id="messageToSeller"
                v-model="state.form.text"
                class="form-control"
                placeholder="Type your message to the seller..."
            ></textarea>
          </div>
        </div>
        <div class="modal-footer">
          <button type="button" class="btn btn-success text-white" style="font-weight: bold;" @click="closeModal">Cancel</button>
          <button type="button" class="btn btn-warning text-white" style="font-weight: bold;" @click="submit()">Send Message</button>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import lib from "../script/lib";
import axios from "axios";
import { computed, reactive } from "vue";
import router from "@/script/router";

export default {
  setup() {
    const state = reactive({
      items: [],
      form: {
        negotiationPrice: "",
        text: ""
      }
    });

    const load = () => {
      axios.get("/api/cart/items").then(({ data }) => {
        state.items = data;
      });
    };
    const computedPrice = computed(() => {
      let result = 0;

      for (let i of state.items) {
        result += i.price - i.price * i.discountPer / 100;
      }

      return result;
    });

    const submit = () => {
      const args = JSON.parse(JSON.stringify(state.form));
      args.items = JSON.stringify(state.items);
      console.log(args);
      axios.post("/api/messages", args).then(() => {
        router.push({path: "/messages"})
      })
    }
    load();

    return { state, lib, computedPrice,submit };
  },

  methods: {
    closeModal() {
      this.$router.push('/messages');
    }
  },
};
</script>

<style scoped>
.send-message-modal {
  position: fixed;
  top: 0;
  left: 0;
  right: 0;
  bottom: 0;
  display: flex;
  align-items: center;
  justify-content: center;
  background-color: rgba(0, 0, 0, 0.5);
}


.modal-content {
  padding: 20px;
}

.modal-body textarea {
  width: 100%;
   resize: none;
}

/* Add some styles to the item list section */

ul.list-group {
  margin-bottom: 0;
}
.total-price {
  margin-top: 20px;
  font-size: 1.5rem;
  font-weight: bold;
}
/* Media query for smaller screens */
@media (max-width: 900px) {
  .modal-dialog {
    max-width: 400px; /* Adjust the width for smaller screens */
  }

  .total-price {
    font-size: 1.2rem; /* Adjust the font size for smaller screens */
  }
}

</style>
