<template>
    <el-main>
        <el-form :inline="true" class="demo-form-inline">
            <el-form-item>
                <el-dialog
                        title="编辑您的信息"
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
        <el-table :data="list">
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

            </template>
          </el-table-column>
        </el-table>
    </el-main>
</template>

<script>
    export default {
        name: "participateInfo",
        inject: ['reload'],
        data() {
            return {
                list: null,
                dialogEditFormVisible: false,
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
            getData() {
                console.log([localStorage.getItem('userId')])
                this.$axios.post('/competition-epidemic/participant-info/infos',
                    [localStorage.getItem('userId')]).then((res) => {
                    console.log(res)
                    this.list = res.data
                })
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
            }
        }
    }
</script>

<style scoped>

</style>