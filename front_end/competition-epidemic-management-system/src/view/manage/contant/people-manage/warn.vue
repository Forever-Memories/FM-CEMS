<template>
    <el-main>
        <el-table :data="list.slice((currentPage-1)*pageSize,currentPage*pageSize)">
            <el-table-column prop="userId" label="人员id" width="120">
            </el-table-column>
            <el-table-column prop="userName" label="人员姓名" width="120">
            </el-table-column>
            <el-table-column prop="unit" label="所属单位" width="180">
            </el-table-column>
            <el-table-column prop="isCough" label="是否咳嗽" width="180">
            </el-table-column>
            <el-table-column prop="isHistory" label="是否有疫病史" width="180">
            </el-table-column>
            <el-table-column prop="isTough" label="是否有亲密接触" width="180">
            </el-table-column>
            <el-table-column
                    fixed="right"
                    label="操作"
                    width="200">
                <template slot-scope="scope">
                    <el-popover
                            placement="top"
                            width="160"
                            ref="deleteVisible">
                        <p>确定移除该异常信息吗？</p>
                        <div style="text-align: right; margin: 0">
                            <el-button type="success" size="mini" @click="deleteById(scope.row.id)" >确定</el-button>
                        </div>
                        <el-button slot="reference" @click="deleteVisible = true" type="success" size="small">移除</el-button>
                    </el-popover>
                </template>
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
        name: "warn",
        inject: ['reload'],
        data() {
            return {
                list: null,
                currentPage: 1,
                pageSize:10,
                deleteVisible: false,
            }
        },
        created() {
            this.getData();
        },
        methods: {
            getData() {
                this.$axios.get('/competition-epidemic/warn/all-info').then((res) => {
                    console.log(res)
                    this.list = res.data
                })
            },
            deleteById(id) {
                this.$axios.post('/competition-epidemic/warn/delete',
                    {
                        "id": id
                    })
                this.deleteVisible = false;
                this.reload();
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