<template>
    <div class="manage">
        <el-container>
            <el-header>
                <span style="float: left; font-weight: 900; font-size: 30px;">FM-CEMS</span>
                <el-dropdown style="float: right;margin-right: 10px">
                    <i class="el-icon-setting"></i>
                    <el-dropdown-menu slot="dropdown">
                        <el-button type="danger" @click="handleQuit" plain>退出登陆</el-button>
                    </el-dropdown-menu>
                </el-dropdown>
                <span style="float: right">欢迎您，{{this.name}}，您的身份是{{this.userType}}</span>
            </el-header>
            <el-container>
                <el-aside width="200px">
                    <el-menu
                            default-active="this.$route.path2"
                            class="el-menu-vertical-demo"
                            @open="handleOpen"
                            @close="handleClose"
                            background-color="#545c64"
                            text-color="#fff"
                            active-text-color="#ffd04b"
                            router>
                        <el-menu-item index="/manage/testcase">
                            <i class="el-icon-postcard"></i>
                            <span slot="title">测试页</span>
                        </el-menu-item>
                        <el-submenu index="2">
                            <template slot="title"><i class="el-icon-user"></i>信息管理</template>
                            <el-menu-item index="/participate/info">
                                <template slot="title"><i class="el-icon-user"></i>我的信息</template>
                            </el-menu-item>
                            <el-menu-item index="/participate/healthy">
                                <template slot="title"><i class="el-icon-upload"></i>健康信息上报</template>
                            </el-menu-item>
                        </el-submenu>
                        <el-menu-item index="/participate/competition">
                            <i class="el-icon-s-help"></i>
                            <span slot="title">赛事查询</span>
                        </el-menu-item>
                        <el-submenu index="5">
                            <template slot="title"><i class="el-icon-date"></i>参赛活动</template>
                            <el-menu-item index="/participate/bus">
                                <template slot="title"><i class="el-icon-position"></i>班车安排</template>
                            </el-menu-item>
                            <el-menu-item index="/participate/registry">
                                <template slot="title"><i class="el-icon-s-claim"></i>我的报名</template>
                            </el-menu-item>
                        </el-submenu>
                    </el-menu>
                </el-aside>
                <el-container>
                    <router-view></router-view>
                </el-container>
            </el-container>
        </el-container>
    </div>
</template>

<script>
    export default {
        name: "organize",
        data() {
            return {
                list: null,
                name: localStorage.getItem('name'),
                userType: null
            }
        },
        created() {
            this.getType()
        },
        methods: {
            handleQuit() {
                localStorage.setItem('userId', '0')
                localStorage.setItem('userType', null);
                localStorage.setItem('name', null)
                this.$router.push("/login");
            },
            getType() {
                console.log(localStorage.getItem('userType'))
                let userType = localStorage.getItem('userType')
                if (userType === "1") {
                    this.userType = "参赛人员"
                } else if (userType === "2") {
                    this.userType = "组织人员"
                } else if(userType === "3") {
                    this.userType = "管理员"
                }
            }
        }
    };
</script>

<style scoped>
    .el-header {
        background-color: #B3C0D1;
        color: #333;
        line-height: 60px;
    }

    .el-menu {
        text-align: left;
    }

    .el-aside {
        background-color: #545c64;
        color: #333;
        text-align: center;
    }

    .el-main {
        background-color: #E9EEF3;
        color: #333;
    }

    .el-container, .el-header, .el-aside, .el-main, .manage {
        margin: 0;
        padding: 0;
        height: 100%;
    }
</style>