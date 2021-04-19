import Vue from 'vue'
import App from './App.vue'
import router from './router'

Vue.config.productionTip = false


localStorage.setItem('userId', '1')
new Vue({
  router,
  render: h => h(App),
}).$mount('#app')

