<template>
    <el-main>
        <el-form :inline="true" :model="formInline" class="demo-form-inline">
            <el-form-item label="人员姓名">
                <el-input v-model="formInline.peopleName" placeholder="人员姓名">
                </el-input>
            </el-form-item>
            <el-form-item>
                <el-button type="primary" @click="searchHealthy()">查询</el-button>
                <el-button type="primary" @click="handleCreate" plain>新增健康信息</el-button>
                <el-dialog
                        title="新增健康信息"
                        :visible.sync="dialogCreateFormVisible"
                        width="50%">
                    <el-form ref="createFrom" :model="createFrom" label-width="120px" size="mini" label-position="left">
                        <el-form-item label="人员id">
                            <el-input v-model="createFrom.userId"></el-input>
                        </el-form-item>
                        <el-form-item label="体温">
                            <el-input v-model="createFrom.temperature"></el-input>
                        </el-form-item>
                        <el-form-item label="是否咳嗽">
                            <el-select v-model="createFrom.isCough" clearable placeholder="请选择">
                                <el-option
                                        v-for="item in isOrNoList"
                                        :key="item.value"
                                        :label="item.label"
                                        :value="item.value">
                                </el-option>
                            </el-select>
                        </el-form-item>
                        <el-form-item label="是否有疫病史">
                            <el-select v-model="createFrom.isHistory" clearable placeholder="请选择">
                                <el-option
                                        v-for="item in isOrNoList"
                                        :key="item.value"
                                        :label="item.label"
                                        :value="item.value">
                                </el-option>
                            </el-select>
                        </el-form-item>
                        <el-form-item label="是否有亲密接触">
                            <el-select v-model="createFrom.isTouch" clearable placeholder="请选择">
                                <el-option
                                        v-for="item in isOrNoList"
                                        :key="item.value"
                                        :label="item.label"
                                        :value="item.value">
                                </el-option>
                            </el-select>
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
            <el-table-column prop="userName" label="姓名" width="120">
            </el-table-column>
            <el-table-column prop="temperature" label="体温" width="180">
            </el-table-column>
            <el-table-column prop="isCough" label="是否咳嗽" width="180">
            </el-table-column>
            <el-table-column prop="isHistory" label="是否有疫病史" width="180">
            </el-table-column>
            <el-table-column prop="isTough" label="是否有亲密接触" width="180">
            </el-table-column>
            <el-table-column prop="time" label="登记时间" width="180">
            </el-table-column>
            <el-table-column
                    fixed="right"
                    label="操作"
                    width="180">
                <template slot-scope="scope">
                    <el-popover
                            placement="top"
                            width="160"
                            ref="deleteHealthyVisible">
                        <p>确定删除该条健康上报记录安排吗？</p>
                        <div style="text-align: right; margin: 0">
                            <el-button type="danger" size="mini" @click="deleteHealthyById(scope.row.id)" >确定</el-button>
                        </div>
                    <el-button slot="reference" @click="deleteHealthyVisible = true" type="danger" size="small">删除</el-button>
                    </el-popover>
                </template>
            </el-table-column>
        </el-table>
    </el-main>
</template>

<script>
    export default {
        name: "healthy",
        inject: ['reload'],
        data() {
            return {
                list: null,
                isOrNoList: [{
                    value: true,
                    label: '是'
                }, {
                    value: false,
                    label: '否'
                }],
                formInline: {
                    peopleName: ''
                },
                dialogCreateFormVisible: false,
                deleteHealthyVisible : false,
                createFrom: {
                    "isCough": null,
                    "isHistory": null,
                    "isTouch": null,
                    "temperature": null,
                    "time": new Date().getTime(),
                    "userId": null,
                }
            }
        },
        created() {
            this.getData();
        },
        methods: {
            getData() {
                this.$axios.get('/competition-epidemic/healthy-info/all-infos').then((res) => {
                    console.log(res)
                    this.list = res.data
                })
            },
            searchHealthy() {
                console.log(this.formInline)
                this.$axios.post('/competition-epidemic/healthy-info/search',
                    {
                        name: this.formInline.peopleName
                    }).then((res) => {
                    this.list = res.data
                });
            },
            onSubmit() {
                console.log('submit!');
            },
            handleCreate() {
                this.createFrom = {
                    "isCough": null,
                    "isHistory": null,
                    "isTouch": null,
                    "temperature": null,
                    "time": new Date().getTime(),
                    "userId": null,
                };
                this.dialogCreateFormVisible = true;
            },
            createRegistry(formHealthy) {
                this.$refs[formHealthy].validate((valid) => {
                    if (valid) {
                        this.$axios.post('/competition-epidemic/healthy-info/create',
                            {
                                "isCough": this.createFrom.isCough,
                                "isHistory": this.createFrom.isHistory,
                                "isTouch": this.createFrom.isTouch,
                                "temperature": this.createFrom.temperature,
                                "time": this.createFrom.time,
                                "userId": this.createFrom.userId
                            }
                        )
                    } else {
                        this.$message.error('上报健康信息失败');
                    }
                });
                this.dialogCreateFormVisible = false;
                this.reload();
            },
            deleteHealthyById(healthyId) {
                console.log('healthyId: ')
                console.log(healthyId)
                this.$axios.post('/competition-epidemic/healthy-info/delete',
                    {
                        "id": healthyId
                    })
                this.deleteHealthyVisible = false;
                this.reload();
            },
        }
    }
</script>

<style scoped>

</style>