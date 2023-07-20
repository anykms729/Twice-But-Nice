<template>
  <header>
    <div class="collapse bg-dark" id="navbarHeader">
      <div class="container">
        <div class="row">
          <div class="col-sm-8 col-md-7 py-4">
            <h4 class="text-white">About</h4>
            <p class="text-muted">
              Welcome to Twice but Nice, Ireland's premier C2C second-hand platform! We believe in unlocking the hidden
              value in pre-loved items, empowering individuals to buy and sell with ease. With a click of a button, you
              can explore a world of remarkable deals, whether you're hunting for unique treasures or looking to
              clutter your life. Our vibrant community of sellers and buyers ensures that every transaction is a
              win-win, fostering a sustainable and circular economy. </p>
          </div>
          <div class="col-sm-4 offset-md-1 py-4">
            <h4 class="text-white">Category</h4>
            <ul class="list-unstyled">
              <li>
                <router-link to="/" class="text-white" style="text-decoration: none;">Home</router-link>
              </li>
              <li v-if="$store.state.account.id">
                <router-link to="/orders" class="text-white" style="text-decoration: none;">Order History</router-link>
              </li>
              <li>
                <router-link to="/login" class="text-white" v-if="!$store.state.account.id" style="text-decoration: none;">Login</router-link>
                <a to="/login" class="text-white" style="text-decoration: none;" @click="logout()" v-else>Logout</a>
              </li>
            </ul>
          </div>
        </div>
      </div>
    </div>
    <div class="navbar navbar-dark bg-success shadow-sm">
      <div class="container">
        <a href="#" class="navbar-brand d-flex align-items-center">
          <i class="fa fa-envira text-white" style="font-size:30px;"></i>
          <strong @click.prevent="$router.push('/')"> Twice but Nice</strong>
        </a>
        <router-link to="/cart" class="cart btn">
          <i class="fa fa-shopping-cart" aria-hidden="true" style="font-size:23px"></i>
        </router-link>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarHeader"
                aria-controls="navbarHeader" aria-expanded="false" aria-label="Toggle navigation">
          <span class="navbar-toggler-icon"></span>
        </button>
      </div>
    </div>
  </header>
</template>

<script>
import router from "@/script/router";
import store from "@/script/store";
import axios from "axios";

export default {
  name: 'Header',
  setup() {
    const logout = () => {
      axios.post("api/account/logout").then(()=> {
        store.commit('setAccount', 0);
        router.push({path: "/"});
      })
    }
    return {logout};
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
header .navbar .cart {
  margin-left: auto;
  color: #fff;
}
</style>
