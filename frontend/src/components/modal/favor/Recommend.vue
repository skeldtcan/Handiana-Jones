<template>
  <div id="app">
    <vue-glide v-if="heritages.length">
      <vue-glide-slide
        v-for="(heritage, idx) in heritages"
        :key="idx">
        <RecommendHeritage
          :heritage="heritage"
        />
      </vue-glide-slide>
      <template slot="control">
        <button class = "leftbutton" data-glide-dir="<"><i class="fas fa-arrow-alt-circle-left"></i></button>
        <button class = "rightbutton" data-glide-dir=">"><i class="fas fa-arrow-alt-circle-right"></i></button>
      </template>
    </vue-glide>
  </div>
</template>

<script>
import { Glide, GlideSlide } from 'vue-glide-js';
import { recommends } from "@/api/favor.js";
import { mapState } from "vuex";
import  RecommendHeritage  from '@/components/modal/favor/RecommendHeritage';

export default {
  name: "Recommend",
  data: function () {
    return {
      heritages: [],
    }
  },
  components: {
    [Glide.name]: Glide,
    [GlideSlide.name]: GlideSlide,
    RecommendHeritage,
  },
  computed: {
    ...mapState(["userInfo", "isLogin"])
  },
  created() {
    recommends (
      this.userInfo.user_no,
      (response) => {
        this.heritages = response.data;
        console.log(this.heritages);
      },
      (error) => {
        console.log(error);
      }
    );
  },
}
</script>

<style>
.leftbutton {
  position: relative;
  left: 45%;
  color: #24303F;
  font-size: 40px;
  margin: 2% 0% 2% 0%;
  
}
.rightbutton {
  position: relative;
  left: 50%;
  color: #24303F;
  font-size: 40px;
  margin: 2% 0% 2% 0%;
}
</style>