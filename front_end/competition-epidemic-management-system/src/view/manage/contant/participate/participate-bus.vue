<template>
    <el-main>
        <el-table :data="list">
            <el-table-column prop="id" label="班车编号" width="120">
            </el-table-column>
            <el-table-column prop="placeName" label="地点" width="200">
            </el-table-column>
            <el-table-column prop="time" label="时间" width="200">
            </el-table-column>
            <el-table-column prop="arrangement" label="安排信息" width="200">
            </el-table-column>
            <el-table-column prop="comment" label="备注" width="2000">
            </el-table-column>
        </el-table>
    </el-main>
</template>

<script>
    export default {
        name: "participateBus",
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
                this.$axios.get('/competition-epidemic/bus/all-info').then((res) => {
                    console.log(res)
                    this.list = res.data
                })
            },
        }
    }
</script>

<style scoped>

</style>