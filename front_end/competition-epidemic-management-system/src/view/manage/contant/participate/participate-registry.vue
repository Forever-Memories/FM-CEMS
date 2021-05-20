<template>
    <el-main>
        <el-form :inline="true" class="demo-form-inline">
            <el-form-item>
                <el-button type="primary" @click="handleCreate" plain>新增报名</el-button>
                <el-dialog
                        title="新增报名"
                        :visible.sync="dialogCreateFormVisible"
                        width="60%">
                    <el-form ref="createFrom" :model="createFrom" label-width="100px" size="mini">
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
            <el-table-column prop="competitionStartTime" label="比赛时间" width="3000">
            </el-table-column>
        </el-table>
    </el-main>
</template>

<script>
    export default {
        name: "participateRegistry",
        inject: ['reload'],
        data() {
            return {
                list: null,
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
                this.$axios.post('/competition-epidemic/registry/search',
                    {
                        participantName: localStorage.getItem('name')
                    }).then((res) => {
                    this.list = res.data
                });
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
                                "userId": localStorage.getItem('userId')
                            })
                    } else {
                        this.$message.error('新建报名信息息失败');
                    }
                });
                this.dialogCreateFormVisible = false;
                this.reload();
            },
        }
    }
</script>

<style scoped>

</style>