<template>
    <el-main>
        <el-table :data="list">
            <el-table-column prop="userName" label="组织人员" width="120">
            </el-table-column>
            <el-table-column prop="placeName" label="活动场地" width="200">
            </el-table-column>
            <el-table-column prop="startTime" label="开始时间" width="200">
            </el-table-column>
            <el-table-column prop="endTime" label="结束时间" width="200">
            </el-table-column>
        </el-table>
    </el-main>
</template>

<script>
    export default {
        name: "participateArrangement",
        inject: ['reload'],
        data() {
            return {
                list: null,
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
                this.$axios.post('/competition-epidemic/organize-arrangement/info',
                    {
                        "userId": localStorage.getItem('userId')
                    }).then((res) => {
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