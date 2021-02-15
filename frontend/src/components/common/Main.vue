<template>
<v-main>
  <v-container class="my-16">
    <v-row class="text-center">
      <v-col cols="12">
        <Kakaomap/>
      </v-col>
      <v-col
        class="mb-5"
        cols="12"
      >
      <!-- 밑에 부터 구조 잡기 위해 남겨둠. -->
        <h2 class="headline font-weight-bold mb-3">
          What's next?
        </h2>

        <v-row justify="center">
          <a
            v-for="(next, i) in whatsNext"
            :key="i"
            :href="next.href"
            class="subheading mx-3"
            target="_blank"
          >
            {{ next.text }}
          </a>
        </v-row>
      </v-col>
      <v-col
        class="mb-5"
        cols="12"
      >
        <h2 class="headline font-weight-bold mb-3">
          Important Links
        </h2>

        <v-row justify="center">
          <a
            v-for="(link, i) in importantLinks"
            :key="i"
            :href="link.href"
            class="subheading mx-3"
            target="_blank"
          >
            {{ link.text }}
          </a>
        </v-row>
      </v-col>

      <v-col
        class="mb-5"
        cols="12"
      >
        <h2 class="headline font-weight-bold mb-3">
          Ecosystem
        </h2>

        <v-row justify="center">
          <a
            v-for="(eco, i) in ecosystem"
            :key="i"
            :href="eco.href"
            class="subheading mx-3"
            target="_blank"
          >
            {{ eco.text }}
          </a>
        </v-row>
      </v-col>
    </v-row>
  </v-container>
</v-main>
</template>

<!-- Main.vue -->
<script>
import Kakaomap from '@/components/modal/map/Kakaomap';

  export default {
    name: 'Main',
    components: {
      Kakaomap
    }
  }
</script>


<style>

</style>