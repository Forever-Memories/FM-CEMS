<template>
    <el-main>
        <el-form :inline="true" :model="formInline" class="demo-form-inline">
            <el-form-item label="赛项名称">
                <el-input v-model="formInline.competitionName" placeholder="赛项名称"></el-input>
            </el-form-item>
            <el-form-item label="比赛场地">
                <el-select v-model="formInline.place" placeholder="比赛场地">
                    <el-option label="区域一" value="shanghai"></el-option>
                    <el-option label="区域二" value="beijing"></el-option>
                </el-select>
            </el-form-item>
            <el-form-item>
                <el-button type="primary" @click="onSubmit">查询</el-button>
                <el-button type="primary" @click="handleCreateCompetition" plain>新增赛项</el-button>
                <el-button type="primary" @click="handleCreatePlace" plain>新增场地</el-button>
            </el-form-item>
        </el-form>
        <el-table :data="competitionList">
            <el-table-column prop="id" label="编号" width="120">
            </el-table-column>
            <el-table-column prop="name" label="项目" width="180">
            </el-table-column>
            <el-table-column prop="placeName" label="场管" width="180">
            </el-table-column>
            <el-table-column prop="startTime" label="开始时间" width="180">
            </el-table-column>
            <el-table-column prop="startTime" label="结束时间" width="180">
            </el-table-column>
            <el-table-column
                    fixed="right"
                    label="操作"
                    width="200">
                <template slot-scope="scope">
                    <el-button  @click="handleClick(scope.row)" type="text" size="small">编辑</el-button>
                    <el-button type="danger" size="small">删除</el-button>
                </template>
            </el-table-column>
        </el-table>
    </el-main>
</template>

<script>
    export default {
        name: "competition",
        data() {
            return {
                competitionList: null,
                placeList: null,
                formInline: {
                    competitionName: '',
                    place: ''
                },
                dialogCreatePlaceVisible: false,
                dialogCreateCompetitionVisible: false,
                createPlaceFrom: {
                    "placeName": ""
                },
                createCompetitionFrom: {
                    "endTime": new Date().getTime(),
                    "name": "",
                    "placeId": 0,
                    "startTime": new Date().getTime()
                }
            }
        },
        created() {
            this.getAllCompetition();
        },
        methods: {
            getAllCompetition() {
                this.$axios.get('/competition-epidemic/competition/all-competition').then((res) => {
                    console.log(res)
                    this.competitionList = res.data
                })
            },
            getAllPlaceList() {
                this.$axios.get('/competition-epidemic/competition/all-place').then((res) => {
                    console.log(res)
                    this.placeList = res.data
                })
            },
            onSubmit() {
                console.log('submit!');
            },
            handleCreatePlace() {
                this.createPlaceFrom = {
                    "placeName": ""
                };
                this.createPlaceFrom = true;
            },
            createPlace() {
                this.createPlaceFrom = false;
            },
            handleCreateCompetition() {
                this.createCompetitionFrom = {
                    "endTime": new Date().getTime(),
                        "name": "",
                        "placeId": 0,
                        "startTime": new Date().getTime()
                }
            }
        }
    }
</script>

<style scoped>

</style>