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
        <el-table :data="list.slice((currentPage-1)*pageSize,currentPage*pageSize)">
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
                            <el-button type="danger" size="mini" @click="deleteRegistry(scope.row)">确定</el-button>
                        </div>
                        <el-button slot="reference" @click="deleteVisible = true" type="danger" size="small">删除
                        </el-button>
                    </el-popover>
                </template>
            </el-table-column>
        </el-table>
        <el-pagination
                @size-change="handleSizeChange"
                @current-change="handleCurrentChange"
                :current-page="currentPage"
                layout="prev, pager, next"
                :page-size="pageSize"
                :total="this.list.length">
        </el-pagination>
    </el-main>
</template>

<script>
    export default {
        name: "registry",
        inject: ['reload'],
        data() {
            return {
                list: null,
                participateList: null,
                currentPage: 1,
                pageSize: 10,
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
            handleSizeChange: function (size) {
                this.pageSize = size;
                console.log(this.pageSize)  //每页下拉显示数据
            },
            handleCurrentChange: function (currentPage) {
                this.currentPage = currentPage;
                console.log(this.currentPage)  //点击第几页
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
                        this.$axios.post('/competition-epidemic/participant-info/infos',
                            [this.createFrom.userId]).then((res) => {
                            if (res.data.length === 0 || res.data[0].type !== "参赛人员") {
                                this.$message.error('该用户id无效，无对应参赛人员，请检查');
                            } else {
                                this.$axios.post('/competition-epidemic/competition/competition',
                                    {
                                    "competitionId" :this.createFrom.competitionId
                                }).then((res2) => {
                                    console.log("打印")
                                    console.log(res2.data)
                                    if(res2.data === null || res2.data === "") {
                                        this.$message.error('该赛项id无效，无对应赛项，请检查');
                                    } else {
                                        this.$axios.post('/competition-epidemic/registry/create',
                                            {
                                                "competitionId": this.createFrom.competitionId,
                                                "userId": this.createFrom.userId
                                            })
                                    }
                                })
                            }
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