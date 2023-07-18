<template>
  <div class="home">
     <router-link to="/addItem" class="text-black">+ Add item</router-link>

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

    // const addItem = () => {
    //   axios.post("/api/items", item).then((res) => {
    //     console.log("Item added successfully:", res.data);
    //     router.push({path:"/"});
    //     window.alert(res.data);
    //   }).catch((error)=>{
    //     window.alert(error);
    //   });
    // }



    axios.get("/api/items").then(({data}) => {
      state.items = data;
    });

    return {state}
  }
}
</script>



<style scoped>
.btn {
  float: right;
  margin-top: 10px;
  margin-right: 10px;
  font-size: 14px;
  padding: 5px 10px;

}

</style>