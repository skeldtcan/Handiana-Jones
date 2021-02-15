<template>
  <!-- <v-data-table
    :headers="headers"
    :items="heritages"
    :items-per-page="5"
    class="elevation-1"
  ></v-data-table> -->
  <v-sheet
    class="mx-auto"
    elevation="8"
    max-width="800"
  >
    <v-slide-group
      v-model="model"
      class="pa-4"
      active-class="success"
      show-arrows
    >
      <v-slide-item
        v-for="n in heritages"
        :key="n"
        v-slot="{ active, toggle }"
      >
        <v-card
          :color="active ? undefined : 'grey lighten-1'"
          class="ma-4"
          height="200"
          width="100"
          @click="toggle"
        >
          <v-row
            class="fill-height"
            align="center"
            justify="center"
          >
            <v-scale-transition>
              <v-icon
                v-if="active"
                color="white"
                size="48"
                v-text="'mdi-close-circle-outline'"
              ></v-icon>
            </v-scale-transition>
          </v-row>
        </v-card>
      </v-slide-item>
    </v-slide-group>
  </v-sheet>
</template>

<script>
import { recommends } from "@/api/favor.js";
import { mapState } from "vuex";

export default {
  name: "Recommend",
  data () {
      return {
        headers: [
          {
            text: '문화재명',
            align: 'start',
            sortable: false,
            value: 'ccba_mnm',
          },
          { text: '종류', value: 'bcode_name' },
          { text: '시대', value: 'ccce_name' },
          { text: '주소', value: 'ccba_lcad' },
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