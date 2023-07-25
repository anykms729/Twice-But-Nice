<template>
  <div>
    <h1></h1>
    <!-- Modal -->
    <div class="modal fade" id="exampleModalCenter" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
      <div class="modal-dialog modal-dialog-centered" role="document">
        <div class="modal-content">
          <div class="modal-header">
            <h5 class="modal-title" id="exampleModalLongTitle">Delete Item</h5>
          </div>
          <div class="modal-body">
            <form @submit.prevent="deleteItem">
              <div class="form-group">
                <label for="itemId">Item ID:</label>
                <input type="text" id="itemId" v-model="item.itemId" required class="form-control">
              </div>
              <div class="form-group">
                <label for="itemName">Item Name:</label>
                <input type="text" id="itemName" v-model="item.itemName" required class="form-control">
              </div>
              <div class="modal-footer">
                <button type="button" class="btn btn-success" data-dismiss="modal" style="font-weight: bold;" @click="closeModalAndRedirect">Close</button>
                <button type="submit" class="btn btn-warning text-white" style="font-weight: bold;">Delete Item</button>
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
        itemId: "",
        itemName: ""
      },
      errorMessage: '', // Add this property to store the error message
    };
  },
  methods: {
    deleteItem() {
      const { itemId, itemName } = this.item;
      // Send API request to delete the item using the provided ID and name
      axios
          .delete(`/api/items/${itemId}`, {
            data: {
              itemName: itemName
            }
          })
          .then(response => {
            console.log("Item deleted successfully:", response.data);
            // Reset the form fields
            this.item = {
              itemId: "",
              itemName: ""
            };
            // Close the modal and redirect to home
            this.closeModalAndRedirect();
          })
          .catch(error => {
            // Error occurred while adding the item
            if (error.response && error.response.status === 401) {
              // Unauthorized (401) - User is not logged in, show a pop-up message
              this.errorMessage = "Please log in to delete an item.";
              // You can also display a custom styled modal instead of the standard alert.
            } else {
              this.errorMessage ="There's something wrong, contact with platform admin";
            }
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

</style>
