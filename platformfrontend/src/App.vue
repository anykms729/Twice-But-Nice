<!--
In App.vue, I can define the layout and structure of application, including the header, footer, and main content areas.
I can also define the global styles and scripts that will be used throughout the application.
-->

<template>
   <Header/>
<!-- The <router-view> component is a functional component that renders the matched component for the given path. -->

  <RouterView/>
  <Footer/>
 </template>

<script>

import Header from "@/components/Header.vue";
import Footer from "@/components/Footer.vue";
// The store import is used to access the Vuex store instance, which is used for managing the state of the application.
import store from "@/script/store";
// The axios import is used to make HTTP requests to the server or API.
import axios from "axios";
// The watch import is used to watch for changes to a reactive property in the Vue.js component.
import {watch} from "vue";
// The useRoute import is used to access the current route object in the Vue.js component.
import {useRoute} from 'vue-router';


export default {
  name: 'App',
  components: {
    Footer,
    Header
  },
  setup: function () {
    const check = () => {
      axios.get("/api/account/check").then(({data}) => {
        console.log(data);
        // The store.commit method is used to call a mutation named setAccount and pass it the data object as an argument.
        // The data object is typically the response data from an HTTP request made using the Axios library.
        store.commit("setAccount", data || 0);
      })
    };
    const route = useRoute();

    watch(route, () => {
      check();
    })

  }

}
</script>

<style>

.bd-placeholder-img {
  font-size: 1.125rem;
  text-anchor: middle;
  -webkit-user-select: none;
  -moz-user-select: none;
  user-select: none;
}

@media (min-width: 768px) {
  .bd-placeholder-img-lg {
    font-size: 3.5rem;
  }
}

.b-example-divider {
  height: 3rem;
  background-color: rgba(0, 0, 0, .1);
  border: solid rgba(0, 0, 0, .15);
  border-width: 1px 0;
  box-shadow: inset 0 .5em 1.5em rgba(0, 0, 0, .1), inset 0 .125em .5em rgba(0, 0, 0, .15);
}

.b-example-vr {
  flex-shrink: 0;
  width: 1.5rem;
  height: 100vh;
}

.bi {
  vertical-align: -.125em;
  fill: currentColor;
}

.nav-scroller {
  position: relative;
  z-index: 2;
  height: 2.75rem;
  overflow-y: hidden;
}

.nav-scroller .nav {
  display: flex;
  flex-wrap: nowrap;
  padding-bottom: 1rem;
  margin-top: -1px;
  overflow-x: auto;
  text-align: center;
  white-space: nowrap;
  -webkit-overflow-scrolling: touch;
}
</style>
