<template>
    <el-main>
        <el-button type="primary" @click="handleCreate" plain>创建组织活动安排</el-button>
        <el-dialog
                title="创建组织活动安排"
                :visible.sync="dialogCreateFormVisible"
                width="30%">
            <el-form ref="createFrom" :model="createFrom" label-width="80px" size="mini" label-position="left">
                <el-form-item label="组织人员id">
                    <el-input v-model="createFrom.userId"></el-input>
                </el-form-item>
                <el-form-item label="活动赛场">
                    <el-select v-model="createFrom.placeId" placeholder="请选择场地">
                        <el-option
                                v-for="item in placeList"
                                :key="item.id"
                                :label="item.name"
                                :value="item.id">
                        </el-option>
                    </el-select>
                </el-form-item>
                <el-form-item label="开始时间">
                    <el-date-picker type="datetime" placeholder="选择活动开始时间" v-model="createFrom.startTime" value-format="timestamp" style="width: 100%;"></el-date-picker>
                </el-form-item>
                <el-form-item label="结束时间">
                    <el-date-picker type="datetime" placeholder="选择活动结束时间" v-model="createFrom.endTime" value-format="timestamp" style="width: 100%;"></el-date-picker>
                </el-form-item>
            </el-form>
            <span slot="footer" class="dialog-footer">
    <el-button @click="dialogCreateFormVisible = false">取 消</el-button>
    <el-button type="primary" @click="createArrange('createFrom')">确 定</el-button>
  </span>
        </el-dialog>
        <el-table :data="list.slice((currentPage-1)*pageSize,currentPage*pageSize)">
            <el-table-column prop="userName" label="组织人员" width="120">
            </el-table-column>
            <el-table-column prop="placeName" label="活动场地" width="200">
            </el-table-column>
            <el-table-column prop="startTime" label="开始时间" width="200">
            </el-table-column>
            <el-table-column prop="endTime" label="结束时间" width="400">
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
                        <p>确定删除该组织活动安排吗？</p>
                        <div style="text-align: right; margin: 0">
                            <el-button type="danger" size="mini" @click="deleteById(scope.row.id)" >确定</el-button>
                        </div>
                        <el-button slot="reference" @click="deleteVisible = true" type="danger" size="small">删除</el-button>
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
        name: "organizeArrangement",
        inject: ['reload'],
        data() {
            return {
                list: null,
                currentPage: 1,
                pageSize:10,
                placeList: null,
                dialogCreateFormVisible: false,
                deleteVisible: false,
                createFrom: {
                    "userId": null,
                    "placeId": null,
                    "startTime": new Date().getTime(),
                    "endTime": new Date().getTime()
                },
            }
        },
        created() {
            this.getData();
            this.getAllPlaceList()
        },
        methods: {
            getData() {
                this.$axios.get('/competition-epidemic/organize-arrangement/all-info').then((res) => {
                    console.log(res)
                    this.list = res.data
                })
            },
            getAllPlaceList() {
                this.$axios.get('/competition-epidemic/competition/all-place').then((res) => {
                    console.log(res)
                    this.placeList = res.data
                })
            },
            handleCreate() {
                this.createFrom = {
                    "userId": null,
                    "placeId": null,
                    "startTime": new Date().getTime(),
                    "endTime": new Date().getTime()
                };
                this.dialogCreateFormVisible = true;
            },
            handleSizeChange: function (size) {
                this.pageSize = size;
                console.log(this.pageSize)  //每页下拉显示数据
            },
            handleCurrentChange: function(currentPage){
                this.currentPage = currentPage;
                console.log(this.currentPage)  //点击第几页
            },
            createArrange(createFrom) {
                this.$refs[createFrom].validate((valid) => {
                    if (valid) {
                        this.$axios.post('/competition-epidemic/organize-arrangement/create',
                            {
                                "userId": this.createFrom.userId,
                                "placeId": this.createFrom.placeId,
                                "startTime": this.createFrom.startTime,
                                "endTime": this.createFrom.endTime
                            })
                    } else {
                        this.$message.error('创建组织活动安排失败');
                    }
                });
                this.dialogCreateFormVisible = false;
                this.reload();
            },
            deleteById(id) {
                this.$axios.post('/competition-epidemic/organize-arrangement/delete',
                    {
                        "id": id
                    })
                this.deleteVisible = false;
                this.reload();
            },
        }
    }
</script>

<style scoped>

</style>