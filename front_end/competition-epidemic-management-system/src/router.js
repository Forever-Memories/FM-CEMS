import Vue from 'vue'
import ElementUI from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'
import Router from 'vue-router'

import axios from 'axios';
axios.defaults.headers.post['Content-Type'] = 'application/x-www-form-urlencoded';
Vue.prototype.$axios = axios;

import login from './view/login/login'
import manage from './view/manage/manage'
import testcase from "./view/manage/contant/testcase";

Vue.use(ElementUI)

Vue.use(Router)

const router = new Router({
    routes: [
        {
            path: '/login',
            name: 'login',
            component: login
        },
        {
            path: '/manage',
            name: 'manage',
            component: manage,
            children: [
                {
                    path: 'testcase',
                    name: 'testcase',
                    component: testcase
                }
            ]
        }
    ]

})

router.beforeEach((to,from,next) => {
    if(to.name !== 'login' && localStorage.getItem('userId') == '0') next({name: 'login'})
    else next()
})

export default router