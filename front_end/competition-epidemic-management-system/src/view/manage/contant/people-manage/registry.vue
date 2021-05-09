<template>
    <el-main>
        <el-form :inline="true" :model="formInline" class="demo-form-inline">
            <el-form-item label="赛项名称">
                <el-input v-model="formInline.competitionName" placeholder="赛项名称"></el-input>
            </el-form-item>
            <el-form-item label="人员姓名">
                <el-input v-model="formInline.peopleName" placeholder="人员姓名">
                </el-input>
            </el-form-item>
            <el-form-item>
                <el-button type="primary" @click="onSubmit">查询</el-button>
                <el-button type="primary" @click="handleCreate" plain>新增报名</el-button>
                <el-dialog
                        title="新增报名"
                        :visible.sync="dialogCreateFormVisible"
                        width="60%">
                    <el-form ref="form" :model="createFrom" label-width="80px" size="mini">
                        <el-form-item label="参赛人员">
                            <el-input v-model="createFrom.user"></el-input>
                        </el-form-item>
                        <el-form-item label="参赛项目">
                            <el-input v-model="createFrom.competition"></el-input>
                        </el-form-item>
                    </el-form>
                    <span slot="footer" class="dialog-footer">
    <el-button @click="dialogCreateFormVisible = false">取 消</el-button>
    <el-button type="primary" @click="createRegistry">确 定</el-button>
  </span>
                </el-dialog>
            </el-form-item>
        </el-form>
        <el-table :data="list">
            <el-table-column prop="userName" label="参赛人员" width="180">
            </el-table-column>
            <el-table-column prop="userUnit" label="代表单位" width="180">
            </el-table-column>
            <el-table-column prop="competitionName" label="参加项目" width="180">
            </el-table-column>
            <el-table-column prop="competitionStartTime" label="比赛时间" width="300">
            </el-table-column>
            <el-table-column
                    fixed="right"
                    label="操作"
                    width="200">
                <template slot-scope="scope">
                    <el-button  @click="handleClick(scope.row)" type="text" size="small">编辑</el-button>
                    <el-button type="danger" size="small">删除</el-button>
                </template>
            </el-table-column>
        </el-table>
    </el-main>
</template>

<script>
    export default {
        name: "registry",
        inject: ['reload'],
        data() {
            return {
                list: null,
                formInline: {
                    competitionName: '',
                    peopleName: ''
                },
                dialogCreateFormVisible: false,
                createFrom: {
                    "competition": "",
                    "user": ""
                }
            }
        },
        created() {
            this.getData();
        },
        methods: {
            getData() {
                this.$axios.get('/competition-epidemic/registry/all-info').then((res) => {
                    console.log(res)
                    this.list = res.data
                })
            },
            onSubmit() {
                console.log('submit!');
            },
            handleCreate() {
                this.createFrom = {
                    "competition": "",
                    "user": ""
                };
                this.dialogCreateFormVisible = true;
            },
            createRegistry() {

                this.dialogCreateFormVisible = false;
            }
        }
    }
</script>

<style scoped>

</style>