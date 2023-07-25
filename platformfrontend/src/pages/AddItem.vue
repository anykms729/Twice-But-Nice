<template>
  <div>
    <h1></h1>
    <!-- Modal -->
    <div class="modal fade" id="ModalCenter" tabindex="-1" role="dialog" aria-labelledby="ModalCenterTitle" aria-hidden="true">
      <div class="modal-dialog modal-dialog-centered" role="document">
        <div class="modal-content">
          <div class="modal-header">
            <h5 class="modal-title" id="addItemTitle">Add Item</h5>
          </div>
          <div class="modal-body">
            <form @submit.prevent="addItem">
              <div class="form-group">
                <label for="name">Name:</label>
                <input type="text" id="name" v-model="item.name" required class="form-control">
              </div>
              <div class="form-group">
                <label for="imgPath">Image Path:</label>
                <input type="text" id="imgPath" v-model="item.imgPath" class="form-control">
              </div>
              <div class="form-group">
                <label for="price">Price:</label>
                <input type="number" id="price" v-model="item.price" required class="form-control">
              </div>
              <div class="form-group">
                <label for="discountPer">Discount Percentage:</label>
                <input type="number" id="discountPer" v-model="item.discountPer" required class="form-control">
              </div>
              <div class="modal-footer">
                <button type="button" class="btn btn-success" data-dismiss="modal" style="font-weight: bold;" @click="closeModalAndRedirect">Close</button>
                <button type="submit" class="btn btn-warning text-white" style="font-weight: bold;" >Add Item</button>
              </div>
              <div v-if="errorMessage" class="alert alert-danger" role="alert">
                {{ errorMessage }}
              </div>
            </form>
          </div>
        </div>
      </div>
    </div>
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
      },
      defaultImage: "/img/elephant-g7baf1f085_640.png",
      errorMessage: '', 
    };
  },
  methods: {
    addItem() {
      if (this.item.imgPath === "") {
        this.item.imgPath = this.defaultImage;
      }
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
            // Close the modal and redirect to home
            this.closeModalAndRedirect();
          })
          .catch(error => {
            // Error occurred while adding the item
            if (error.response && error.response.status === 401) {
              // Unauthorized (401) - User is not logged in, show a pop-up message
              this.errorMessage = "Please log in to add an item.";
              // You can also display a custom styled modal instead of the standard alert.
            } else {
              this.errorMessage ="There's something wrong, contact with platform admin";
            }
          });
    },
    closeModal() {
      const modalElement = document.getElementById("ModalCenter");
      modalElement.classList.remove("show");
      modalElement.style.display = "none";
      const modalBackdropElement = document.getElementsByClassName("modal-backdrop")[0];
      modalBackdropElement.parentNode.removeChild(modalBackdropElement);
    },
    closeModalAndRedirect() {
      this.closeModal();
      this.$router.push('/');
    }
  },
  mounted() {
    // Open the modal when the component is mounted
    const modalElement = document.getElementById("ModalCenter");
    modalElement.style.display = "block";
    modalElement.classList.add("show");
    modalElement.style.paddingRight = "15px";
    document.body.classList.add("modal-open");
    const modalBackdropElement = document.createElement("div");
    modalBackdropElement.classList.add("modal-backdrop");
    modalBackdropElement.classList.add("fade");
    modalBackdropElement.classList.add("show");
    document.body.appendChild(modalBackdropElement);
  }
};
</script>

<style scoped>
.form-group {
  margin-bottom: 20px;
}

label {
  font-weight: bold;
}

.form-control {
  width: 100%;
  padding: 10px;
  border-radius: 4px;
  border: 1px solid #ccc;
}
</style>
