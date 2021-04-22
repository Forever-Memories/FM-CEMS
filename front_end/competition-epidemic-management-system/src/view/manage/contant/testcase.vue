<template>
    <el-main>
        <el-table :data="tableData">
            <el-table-column prop="user_id" label="人员id"  width="100">
            </el-table-column>
            <el-table-column prop="name" label="姓名" width="120">
            </el-table-column>
            <el-table-column prop="unit" label="所属单位" width="180">
            </el-table-column>
            <el-table-column prop="id_number" label="身份证号">
            </el-table-column>
        </el-table>
    </el-main>
</template>

<script>
    export default {
        name: "testcase",
        data() {
            const item = {
                user_id: '0',
                name: '朱浙庆',
                unit: '安徽大学计科院',
                id_number: '12345678'
            };
            return {
                list: null,
                tableData: Array(20).fill(item)
            }
        },
        created() {

        },
        methods: {
            getData() {
                this.$axios.get('/competition-epidemic/participant-info/all-infos').then(() => {
                    this.list = [{
                        user_id: '0',
                        name: '朱浙庆',
                        unit: '安徽大学计科院',
                        id_number: '12345678'
                    }]
                })
            }
        }
    }
</script>

<style scoped>

</style>