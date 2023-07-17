<template>
  <div class="home">
    <button class="btn btn-primary btn-black" @click="showAddForm = true">+ Add item</button>
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
    <Modal v-if="showAddForm" @close="showAddForm = false">
      <AddForm @add-item="addItem"/>
    </Modal>
  </div>
</template>

<script>
import axios from "axios";
import {reactive, ref} from "vue";
import Card from "@/components/Card";
import AddForm from "@/components/AddForm.vue";
import Modal from "@/components/Modal.vue";

export default {
  name: "Home",
  components: {Card, AddForm, Modal},
  setup() {
    const state = reactive({
      items: []
    });

    const showAddForm = ref(false);

    const addItem = (item) => {
      axios
          .post("/api/items", item)
          .then((response) => {
            console.log("Item added successfully:", response.data);
          })
          .catch((error) => {
            console.error("Error adding item:", error);
          });
    };

    axios.get("/api/items").then(({data}) => {
      state.items = data;
    });

    return {state, showAddForm, addItem}
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