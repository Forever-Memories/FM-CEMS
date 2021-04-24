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
import people_manage from "./view/manage/contant/people-manage/people-manage"

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
                },
                {
                    path: 'people_manage',
                    name: 'people_manage',
                    component: people_manage
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