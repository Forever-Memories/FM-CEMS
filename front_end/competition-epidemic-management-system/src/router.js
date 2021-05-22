import Vue from 'vue'
import ElementUI from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'
import Router from 'vue-router'

import axios from 'axios';
axios.defaults.headers.post['Content-Type'] = 'application/x-www-form-urlencoded';
Vue.prototype.$axios = axios;

import login from './view/login/login'
import manage from './view/manage/manage'
import participate from "./view/manage/participate";
import organize from "./view/manage/organize";
import testcase from "./view/manage/contant/testcase";
import people_manage from "./view/manage/contant/people-manage/people-manage"
import healthy from "./view/manage/contant/people-manage/healthy";
import competition from "./view/manage/contant/people-manage/competition";
import registry from "./view/manage/contant/people-manage/registry";
import bus from "./view/manage/contant/people-manage/bus";
import participateInfo from "./view/manage/contant/participate/participate-info"
import participateHealthy from "./view/manage/contant/participate/participate-healthy"
import participateCompetition from "./view/manage/contant/participate/participate-competition"
import participateBus from "./view/manage/contant/participate/participate-bus"
import participateRegistry from "./view/manage/contant/participate/participate-registry"
import organizeArrangement from "./view/manage/contant/people-manage/organize-arrangement"
import participateArrangement from "./view/manage/contant/participate/participate-arrangement"

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
                },
                {
                    path: 'healthy',
                    name: 'healthy',
                    component: healthy
                },
                {
                    path: 'competition',
                    name: 'competition',
                    component: competition
                },
                {
                    path: 'registry',
                    name: 'registry',
                    component: registry
                },
                {
                    path: 'bus',
                    name: 'bus',
                    component: bus
                },
                {
                    path: 'organize-arrangement',
                    name: 'organize-arrangement',
                    component: organizeArrangement
                }
            ]
        },
        {
            path: '/participate',
            name: 'participate',
            component: participate,
            children: [
                {
                    path: 'info',
                    name: 'info',
                    component: participateInfo
                },
                {
                    path: 'healthy',
                    name: 'healthy',
                    component: participateHealthy
                },
                {
                    path: 'competition',
                    name: 'competition',
                    component: participateCompetition
                },
                {
                    path: 'bus',
                    name: 'bus',
                    component: participateBus
                },
                {
                    path: 'registry',
                    name: 'registry',
                    component: participateRegistry
                }
            ]
        },
        {
            path: '/organize',
            name: 'organize',
            component: organize,
            children: [
                {
                    path: 'info',
                    name: 'info',
                    component: participateInfo
                },
                {
                    path: 'healthy',
                    name: 'healthy',
                    component: participateHealthy
                },
                {
                    path: 'competition',
                    name: 'competition',
                    component: participateCompetition
                },
                {
                    path: 'bus',
                    name: 'bus',
                    component: participateBus
                },
                {
                    path: 'organize-arrangement',
                    name: 'organize-arrangement',
                    component: participateArrangement
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