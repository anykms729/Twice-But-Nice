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
            <div class="modal-body d-flex flex-column justify-content-between">
              <!-- Container div to center the icon and message -->
              <div style="display: flex; flex-direction: column; align-items: center;">
                <i class="fa fa-check-square" style="font-size: 40px; font-weight: bold;"></i>
                <p style="text-align: center; font-weight: bold; font-size: 20px;">The item has been added to your cart successfully!</p>
              </div>
              <!-- Add a spacer to push the button to the bottom -->
              <div style="flex: 1;"></div>
              <button type="button" class="btn btn-success text-white" @click="showModal = false" style="font-weight: bold; margin-left: auto;">
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
  border-style: dot-dot-dash;
  border-width: thick;
  border-color: #198754;
  border-radius: 10px;
}

.modal-dialog {
  width: 100%;
  max-width: 300px;
}

.fa-check-square {
  color: #198754; /* Green color for the check mark */
}

</style>