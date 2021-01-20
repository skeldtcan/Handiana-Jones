<template>
  <v-container>
    <v-row class="text-center">
      <v-col cols="12 mb-12">
        <v-img
          :src="require('@/assets/mainImage.jpg')"
          gradient="rgba(10,15,21,.33), rgba(15,12,22,.7)"
          class="mb-4"
          height="300"
        />
      </v-col>
      
      <v-col class="mt-12">
        <div class="wrap">
          <div class="search">
              <input type="text" class="searchTerm" placeholder="What are you looking for?">
              <button type="" class="searchButton">
                <i class="fa fa-search"></i>
            </button>
          </div>
        </div>

        <p class="subheading font-weight-regular">
          For help and collaboration with other Vuetify developers,
          <br>please join our online
          <a
            href="https://community.vuetifyjs.com"
            target="_blank"
          >Discord Community</a>
        </p>
      </v-col>

      <v-col
        class="mb-5"
        cols="12"
      >
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
</template>

<script>
  export default {
    name: 'Main',
  }
</script>

<style>
@import url(https://fonts.googleapis.com/css?family=Open+Sans);

body{
  background: #f2f2f2;
  font-family: 'Open Sans', sans-serif;
}

.search {
  width: 100%;
  position: relative;
  display: flex;
}

.searchTerm {
  width: 100%;
  border: 4px solid #263238;
  border-right: none;
  padding: 5px;
  height: 50px;
  border-radius: 5px 5px 5px 5px;
  outline: none;
  color: #263238;
}

.searchTerm:focus{
  color: #263238;
}

.searchButton {
  width: 50px;
  height: 48px;
  border: 0px solid #263238;
  background: #263238;
  text-align: center;
  color: #fff;
  border-radius: 0 5px 5px 0;
  cursor: pointer;
  font-size: 25px;
}

/*Resize the wrap to see the search bar change!*/
.wrap{
  width: 60%;
  position: absolute;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
}
</style>