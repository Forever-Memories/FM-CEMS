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
                <el-button type="primary" @click="search()">查询</el-button>
                <el-button type="primary" @click="handleCreate" plain>新增报名</el-button>
                <el-dialog
                        title="新增报名"
                        :visible.sync="dialogCreateFormVisible"
                        width="60%">
                    <el-form ref="createFrom" :model="createFrom" label-width="100px" size="mini">
                        <el-form-item label="参赛人员Id">
                            <el-input v-model="createFrom.userId"></el-input>
                        </el-form-item>
                        <el-form-item label="参赛项目Id">
                            <el-input v-model="createFrom.competitionId"></el-input>
                        </el-form-item>
                    </el-form>
                    <span slot="footer" class="dialog-footer">
    <el-button @click="dialogCreateFormVisible = false">取 消</el-button>
    <el-button type="primary" @click="createRegistry('createFrom')">确 定</el-button>
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
                    <el-popover
                            placement="top"
                            width="160"
                            ref="deleteVisible">
                        <p>确定删除该报名信息吗？</p>
                        <div style="text-align: right; margin: 0">
                            <el-button type="danger" size="mini" @click="deleteRegistry(scope.row)" >确定</el-button>
                        </div>
                        <el-button slot="reference" @click="deleteVisible = true" type="danger" size="small">删除</el-button>
                    </el-popover>
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
                deleteVisible: false,
                formInline: {
                    competitionName: '',
                    peopleName: ''
                },
                dialogCreateFormVisible: false,
                createFrom: {
                    "competitionId": null,
                    "userId": null
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
                    "competitionId": null,
                    "userId": null
                };
                this.dialogCreateFormVisible = true;
            },
            createRegistry(formRegistry) {
                this.$refs[formRegistry].validate((valid) => {
                    if (valid) {
                        this.$axios.post('/competition-epidemic/registry/create',
                            {
                                "competitionId": this.createFrom.competitionId,
                                "userId": this.createFrom.userId
                            })
                    } else {
                        this.$message.error('新建报名信息息失败');
                    }
                });
                this.dialogCreateFormVisible = false;
                this.reload();
            },
            search() {
                this.$axios.post('/competition-epidemic/registry/search',
                    {
                        competitionName: this.formInline.competitionName,
                        participantName: this.formInline.peopleName
                    }).then((res) => {
                    this.list = res.data
                });
            },
            deleteRegistry(row) {
                console.log(row)
                this.$axios.post('/competition-epidemic/registry/delete',
                    {
                        "competitionId": row.competitionId,
                        "userId": row.userId
                    }
                )
                this.deleteVisible = false;
                this.reload();
            }
        }
    }
</script>

<style scoped>

</style>