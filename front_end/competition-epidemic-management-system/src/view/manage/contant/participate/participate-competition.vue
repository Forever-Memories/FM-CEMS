<template>
    <el-main>
        <el-form :inline="true" :model="formInline" class="demo-form-inline">
            <el-form-item label="赛项名称">
                <el-input v-model="formInline.competitionName" placeholder="赛项名称"></el-input>
            </el-form-item>
            <el-form-item label="比赛场地">
                <el-select v-model="formInline.place" placeholder="比赛场地">
                    <el-option
                            v-for="item in placeList"
                            :key="item.id"
                            :label="item.name"
                            :value="item.id">
                    </el-option>
                </el-select>
            </el-form-item>
            <el-form-item>
                <el-button type="primary" @click="search()">查询</el-button>
            </el-form-item>
        </el-form>
        <el-table :data="competitionList">
            <el-table-column prop="id" label="编号" width="120">
            </el-table-column>
            <el-table-column prop="name" label="项目" width="180">
            </el-table-column>
            <el-table-column prop="placeName" label="场馆" width="180">
            </el-table-column>
            <el-table-column prop="startTime" label="开始时间" width="180">
            </el-table-column>
            <el-table-column prop="startTime" label="结束时间" width="1800">
            </el-table-column>
        </el-table>
    </el-main>
</template>

<script>
    export default {
        name: "participateCompetition",
        inject: ['reload'],
        data() {
            return {
                competitionList: null,
                placeList: null,
                formInline: {
                    competitionName: '',
                    place: null
                }
            }
        },
        created() {
            this.getAllCompetition();
            this.getAllPlaceList();
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
            search() {
                console.log(this.formInline)
                this.$axios.post('/competition-epidemic/competition/search',
                    {
                        competitionName: this.formInline.competitionName,
                        placeId: this.formInline.place
                    }).then((res) => {
                    this.competitionList = res.data
                });
            },
        }
    }
</script>

<style scoped>

</style>