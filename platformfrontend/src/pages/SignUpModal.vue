<template>
  <div>
    <h1></h1>
    <!-- Modal -->
    <div class="modal fade" id="ModalCenter" tabindex="-1" role="dialog" aria-labelledby="ModalCenterTitle" aria-hidden="true">
      <div class="modal-dialog modal-dialog-centered" role="document">
        <div class="modal-content">
          <div class="modal-header">
            <h5 class="modal-title" id="createAccountTitle">Create Account</h5>
          </div>

          <div class="modal-body">
            <form @submit.prevent="createAccount">
              <div class="form-group">
                <label for="accountEmail">Email:</label>
                <input type="text" id="accountEmail" v-model="account.email" required class="form-control">
              </div>
              <div class="form-group">
                <label for="password">Password:</label>
                <input type="text" id="password" v-model="account.password" class="form-control">
              </div>
              <div class="modal-footer">
                <button type="button" class="btn btn-success" data-dismiss="modal" style="font-weight: bold;" @click="closeModalAndRedirect">Close</button>
                <button type="submit" class="btn btn-warning text-white" style="font-weight: bold;" >Submit</button>
              </div>
              <div v-if="successMessage" class="alert alert-success" role="alert">
                {{ successMessage }}
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
      account: {
        email: "",
        password: "",
      },
      errorMessage: '',
      successMessage:''
    };
  },
  methods: {
    createAccount() {
      axios
          .post("/api/account/signup", this.account)
          .then(() => {
            this.successMessage = "Account creation successful!";
            // Reset the form fields
            this.account = {
              name: "",
              imgPath: "",
            };
            // Close the modal and redirect to home
            setTimeout(() => {
              this.closeModalAndRedirect();
            }, 2000);
          })
          .catch(error => {
            // Error occurred while adding the item
            if (error.response && error.response.status === 409) {
              // Unauthorized (401) - User is not logged in, show a pop-up message
              this.errorMessage = "Entered email already exists.";
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
