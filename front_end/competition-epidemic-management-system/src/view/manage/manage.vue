<template>
    <div class="manage">
        <el-container>
            <el-header>
                <span style="float: left; font-weight: 900; font-size: 30px;">FM-CEMS</span>
                <el-dropdown style="float: right;margin-right: 10px">
                    <i class="el-icon-setting"></i>
                    <el-dropdown-menu slot="dropdown">
                        <el-dropdown-item>退出登陆</el-dropdown-item>
                    </el-dropdown-menu>
                </el-dropdown>
                <span style="float: right">朱浙庆</span>
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
                            <template slot="title"><i class="el-icon-user"></i>人员管理</template>
                            <el-menu-item index="/manage/people_manage">
                                <template slot="title"><i class="el-icon-user"></i>人员管理</template>
                            </el-menu-item>
                            <el-menu-item index="2-2">
                                <template slot="title"><i class="el-icon-upload"></i>健康信息上报</template>
                            </el-menu-item>
                        </el-submenu>
                        <el-submenu index="3">
                            <template slot="title"><i class="el-icon-s-custom"></i>管理员</template>
                            <el-menu-item index="3-1">
                                <template slot="title"><i class="el-icon-user-solid"></i>管理员信息</template>
                            </el-menu-item>
                            <el-menu-item index="3-2">
                                <template slot="title"><i class="el-icon-s-check"></i>权限组</template>
                            </el-menu-item>
                        </el-submenu>
                        <el-menu-item index="4">
                            <i class="el-icon-s-help"></i>
                            <span slot="title">赛事管理</span>
                        </el-menu-item>
                        <el-submenu index="5">
                            <template slot="title"><i class="el-icon-date"></i>参赛活动管理</template>
                            <el-menu-item index="5-1">
                                <template slot="title"><i class="el-icon-position"></i>班车安排</template>
                            </el-menu-item>
                            <el-menu-item index="5-2">
                                <template slot="title"><i class="el-icon-s-claim"></i>入场记录</template>
                            </el-menu-item>
                        </el-submenu>
                        <el-submenu index="6">
                            <template slot="title"><i class="el-icon-s-operation"></i>系统管理</template>
                            <el-menu-item index="6-1">
                                <template slot="title"><i class="el-icon-setting"></i>系统设置</template>
                            </el-menu-item>
                            <el-menu-item index="6-2">
                                <template slot="title"><i class="el-icon-s-management"></i>系统日志</template>
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
        name: "manage",
        data() {
            const item = {
                user_id: '0',
                name: '朱浙庆',
                unit: '安徽大学计科院',
                id_number: '12345678'
            };
            return {
                list: null,
                tableData: Array(20).fill(item)
            }
        },
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