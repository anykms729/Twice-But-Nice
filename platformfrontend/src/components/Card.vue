<template>

  <div class="card shadow-sm" >
    <span class="img" :style="{backgroundImage: `url(${item.imgPath})`}"/>
    <div class="card-body">
      <p class="card-text">
        <span>{{ item.name }} &nbsp;</span>
        <span class="discount badge bg-danger">
          {{ item.discountPer }}%
        </span>
      </p>
      <div class="d-flex justify-content-between align-items-center">
        <button class="btn btn-warning" @click="addToCart(item.id)">
          <i class="fa fa-shopping-cart" aria-hidden="true"></i>
        </button>
        <small class="price text-muted">
          EUR {{ lib.getNumberFormatted(item.price) }}
        </small>
        <small class="real text-danger">
          EUR {{ lib.getNumberFormatted(item.price - (item.price * item.discountPer / 100)) }}
        </small>
      </div>

      <!-- Modal -->
      <div class="modal" :class="{ 'show': showModal }">
        <div class="modal-dialog">
          <div class="modal-content">
            <div class="modal-header">
              <h5 class="modal-title">Success Message</h5>
            </div>
            <div class="modal-body">
              The item has been added to your cart successfully!
            </div>
            <div class="modal-footer">
              <button type="button" class="btn btn-success" @click="showModal = false">
                Okay
              </button>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>


import axios from "axios";
import lib from "@/script/lib";
export default {
  name: "Card",
  props: {
    item: Object
  },
  setup() {
    // Your existing setup() code here
    return { lib };
  }
  ,data() {
    return {
      showModal: false,
    };
  },
  methods: {
    addToCart(itemId) {
      axios
          .post(`/api/cart/items/${itemId}`)
          .then(() => {
            // Show the modal when the item is added to the cart
            this.showModal = true;
          })
          .catch((error) => {
            console.error("Error adding item to cart:", error);
          });
    },
  },
};
</script>

<style scoped>
.card .img {
  display: inline-block;
  width: 100%;
  height: 250px;
  background-size: cover;
  background-position: center;
}

.card .card-body .price {
  text-decoration: line-through;
}
.modal {
  position: fixed;
  top: 0;
  left: 0;
  right: 0;
  bottom: 0;
  display: none;
  align-items: center;
  justify-content: center;
 }

.modal.show {
  display: flex;
}
.modal-content {
  border: none;
  border-radius: 8px;
}

.modal-header {
  border-bottom: none;
}

.modal-footer {
  border-top: none;
}

.modal-dialog {
  padding: 20px;
  border-radius: 6px;
  width: 100%;
  max-width: 450px;
}

.modal-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
  padding-bottom: 10px;
}

.modal-title {
  font-size: 20px;
  font-weight: bold;
}

.modal-body {
  padding: 10px 13px;
}

.modal-footer {
  text-align: right;
  margin-top: 10px;
}

</style>