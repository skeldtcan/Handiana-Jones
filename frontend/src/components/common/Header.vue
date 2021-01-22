<template>
  <div>
        <v-app-bar
        app
        color="blueGreyDarken4"
      >
        <div class="d-flex align-center">
          <v-toolbar-title color="blueGreyDarken4"><span class="white--text east-sea-Dokdo " style="font-size:48px;">한디아나존스</span></v-toolbar-title>
        </div>

        <v-spacer></v-spacer>

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

