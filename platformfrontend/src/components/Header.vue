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
              declutter your life. Our vibrant community of sellers and buyers ensures that every transaction is a
              win-win, fostering a sustainable and circular economy. </p>
          </div>
          <div class="col-sm-4 offset-md-1 py-4">
            <h4 class="text-white">Contact</h4>
            <ul class="list-unstyled">
              <li>
                <router-link to="/" class="text-white">Home</router-link>
              </li>
              <li>
                <router-link to="/login" class="text-white" v-if="$store.state.account.id">Login</router-link>
                <a to="/login" class="text-white" @click="logout()" v-else>Logout</a>
              </li>
            </ul>
          </div>
        </div>
      </div>
    </div>
    <div class="navbar navbar-dark bg-dark shadow-sm">
      <div class="container">
        <a href="#" class="navbar-brand d-flex align-items-center">
          <svg xmlns="http://www.w3.org/2000/svg" width="20" height="20" fill="none" stroke="currentColor"
               stroke-linecap="round" stroke-linejoin="round" stroke-width="2" aria-hidden="true" class="me-2"
               viewBox="0 0 24 24">
            <path d="M23 19a2 2 0 0 1-2 2H3a2 2 0 0 1-2-2V8a2 2 0 0 1 2-2h4l2-3h6l2 3h4a2 2 0 0 1 2 2z"/>
            <circle cx="12" cy="13" r="4"/>
          </svg>
          <strong>Twice but Nice</strong>
        </a>
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

export default {
  name: 'Header',
  setup() {
    const logout = () => {
      store.commit('setAccount', 0);
      router.push({path: "/"});
    }
    return {logout};
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
</style>
