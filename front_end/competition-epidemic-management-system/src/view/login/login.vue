<template>
    <body>
    <el-row>
        <el-col :span="8" :offset="8">
            <el-card class="login-card">
                <h2>欢迎登录FM-CEMS(Alpha)</h2>
                <el-divider></el-divider>
                <el-form :model="validateForm" ref="validateForm" label-width="100px" class="demo-ruleForm">
                    <el-form-item
                            label="用户名"
                            prop="name"
                            :rules="[
                                { required: true, message: '用户名不能为空'},
                                ]"
                    >
                        <el-input type="text" v-model.number="validateForm.name" placeholder="请输入用户名"
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
                    name: '',
                    password: ''
                }
            };
        },
        methods: {
            submitForm(formName) {
                this.$refs[formName].validate((valid) => {
                    if (valid) {
                        this.$axios.get('')
                        this.$router.push("/manage");
                    } else {
                        console.log('error submit!!');
                        return false;
                    }
                });
            },
            resetForm(formName) {
                this.$refs[formName].resetFields();
            }
        }
    };
</script>