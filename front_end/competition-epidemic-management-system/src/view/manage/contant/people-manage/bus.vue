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
                    <el-date-picker type="datetime" placeholder="选择日期时间" v-model="createFrom.time" value-format="timestamp" style="width: 100%;"></el-date-picker>
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
        <el-dialog
                title="编辑班车安排"
                :visible.sync="dialogEditFormVisible"
                width="30%">
            <el-form ref="editFrom" :model="editFrom" label-width="80px" size="mini" label-position="left">
                <el-form-item label="地点">
                    <el-input v-model="editFrom.placeName"></el-input>
                </el-form-item>
                <el-form-item label="时间">
                    <el-date-picker type="datetime" placeholder="选择日期时间" v-model="editFrom.time" value-format="timestamp" style="width: 100%;"></el-date-picker>
                </el-form-item>
                <el-form-item label="安排信息">
                    <el-input v-model="editFrom.arrangement"></el-input>
                </el-form-item>
                <el-form-item label="备注">
                    <el-input v-model="editFrom.comment"></el-input>
                </el-form-item>
            </el-form>
            <span slot="footer" class="dialog-footer">
    <el-button @click="dialogEditFormVisible = false">取 消</el-button>
    <el-button type="primary" @click="edit('editFrom')">确 定</el-button>
  </span>
        </el-dialog>
        <el-table :data="list.slice((currentPage-1)*pageSize,currentPage*pageSize)">
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
                    <el-button @click="handleEdit(scope.row)" type="text" size="small">编辑</el-button>
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
        name: "bus",
        inject: ['reload'],
        data() {
            return {
                list: null,
                currentPage: 1,
                pageSize:10,
                dialogCreateFormVisible: false,
                dialogEditFormVisible : false,
                deleteBusVisible: false,
                createFrom: {
                    "arrangement": "",
                    "comment": "",
                    "placeName": "",
                    "time": new Date().getTime()
                },
                editFrom: {
                    "arrangement": "",
                    "comment": "",
                    "id": null,
                    "placeName": "",
                    "time": null
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
            handleSizeChange: function (size) {
                this.pageSize = size;
                console.log(this.pageSize)  //每页下拉显示数据
            },
            handleCurrentChange: function(currentPage){
                this.currentPage = currentPage;
                console.log(this.currentPage)  //点击第几页
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
            handleEdit(row) {
                this.editFrom = {
                    "arrangement": row.arrangement,
                    "comment": row.comment,
                    "id": row.id,
                    "placeName": row.placeName,
                    "time": Date.parse(new Date(row.time))
                }
                this.dialogEditFormVisible = true;
            },
            edit(formEdit) {
                this.$refs[formEdit].validate((valid) => {
                    if (valid) {
                        console.log(this.editFrom)
                        this.$axios.post('/competition-epidemic/bus/edit',
                            {
                                "arrangement": this.editFrom.arrangement,
                                "comment": this.editFrom.comment,
                                "id": this.editFrom.id,
                                "placeName": this.editFrom.placeName,
                                "time": this.editFrom.time
                            }
                        );
                        this.reload();
                    }else {
                        this.$message.error('编辑班车信息失败');
                    }
                });
                this.dialogEditFormVisible = false
            },
        }
    }
</script>

<style scoped>

</style>