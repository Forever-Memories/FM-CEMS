<template>
    <el-main>
        <el-table :data="list.slice((currentPage-1)*pageSize,currentPage*pageSize)">
            <el-table-column prop="notice" label="通知内容" width="1000">
            </el-table-column>
            <el-table-column prop="time" label="通知时间" width="300">
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
        name: "notice",
        inject: ['reload'],
        data() {
            return {
                list: null,
                currentPage: 1,
                pageSize:10,
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
            handleSizeChange: function (size) {
                this.pageSize = size;
                console.log(this.pageSize)  //每页下拉显示数据
            },
            handleCurrentChange: function(currentPage){
                this.currentPage = currentPage;
                console.log(this.currentPage)  //点击第几页
            },
        }
    }
</script>

<style scoped>

</style>