<template>
    <el-main>
        <el-form :inline="true" :model="formInline" class="demo-form-inline">
            <el-form-item label="所属单位">
                <el-input v-model="formInline.unit" placeholder="所属单位"></el-input>
            </el-form-item>
            <el-form-item label="人员姓名">
                <el-input v-model="formInline.peopleName" placeholder="人员姓名">
                </el-input>
            </el-form-item>
            <el-form-item>
                <el-button type="primary" @click="searchPeople()">查询</el-button>
                <el-button type="primary" @click="handleCreate" plain>新增人员信息</el-button>
                <el-dialog
                        title="新增人员信息"
                        :visible.sync="dialogCreateFormVisible"
                        width="50%">
                    <el-form ref="createFrom" :model="createFrom" label-width="120px" size="mini" label-position="left">
                        <el-form-item label="姓名">
                            <el-input v-model="createFrom.participantInfoDO.name"></el-input>
                        </el-form-item>
                        <el-form-item label="所属单位">
                            <el-input v-model="createFrom.participantInfoDO.unit"></el-input>
                        </el-form-item>
                        <el-form-item label="身份证号">
                            <el-input v-model="createFrom.participantInfoDO.idNumber"></el-input>
                        </el-form-item>
                        <el-form-item label="来源地">
                            <el-input v-model="createFrom.participantInfoDO.comeFrom"></el-input>
                        </el-form-item>
                        <el-form-item label="身份类型">
                            <el-select v-model="createFrom.userDO.type" clearable placeholder="请选择">
                                <el-option
                                        v-for="item in typeList" :key="item" :label="item" :value="item">
                                </el-option>
                            </el-select>
                        </el-form-item>
                        <el-form-item label="联系号码">
                            <el-input v-model="createFrom.userDO.phoneNumber"></el-input>
                        </el-form-item>
                        <el-form-item label="密码">
                            <el-input v-model="createFrom.userDO.password"></el-input>
                        </el-form-item>
                    </el-form>
                    <span slot="footer" class="dialog-footer">
    <el-button @click="dialogCreateFormVisible = false">取 消</el-button>
    <el-button type="primary" @click="createPeople('createFrom')">确 定</el-button>
  </span>
                </el-dialog>
                <el-dialog
                        title="编辑人员信息"
                        :visible.sync="dialogEditFormVisible"
                        width="50%">
                    <el-form ref="editFrom" :model="editFrom" label-width="120px" size="mini" label-position="left">
                        <el-form-item label="姓名">
                            <el-input v-model="editFrom.name"></el-input>
                        </el-form-item>
                        <el-form-item label="所属单位">
                            <el-input v-model="editFrom.unit"></el-input>
                        </el-form-item>
                        <el-form-item label="身份证号">
                            <el-input v-model="editFrom.idNumber"></el-input>
                        </el-form-item>
                        <el-form-item label="来源地">
                            <el-input v-model="editFrom.comeFrom"></el-input>
                        </el-form-item>
                    </el-form>
                    <span slot="footer" class="dialog-footer">
    <el-button @click="dialogEditFormVisible = false">取 消</el-button>
    <el-button type="primary" @click="editPeople('editFrom')">确 定</el-button>
  </span>
                </el-dialog>
            </el-form-item>
        </el-form>
        <el-table :data="list.slice((currentPage-1)*pageSize,currentPage*pageSize)">
            <el-table-column prop="userId" label="人员id"  width="100">
            </el-table-column>
            <el-table-column prop="name" label="姓名" width="120">
            </el-table-column>
            <el-table-column prop="type" label="人员类型" width="120">
            </el-table-column>
            <el-table-column prop="unit" label="所属单位" width="180">
            </el-table-column>
            <el-table-column prop="idNumber" label="身份证号" width="180">
            </el-table-column>
            <el-table-column prop="comeFrom" label="来源地" width="100">
            </el-table-column>
            <el-table-column prop="phoneNumber" label="联系号码" width="280">
            </el-table-column>
          <el-table-column
              fixed="right"
              label="操作"
              width="120">
            <template slot-scope="scope">
                <el-button  @click="handleEdit(scope.row)" type="text" size="small">编辑</el-button>
                <el-popover
                        placement="top"
                        width="160"
                        ref="deleteVisible">
                    <p>危险操作！确定删除该用户的信息吗？</p>
                    <div style="text-align: right; margin: 0">
                        <el-button type="danger" size="mini" @click="deleteByUserId(scope.row.userId)" >确定</el-button>
                    </div>
                    <el-button slot="reference" @click="deleteVisible = true" type="danger" size="small">删除</el-button>
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
        name: "people-manage",
        inject: ['reload'],
        data() {
            return {
                currentPage: 1,
                pageSize:10,
                list: null,
                typeList: ["参赛人员","组织人员"],
                formInline: {
                    unit: '',
                    peopleName: ''
                },
                deleteVisible: false,
                dialogCreateFormVisible: false,
                dialogEditFormVisible: false,
                createFrom: {
                    "participantInfoDO": {
                        "comeFrom": "",
                        "idNumber": "",
                        "name": "",
                        "unit": ""
                    },
                    "userDO": {
                        "password": "",
                        "phoneNumber": "",
                        "type": "参赛人员"
                    }
                },
                editFrom: {
                    "userId": null,
                    "comeFrom": "",
                    "idNumber": "",
                    "name": "",
                    "unit": "",
                }
            }
        },
        created() {
            this.getData();
        },
        methods: {
            handleSizeChange: function (size) {
                this.pageSize = size;
                console.log(this.pageSize)  //每页下拉显示数据
            },
            handleCurrentChange: function(currentPage){
                this.currentPage = currentPage;
                console.log(this.currentPage)  //点击第几页
            },
            getData() {
                this.$axios.get('/competition-epidemic/participant-info/all-infos').then((res) => {
                    console.log(res)
                    this.list = res.data
                })
            },
            onSubmit() {
                console.log('submit!');
            },
            handleCreate() {
                this.createFrom = {
                    "participantInfoDO": {
                        "comeFrom": "",
                        "idNumber": "",
                        "name": "",
                        "unit": "",
                    },
                    "userDO": {
                        "password": "",
                        "phoneNumber": "",
                        "type": "参赛人员"
                    }
                };
                this.dialogCreateFormVisible = true;
            },
            handleEdit(row) {
                this.editFrom = {
                    "userId" : row.userId,
                    "comeFrom": row.comeFrom,
                    "idNumber": row.idNumber,
                    "name": row.name,
                    "unit": row.unit,
                };
                this.dialogEditFormVisible = true;
            },
            editPeople(formEdit) {
                this.$refs[formEdit].validate((valid) => {
                    if (valid) {
                        this.$axios.post('/competition-epidemic/participant-info/edit',
                            {
                                "userId" : this.editFrom.userId,
                                "comeFrom": this.editFrom.comeFrom,
                                "idNumber": this.editFrom.idNumber,
                                "name": this.editFrom.name,
                                "unit": this.editFrom.unit,
                            }
                        );
                        this.reload();
                    }else {
                        this.$message.error('编辑人员信息失败');
                    }
                });
                this.dialogEditFormVisible = false
            },
            searchPeople() {
                console.log(this.formInline)
                this.$axios.post('/competition-epidemic/participant-info/search',
                    {
                        unit: this.formInline.unit,
                        name: this.formInline.peopleName
                    }).then((res) => {
                    this.list = res.data
                });
                console.log(this.list)
            },
            createPeople(formPeople) {
                this.$refs[formPeople].validate((valid) => {
                    if (valid) {
                        this.$axios.post('/competition-epidemic/participant-info/create',
                            {
                                "participantInfoDO": {
                                    "comeFrom": this.createFrom.participantInfoDO.comeFrom,
                                    "idNumber": this.createFrom.participantInfoDO.idNumber,
                                    "name": this.createFrom.participantInfoDO.name,
                                    "unit": this.createFrom.participantInfoDO.unit,
                                },
                                "userDO": {
                                    "password": this.createFrom.userDO.password,
                                    "phoneNumber": this.createFrom.userDO.phoneNumber,
                                    "type": this.createFrom.userDO.type
                                }
                            });
                    } else {
                        this.$message.error('新增人员信息失败');
                    }
                });
                this.dialogCreateFormVisible = false;
                this.reload()
            },
            deleteByUserId(userId) {
                this.$axios.post('/competition-epidemic/participant-info/delete',
                    {
                        "userId": userId
                    })
                this.deleteVisible = false;
                this.reload();
            }
        }
    }
</script>

<style scoped>

</style>