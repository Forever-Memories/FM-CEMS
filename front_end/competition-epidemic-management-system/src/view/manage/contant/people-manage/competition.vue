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
                <el-button type="primary" @click="handleCreateCompetition" plain>新增赛项</el-button>
                <el-dialog
                        title="新增赛项"
                        :visible.sync="dialogCreateCompetitionVisible"
                        width="100%">
                    <el-form ref="createCompetitionFrom" :model="createCompetitionFrom" label-width="80px" size="mini">
                        <el-form-item label="赛项名">
                            <el-input v-model="createCompetitionFrom.name"></el-input>
                        </el-form-item>
                        <el-form-item label="场地">
                            <el-select v-model="createCompetitionFrom.placeId" placeholder="请选择场地">
                                <el-option
                                        v-for="item in placeList"
                                        :key="item.id"
                                        :label="item.name"
                                        :value="item.id">
                                </el-option>
                            </el-select>
                        </el-form-item>
                        <el-form-item label="开始时间">
                            <el-date-picker type="datetime" placeholder="选择赛项开始时间" v-model="createCompetitionFrom.startTime" value-format="timestamp" style="width: 100%;"></el-date-picker>
                        </el-form-item>
                        <el-form-item label="结束时间">
                            <el-date-picker type="datetime" placeholder="选择赛项结束时间" v-model="createCompetitionFrom.endTime" value-format="timestamp" style="width: 100%;"></el-date-picker>
                        </el-form-item>
                    </el-form>
                    <span slot="footer" class="dialog-footer">
    <el-button @click="dialogCreateCompetitionVisible = false">取 消</el-button>
    <el-button type="primary" @click="createCompetition('createCompetitionFrom')">确 定</el-button>
  </span>
                </el-dialog>
                <el-dialog
                        title="编辑赛事信息"
                        :visible.sync="dialogEditFormVisible"
                        width="50%">
                    <el-form ref="editFrom" :model="editFrom" label-width="80px" size="mini">
                        <el-form-item label="赛项名">
                            <el-input v-model="editFrom.name"></el-input>
                        </el-form-item>
                        <el-form-item label="场地">
                            <el-select v-model="editFrom.placeId" placeholder="请选择场地">
                                <el-option
                                        v-for="item in placeList"
                                        :key="item.id"
                                        :label="item.name"
                                        :value="item.id">
                                </el-option>
                            </el-select>
                        </el-form-item>
                        <el-form-item label="开始时间">
                            <el-date-picker type="datetime" placeholder="选择赛项开始时间" v-model="editFrom.startTime" value-format="timestamp" style="width: 100%;"></el-date-picker>
                        </el-form-item>
                        <el-form-item label="结束时间">
                            <el-date-picker type="datetime" placeholder="选择赛项结束时间" v-model="editFrom.endTime" value-format="timestamp" style="width: 100%;"></el-date-picker>
                        </el-form-item>
                    </el-form>
                    <span slot="footer" class="dialog-footer">
    <el-button @click="dialogEditFormVisible = false">取 消</el-button>
    <el-button type="primary" @click="edit('editFrom')">确 定</el-button>
  </span>
                </el-dialog>
                <el-button type="primary" @click="handleCreatePlace" plain>新增场地</el-button>
                <el-dialog
                        title="新增场地"
                        :visible.sync="dialogCreatePlaceVisible"
                        width="30%">
                    <el-form ref="createPlaceFrom" :model="createPlaceFrom" label-width="80px" size="mini">
                        <el-form-item label="赛场名">
                            <el-input v-model="createPlaceFrom.placeName"></el-input>
                        </el-form-item>
                    </el-form>
                    <span slot="footer" class="dialog-footer">
    <el-button @click="dialogCreatePlaceVisible = false">取 消</el-button>
    <el-button type="primary" @click="createPlace('createPlaceFrom')">确 定</el-button>
  </span>
                </el-dialog>
            </el-form-item>
        </el-form>
        <el-table :data="competitionList.slice((currentPage-1)*pageSize,currentPage*pageSize)">
            <el-table-column prop="id" label="编号" width="120">
            </el-table-column>
            <el-table-column prop="name" label="项目" width="180">
            </el-table-column>
            <el-table-column prop="placeName" label="场馆" width="180">
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
                    <el-button @click="handleEdit(scope.row)" type="text" size="small">编辑</el-button>
                    <el-popover
                            placement="top"
                            width="160"
                            ref="deleteVisible">
                        <p>危险操作！确定删除该项比赛吗？</p>
                        <div style="text-align: right; margin: 0">
                            <el-button type="danger" size="mini" @click="deleteCompetitionId(scope.row.id)" >确定</el-button>
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
                :total="this.competitionList.length">
        </el-pagination>
    </el-main>
