<template>
  <div>
    <h1></h1>
    <!-- Modal -->
    <div class="modal fade" id="exampleModalCenter" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
      <div class="modal-dialog modal-dialog-centered" role="document">
        <div class="modal-content">
          <div class="modal-header">
            <h5 class="modal-title" id="exampleModalLongTitle">Add Item</h5>

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
                <button type="button" class="btn btn-secondary" data-dismiss="modal" @click="closeModalAndRedirect">Close</button>
                <button type="submit" class="btn btn-primary" >Add Item</button>
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
            // Close the modal and redirect to home
            this.closeModalAndRedirect();
          })
          .catch(error => {
            console.error("Error adding item:", error);
          });
    },
    closeModal() {
      const modalElement = document.getElementById("exampleModalCenter");
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
    const modalElement = document.getElementById("exampleModalCenter");
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
/* Additional CSS styling for the form */
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

.btn-secondary {
  background-color: #6c757d;
  border-color: #6c757d;
}

.btn-primary {
  background-color: #007bff;
  border-color: #007bff;
}

.btn-primary:hover {
  background-color: #0069d9;
  border-color: #0069d9;
}
</style>
