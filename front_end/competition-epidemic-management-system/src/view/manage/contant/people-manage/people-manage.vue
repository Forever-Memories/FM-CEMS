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
                <el-button type="primary" @click="onSubmit">查询</el-button>
                <el-button type="primary" @click="handleCreate" plain>新增人员信息</el-button>
                <el-dialog
                        title="新增人员信息"
                        :visible.sync="dialogCreateFormVisible"
                        width="50%">
                    <el-form ref="form" :model="createFrom" label-width="120px" size="mini" label-position="left">
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
    <el-button type="primary" @click="createPeople">确 定</el-button>
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
            <el-table-column prop="comeFrom" label="来源地">
            </el-table-column>
          <el-table-column
              fixed="right"
              label="操作"
              width="120">
            <template slot-scope="scope">
                <el-button  @click="handleClick(scope.row)" type="text" size="small">编辑</el-button>
            </template>
          </el-table-column>
        </el-table>
    </el-main>
</template>

<script>
    export default {
        name: "people-manage",
        data() {
            return {
                list: null,
                typeList: ["参赛人员","组织人员"],
                formInline: {
                    unit: '',
                    peopleName: ''
                },
                dialogCreateFormVisible: false,
                createFrom: {
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
                }
            }
        },
        created() {
            this.getData();
        },
        methods: {
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
            createPeople() {

                this.dialogCreateFormVisible = false;
            }
        }
    }
</script>

<style scoped>

</style>