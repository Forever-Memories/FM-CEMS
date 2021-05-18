<template>
    <body>
    <el-row>
        <el-col :span="8" :offset="8">
            <el-card class="login-card">
                <h2>欢迎登录FM-CEMS(Alpha)</h2>
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
                        <el-button type="primary" @click="submitForm('validateForm')">提交</el-button>
                        <el-button @click="resetForm('validateForm')">重置</el-button>
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
                validateForm: {
                    account: '',
                    password: ''
                }
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
                                localStorage.setItem('userId', this.validateForm.account);
                                localStorage.setItem('userType', res.data.userType);
                                localStorage.setItem('name', res.data.name)
                                this.$message.success('登陆成功')
                                this.$router.push("/manage/people_manage");
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