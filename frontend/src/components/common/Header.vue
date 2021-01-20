<template>
  <div>
        <v-app-bar
        app
        color="blueGreyDarken4"
      >
        <div class="d-flex align-center">
          <!-- <v-img
            alt="Vuetify Logo"
            class="shrink mr-2"
            contain
            src="https://cdn.vuetifyjs.com/images/logos/vuetify-logo-dark.png"
            transition="scale-transition"
            width="40"
          /> -->

          <!-- <v-img
            alt="Vuetify Name"
            class="shrink mt-1 hidden-sm-and-down"
            contain
            min-width="100"
            src="https://cdn.vuetifyjs.com/images/logos/vuetify-name-dark.png"
            width="100"
          /> -->
          <v-toolbar-title color="blueGreyDarken4"><span class="white--text east-sea-Dokdo " style="font-size:48px;">한디아나존스</span></v-toolbar-title>
          <!-- <b class="white">한디아나존스</b> -->
        </div>

        <v-spacer></v-spacer>

        <!-- <v-btn
          href="https://github.com/vuetifyjs/vuetify/releases/latest"
          target="_blank"
          text
        > -->
        <!-- </v-btn> -->
          <span class="mr-2"><Join/></span>
          <span class="mr-2"><Login/></span>
          <!-- <v-icon>mdi-open-in-new</v-icon> -->
        
      </v-app-bar>
  </div>
</template>

<script>
import Join from '@/components/modal/auth/Join';
import Login from '@/components/modal/auth/Login';

export default {
  name: 'Header',
  components: {
    Join,
    Login,
  }
}
</script>

<style>

</style>

