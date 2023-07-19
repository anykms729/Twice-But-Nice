<template>
  <div class="home bg-light">
    <div class="container">
      <div class="buttons-container">
        <button class="btn btn-warning btn-black text-white" @click="$router.push('/addItem')">
          <i class="fa fa-car"></i>
          <span style="font-weight: bold;"> Add item</span>
        </button>
        <button class="btn btn-success btn-black text-white" @click="$router.push('/deleteItem')">
          <i class="fa fa-scissors"></i>
          <span style="font-weight: bold;"> Delete item</span>
        </button>
      </div>
    </div>
    <div class="album py-5 bg-light">
      <div class="container">
        <div class="row row-cols-1 row-cols-sm-2 row-cols-md-3 g-3">
          <div class="col" v-for="(item, idx) in state.items" :key="idx">
            <!--Dynamically bind one or more attributes, or a component prop to an expression.-->
            <Card :item="item"/>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import axios from "axios";
import {reactive} from "vue";
import Card from "@/components/Card";


export default {
  name: "Home",
  components: {Card},
  setup() {
    const state = reactive({
      items: []
    });

    axios.get("/api/items").then(({data}) => {
      state.items = data;
    });

    return {state}
  }
}
</script>



<style scoped>


.buttons-container {
  display: flex;
  justify-content: flex-end;
}

.btn {
  margin-top:17px;
  margin-left: 10px;
  font-size: 14px;
  padding: 5px 10px;
}

.btn:hover {
  transform: scale(1.1);
}

</style>