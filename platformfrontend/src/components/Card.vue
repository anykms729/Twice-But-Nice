<template>
  <div class="card shadow-sm">
    <span class="img" :style="{backgroundImage: `url(${item.imgPath})`}"/>

    <div class="card-body">
      <p class="card-text">{{ item.name }}</p>
      <div class="d-flex justify-content-between align-items-center">
        <button class="btn btn-primary">Purchase</button>
        <small class="text-muted">Euro {{ lib.getNumberFormatted(item.price) }}</small>
      </div>
    </div>
  </div>

  <!--  <div class="col">-->
  <!--    <div class="card shadow-sm">-->
  <!--      <svg class="bd-placeholder-img card-img-top" width="100%" height="225" xmlns="http://www.w3.org/2000/svg" role="img" aria-label="Placeholder: Thumbnail" preserveAspectRatio="xMidYMid slice" focusable="false"><title>Placeholder</title><rect width="100%" height="100%" fill="#55595c"/><text x="50%" y="50%" fill="#eceeef" dy=".3em">Thumbnail</text></svg>-->

  <!--      <div class="card-body">-->
  <!--        <p class="card-text">This is a wider card with supporting text below as a natural lead-in to additional content. This content is a little bit longer.</p>-->
  <!--        <div class="d-flex justify-content-between align-items-center">-->
  <!--          <div class="btn-group">-->
  <!--            <button type="button" class="btn btn-sm btn-outline-secondary">View</button>-->
  <!--            <button type="button" class="btn btn-sm btn-outline-secondary">Edit</button>-->
  <!--          </div>-->
  <!--          <small class="text-muted">9 mins</small>-->
  <!--        </div>-->
  <!--      </div>-->
  <!--    </div>-->
  <!--  </div>-->
</template>

<script>

import lib from "../../../platformbackend/src/scripts/lib";

export default {
  name: "Card",
  props: {
    item: Object
  },

  setup(){
    return {lib}
  }

}
</script>

<style scoped>
.card .img {
  display: inline-block;
  width: 100%;
  height: 250px;
  background-size: cover;
  background-position: center;
}

.card .card-body .price {
  text-decoration: line-through;
}

</style>