<template>
<v-container class="v-example-row mt-3">
    <v-row>
      <v-col>
        <h3>글목록</h3>
      </v-col>
    </v-row>
    <v-row>
      <v-col>
        <v-data-table
          :headers="headers"
          :items="items"
          :items-per-page="5"
          class="elevation-1"
        >
          // <div>
          //   <h3>제목</h3>
          //   <p></p>
          // </div>
        </v-data-table>
      </v-col>
    </v-row>
</v-container>

</template>

<script>
import { recommends } from "@/api/recommend.js";
import { mapState } from "vuex";

export default {
  name: "RecommendResult",
  data () {
      return {
        items: [],
      };
  },
  computed: {
    ...mapState(["userInfo", "isLogin"])
  },
  created() {
    recommends (
      this.userInfo.user_no,
      (response) => {
        console.log(response);
        this.items = response.data;
        console.log(this.items);
      },
      (error) => {
        console.log(error);
      }
    );
  },
}
</script>

<style>

</style>