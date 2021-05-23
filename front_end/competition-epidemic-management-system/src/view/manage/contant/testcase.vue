<template>
    <el-main>
        <el-table :data="list">
            <el-table-column prop="notice" label="通知内容" width="1000">
            </el-table-column>
            <el-table-column prop="time" label="通知时间" width="300">
            </el-table-column>
        </el-table>
    </el-main>
</template>

<script>
    export default {
        name: "testcase",
        inject: ['reload'],
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
                this.$axios.get('/competition-epidemic/notice/all-info').then((res) => {
                    console.log(res)
                    this.list = res.data
                })
            },
        }
    }
</script>

<style scoped>

</style>