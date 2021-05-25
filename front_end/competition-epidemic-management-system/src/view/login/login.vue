<template>
    <body>
    <el-dialog
            title="参赛人员注册"
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
            title="修改密码"
            :visible.sync="dialogUpdatePasswordFormVisible"
            width="50%">
        <el-form ref="updatePasswordForm" :model="updatePasswordForm" label-width="120px" size="mini" label-position="left">
            <el-form-item label="账号" prop="phoneNumber"
                          :rules="[
                                { required: true, message: '账号不能为空'},
                                ]">
                <el-input v-model="updatePasswordForm.phoneNumber"></el-input>
            </el-form-item>
            <el-form-item label="旧密码" prop="oldPassword"
                          :rules="[
                                { required: true, message: '旧密码不能为空'},
                                ]">
                <el-input v-model="updatePasswordForm.oldPassword" show-password type="text"></el-input>
            </el-form-item>
            <el-form-item label="新密码" prop="newPassword1"
                          :rules="[
                                { required: true, message: '密码不能为空'},
                                ]">
                <el-input v-model="updatePasswordForm.newPassword1" show-password type="text"></el-input>
            </el-form-item>
            <el-form-item label="请再次输入新密码" prop="newPassword2"
                          :rules="[
                                { required: true, message: '密码不能为空'},
                                ]">
                <el-input v-model="updatePasswordForm.newPassword2" show-password type="text"></el-input>
            </el-form-item>
        </el-form>
        <span slot="footer" class="dialog-footer">
    <el-button @click="dialogUpdatePasswordFormVisible = false">取 消</el-button>
    <el-button type="primary" @click="updatePassword('updatePasswordForm')">确 定</el-button>
  </span>
    </el-dialog>
    <el-row>
        <el-col :span="8" :offset="8">
            <el-card class="login-card">
                <h2>欢迎使用赛事疫情防控管理系统</h2>
                <el-divider></el-divider>
                <el-form :model="validateForm" ref="validateForm" label-width="100px" class="demo-ruleForm">
                    <el-form-item
                            label="账号"
                            prop="account"
                            :rules="[
                                { required: true, message: '账号不能为空'},
                                ]"
                    >
                        <el-input type="text" v-model.number="validateForm.account" placeholder="请输入账号"
                                  autocomplete="off"></el-input>
                    </el-form-item>

                    <el-form-item
                            label="密码"
                            prop="password"
                            :rules="[
                                { required: true, message: '密码不能为空'},
                                ]"
                    >
                        <el-input type="text" v-model.number="validateForm.password" autocomplete="off"
                                  placeholder="请输入密码" show-password></el-input>
                    </el-form-item>

                    <el-form-item>
                        <el-button type="primary" @click="submitForm('validateForm')">登陆</el-button>
                        <el-button @click="resetForm('validateForm')">重置</el-button>
                        <el-button @click="handleCreate()">注册</el-button>
                        <el-button @click="handleUpdatePassword()">修改密码</el-button>
                    </el-form-item>
                </el-form>
            </el-card>
        </el-col>
    </el-row>
    </body>
</template>

<style scoped>
    body {
        background-image: url("loginbackgroup.jpg");
        background-size: cover;
    }
    .login-card {
        border-radius: 30px;
        margin-top: 100px;
        justify-content: center;
    }
</style>

<script>
    export default {
        name: 'login',
        data() {
            return {
                dialogCreateFormVisible: false,
                dialogUpdatePasswordFormVisible: false,
                validateForm: {
                    account: '',
                    password: ''
                },
                updatePasswordForm: {
                    phoneNumber: null,
                    oldPassword: null,
                    newPassword1: null,
                    newPassword2: null
                },
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
                handleUpdatePassword() {
                    this.updatePasswordForm = {
                        phoneNumber: null,
                        oldPassword: null,
                        newPassword1: null,
                        newPassword2: null
                    };
                    this.dialogUpdatePasswordFormVisible = true;
                },
                updatePassword(updateForm) {
                    this.$refs[updateForm].validate((valid) => {
                        if(valid) {
                            if (this.updatePasswordForm.newPassword1 !== this.updatePasswordForm.newPassword2) {
                                this.$message.error('两次输入密码不一致，请重新输入');
                            } else {
                                this.$axios.post('/competition-epidemic/user/update-password',
                                    {
                                        "newPassword": this.updatePasswordForm.newPassword1,
                                        "oldPassword": this.updatePasswordForm.oldPassword,
                                        "phoneNumber": this.updatePasswordForm.phoneNumber
                                    }).then((res) => {
                                        if(res.data === true) {
                                            this.$message.success('修改密码成功，请重新登陆');
                                            this.dialogUpdatePasswordFormVisible = false;
                                            this.reload()
                                        } else {
                                            this.$message.error('修改密码失败，请检查账号和密码');
                                            this.dialogUpdatePasswordFormVisible = false;
                                            this.reload()
                                        }
                                })
                            }
                        }
                    });
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
                            this.$message.error('注册信息失败');
                        }
                    });
                    this.dialogCreateFormVisible = false;
                    this.reload()
                },
            };
        },
        created() {

            if (localStorage.getItem('userId') == null) {
                localStorage.setItem('userId','0');
            }
        },
        methods: {
            submitForm(formAccount) {
                this.$refs[formAccount].validate((valid) => {
                    if (valid) {
                        this.$axios.post('/competition-epidemic/sign-in',
                            {
                              "password": this.validateForm.password,
                              "phoneNumber": this.validateForm.account
                            }).then(res => {
                              console.log(res)
                              if(res.data.result) {
                                localStorage.setItem('userId', res.data.userId);
                                localStorage.setItem('userType', res.data.userType);
                                localStorage.setItem('name', res.data.name)
                                this.$message.success('登陆成功');
                                if(res.data.userType === 3) {
                                    this.$router.push("/manage/people_manage");
                                } else if(res.data.userType === 1) {
                                    this.$router.push("/participate/info");
                                } else if(res.data.userType === 2) {
                                    this.$router.push("/organize/organize-arrangement");
                                }
                              } else {
                                this.$message.error('登陆失败，请检查账号和密码');
                              }
                        })
                    } else {
                        this.$message.error('登陆失败，请检查账号和密码');
                    }
                });
            },
            resetForm(formName) {
                this.$refs[formName].resetFields();
            }
        }
    };
</script>