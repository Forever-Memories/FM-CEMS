<template>
  <div id="app">
    <login></login>
  </div>
</template>

<script>

import login from "./page/login/login";

export default {
  name: 'App',
  components: {
    login
  }
}
</script>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 0;
}
body {
  margin: 0;
  padding: 0;
}

</style>