</template>

<script>
    export default {
        name: "competition",
        inject: ['reload'],
        data() {
            return {
                deleteVisible: false,
                competitionList: null,
                currentPage: 1,
                pageSize:10,
                placeList: null,
                formInline: {
                    competitionName: '',
                    place: null
                },
                dialogCreatePlaceVisible: false,
                dialogCreateCompetitionVisible: false,
                dialogEditFormVisible: false,
                createPlaceFrom: {
                    "placeName": ""
                },
                createCompetitionFrom: {
                    "endTime": new Date().getTime(),
                    "name": "",
                    "placeId": null,
                    "startTime": new Date().getTime()
                },
                editFrom: {
                    "endTime": new Date().getTime(),
                    "id": null,
                    "name": "",
                    "placeId": null,
                    "startTime": new Date().getTime()
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
            handleSizeChange: function (size) {
                this.pageSize = size;
                console.log(this.pageSize)  //每页下拉显示数据
            },
            handleCurrentChange: function(currentPage){
                this.currentPage = currentPage;
                console.log(this.currentPage)  //点击第几页
            },
            onSubmit() {
                console.log('submit!');
            },
            handleCreatePlace() {
                this.createPlaceFrom = {
                    "placeName": ""
                };
                this.dialogCreatePlaceVisible = true;
            },
            createPlace(formPlace) {
                this.$refs[formPlace].validate((valid) => {
                    if (valid) {
                        this.$axios.post('/competition-epidemic/competition/create-place',
                            {
                                "name": this.createPlaceFrom.placeName
                            })
                    } else {
                        this.$message.error('创建场地失败');
                    }
                });
                this.dialogCreatePlaceVisible = false;
                this.reload()
            },
            handleCreateCompetition() {
                this.getAllPlaceList();
                this.createCompetitionFrom = {
                    "endTime": new Date().getTime(),
                    "name": "",
                    "placeId": null,
                    "startTime": new Date().getTime()
                }
                this.dialogCreateCompetitionVisible = true;
            },
            handleEdit(row) {
                this.editFrom = {
                    "endTime": Date.parse(new Date(row.endTime)),
                    "id": row.id,
                    "name": row.name,
                    "placeId": row.placeId,
                    "startTime": Date.parse(new Date(row.startTime))
                };
                this.dialogEditFormVisible = true;
            },
            edit(formEdit) {
                this.$refs[formEdit].validate((valid) => {
                    if (valid) {
                        console.log(this.editFrom)
                        if(this.editFrom.startTime >= this.editFrom.endTime) {
                            this.$message.error('赛事结束时间不晚于开始时间，请设置正确的比赛时间');
                        } else {
                            this.$axios.post('/competition-epidemic/competition/edit',
                                {
                                    "endTime": this.editFrom.endTime,
                                    "id": this.editFrom.id,
                                    "name": this.editFrom.name,
                                    "placeId": this.editFrom.placeId,
                                    "startTime": this.editFrom.startTime
                                }
                            );
                        }
                        this.reload();
                    }else {
                        this.$message.error('编辑赛事信息失败');
                    }
                });
                this.dialogEditFormVisible = false
            },
            createCompetition(formCompetition) {
                this.$refs[formCompetition].validate((valid) => {
                    if (valid) {
                        if(this.createCompetitionFrom.startTime >= this.createCompetitionFrom.endTime) {
                            this.$message.error('赛事结束时间不晚于开始时间，请设置正确的比赛时间');
                        } else {
                            this.$axios.post('/competition-epidemic/competition/create',
                                {
                                    "endTime": this.createCompetitionFrom.endTime,
                                    "name": this.createCompetitionFrom.name,
                                    "placeId": this.createCompetitionFrom.placeId,
                                    "startTime": this.createCompetitionFrom.startTime
                                })
                        }
                    } else {
                        this.$message.error('创建场地失败');
                    }
                });
                this.dialogCreateCompetitionVisible = false;
                this.reload();
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
            deleteCompetitionId(competitionId) {
                this.$axios.post('/competition-epidemic/competition/delete',
                    {
                        "competitionId": competitionId
                    })
                this.deleteVisible = false;
                this.reload();
            }
        }
    }
</script>

<style scoped>

</style>