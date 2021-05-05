<template>
    <el-main>
        <el-form :inline="true" :model="formInline" class="demo-form-inline">
            <el-form-item label="人员姓名">
                <el-input v-model="formInline.peopleName" placeholder="人员姓名">
                </el-input>
            </el-form-item>
            <el-form-item>
                <el-button type="primary" @click="onSubmit">查询</el-button>
                <el-button type="primary" @click="handleCreate" plain>新增健康信息</el-button>
                <el-dialog
                        title="新增健康信息"
                        :visible.sync="dialogCreateFormVisible"
                        width="50%">
                    <el-form ref="form" :model="createFrom" label-width="120px" size="mini" label-position="left">
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
    <el-button type="primary" @click="createRegistry">确 定</el-button>
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
                    <el-button @click="handleClick(scope.row)" type="text" size="small">编辑</el-button>
                    <el-button type="danger" size="small">删除</el-button>
                </template>
            </el-table-column>
        </el-table>
    </el-main>
</template>

<script>
    export default {
        name: "healthy",
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
            createRegistry() {

                this.dialogCreateFormVisible = false;
            }
        }
    }
</script>

<style scoped>

</style>