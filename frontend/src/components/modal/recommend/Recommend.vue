<template>
  <v-data-table
    :headers="headers"
    :items="heritages"
    :items-per-page="5"
    class="elevation-1"
  ></v-data-table>
</template>

<script>
import { recommends } from "@/api/recommend.js";
import { mapState } from "vuex";

export default {
  name: "RecommendResult",
  data () {
      return {
        headers: [
          {
            text: '문화재명',
            align: 'start',
            sortable: false,
            value: 'ccba_mnm',
          },
          { text: '종류', value: 'mcode_name' },
          { text: '주소', value: 'ccba_lcad' },
          { text: '상세설명', value: 'content' },
          { text: 'Protein (g)', value: 'protein' },
          { text: 'Iron (%)', value: 'iron' },
        ],
        heritages: [],
      };
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

</style>