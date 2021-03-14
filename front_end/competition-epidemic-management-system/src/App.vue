<template>
  <div id="app">
    <!--<login></login>-->
    <manage></manage>
  </div>
</template>

<script>

//import login from "./page/login/login";
import manage from "./page/manage/manage";

export default {
  name: 'App',
  components: {
    //login
    manage
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
html,body,#app {
  margin: 0;
  padding: 0;
  height: 100%;
}

</style>
