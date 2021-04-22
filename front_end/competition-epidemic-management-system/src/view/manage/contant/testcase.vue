<template>
    <el-main>
        <el-table :data="list">
            <el-table-column prop="userId" label="人员id"  width="100">
            </el-table-column>
            <el-table-column prop="name" label="姓名" width="120">
            </el-table-column>
            <el-table-column prop="unit" label="所属单位" width="180">
            </el-table-column>
            <el-table-column prop="idNumber" label="身份证号" width="180">
            </el-table-column>
            <el-table-column prop="comeFrom" label="来源地">
            </el-table-column>
        </el-table>
    </el-main>
</template>

<script>
    export default {
        name: "testcase",
        data() {
            return {
                list: null,
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
            }
        }
    }
</script>

<style scoped>

</style>