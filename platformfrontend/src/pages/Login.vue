<template>
  <div class="form-signin w-100 m-auto">
    <h1 class="h3 mb-3 fw-normal">Please sign in</h1>
    <div class="form-floating">
      <input type="email" class="form-control" id="floatingInput" placeholder="name@example.com"
             @keyup.enter="submit()" v-model="state.form.email">
      <label for="floatingInput">Email address</label>
    </div>
    <div class="form-floating">
      <input type="password" class="form-control" id="floatingPassword" placeholder="Password" @keyup.enter="submit()" v-model="state.form.password">
      <label for="floatingPassword">Password</label>
    </div>
    <div class="checkbox mb-3">
      <label>
        <input type="checkbox" value="remember-me" @keyup.enter="submit()"> Remember me
      </label>
    </div>
    <div class="button-container">
      <button class="w-50 btn btn-lg text-white btn-warning" @click="submit()" style="font-weight: bold;">Sign in</button>
      <button class="w-50 btn btn-lg text-white btn-success" @click="$router.push('/signUpModal')" style="font-weight: bold;">Sign up</button>
    </div>
  </div>

</template>

<script>
import {reactive} from "vue";
import axios from "axios";
import router from "@/script/router";
import store from "@/script/store";

export default {
  setup() {
    const state = reactive({
      form: {
        email: "",
        password: ""
      }})

    const submit = () => {
      axios.post("/api/account/login", state.form).then((res) => {
        store.commit('setAccount', res.data);
        sessionStorage.setItem("id",res.data);
        // Show the success modal
         router.push({path:"/"});
      }).catch(()=>{
        window.alert("Login failed");
      });
    }


    return {state, submit}

  }
};

</script>

<style scoped>
.form-signin {
  max-width: 330px;
  padding: 15px;
}

.form-signin .form-floating:focus-within {
  z-index: 2
}

.form-signin input[type="email"] {
  margin-bottom: -1px;
  border-bottom-right-radius: 0;
  border-bottom-left-radius: 0;
}

.form-signin input[type="password"] {
  margin-bottom: 10px;
  border-top-left-radius: 0;
  border-top-right-radius: 0;
}
.button-container {
  display: flex;
  gap: 10px;
}

</style>