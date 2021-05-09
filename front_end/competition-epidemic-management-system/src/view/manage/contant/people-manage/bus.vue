<template>
    <el-main>
        <el-button type="primary" @click="handleCreate" plain>创建班车安排</el-button>
        <el-dialog
                title="创建班车安排"
                :visible.sync="dialogCreateFormVisible"
                width="30%">
            <el-form ref="createFrom" :model="createFrom" label-width="80px" size="mini" label-position="left">
                <el-form-item label="地点">
                    <el-input v-model="createFrom.placeName"></el-input>
                </el-form-item>
                <el-form-item label="时间">
                    <el-date-picker type="datetime" placeholder="选择日期时间" v-model="createFrom.time" value-formate="timestamp" style="width: 100%;"></el-date-picker>
                </el-form-item>
                <el-form-item label="安排信息">
                    <el-input v-model="createFrom.arrangement"></el-input>
                </el-form-item>
                <el-form-item label="备注">
                    <el-input v-model="createFrom.comment"></el-input>
                </el-form-item>
            </el-form>
            <span slot="footer" class="dialog-footer">
    <el-button @click="dialogCreateFormVisible = false">取 消</el-button>
    <el-button type="primary" @click="createBus('createFrom')">确 定</el-button>
  </span>
        </el-dialog>
        <el-table :data="list">
            <el-table-column prop="id" label="班车编号" width="120">
            </el-table-column>
            <el-table-column prop="placeName" label="地点" width="200">
            </el-table-column>
            <el-table-column prop="time" label="时间" width="200">
            </el-table-column>
            <el-table-column prop="arrangement" label="安排信息" width="200">
            </el-table-column>
            <el-table-column prop="comment" label="备注" width="200">
            </el-table-column>
            <el-table-column
                    fixed="right"
                    label="操作"
                    width="200">
                <template slot-scope="scope">
                    <el-button  @click="handleClick(scope.row)" type="text" size="small">编辑</el-button>
                    <el-popover
                            placement="top"
                            width="160"
                            ref="deleteBusVisible">
                        <p>确定删除该班车安排吗？</p>
                        <div style="text-align: right; margin: 0">
                            <el-button type="danger" size="mini" @click="deleteBusById(scope.row.id)" >确定</el-button>
                        </div>
                    <el-button slot="reference" @click="deleteBusVisible = true" type="danger" size="small">删除</el-button>
                    </el-popover>
                </template>
            </el-table-column>
        </el-table>
    </el-main>
</template>

<script>
    export default {
        name: "bus",
        inject: ['reload'],
        data() {
            return {
                list: null,
                dialogCreateFormVisible: false,
                deleteBusVisible: false,
                createFrom: {
                    "arrangement": "",
                    "comment": "",
                    "placeName": "",
                    "time": new Date().getTime()
                }
            }
        },
        created() {
            this.getData();
        },
        methods: {
            getData() {
                this.$axios.get('/competition-epidemic/bus/all-info').then((res) => {
                    console.log(res)
                    this.list = res.data
                })
            },
            handleCreate() {
                this.createFrom = {
                    "arrangement": "",
                    "comment": "",
                    "placeName": "",
                    "time": new Date().getTime()
                };
                this.dialogCreateFormVisible = true;
            },
            createBus(formBus) {
                this.$refs[formBus].validate((valid) => {
                    if (valid) {
                        this.$axios.post('/competition-epidemic/bus/create',
                            {
                                "arrangement": this.createFrom.arrangement,
                                "comment": this.createFrom.comment,
                                "placeName": this.createFrom.placeName,
                                "time": this.createFrom.time
                            })
                    } else {
                        this.$message.error('创建班车安排失败');
                    }
                });
                this.dialogCreateFormVisible = false;
                this.reload();
            },
            deleteBusById(busId) {
                console.log(busId)
                this.$axios.post('/competition-epidemic/bus/delete',
                    {
                        "busId": busId
                    })
                this.deleteBusVisible = false;
                this.reload();
            },
            handleClick(row) {
                console.log(row)
            }
        }
    }
</script>

<style scoped>

</style>