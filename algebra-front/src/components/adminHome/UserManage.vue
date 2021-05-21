<template>
  <div>
    <div>
      <div style="float:left;">
        <el-input style="width: 500px;" prefix-icon="el-icon-search" type="text" auto-complete="false" v-model="table.search" placeholder="请输入查询条件(条件为空，表示查询所有)" @blur="searchUser"></el-input>
      </div>
      <div style="float:right">
        <el-button type="primary" @click="dialogFormVisibleAdd=true">添加用户</el-button>
        <el-button type="danger" @click="deleteUser()">删除选中</el-button>
      </div>
    </div>
    <template>
      <el-table ref="singleTable"
                :data="this.table.tableData"
                highlight-current-row
                style="width: 100%"
                @selection-change="handleSelectionChange">
        <el-table-column property="userId" v-if="false"></el-table-column>
        <el-table-column type="selection"></el-table-column>
        <el-table-column type="index" width="50" align="center"></el-table-column>
        <el-table-column label="姓名" width="120" align="center">
          <template slot-scope="scope">
            <span style="margin-left: 10px">{{ scope.row.username }}</span>
          </template>
        </el-table-column>
        <el-table-column label="邮箱" align="center">
          <template slot-scope="scope">
            <span style="margin-left: 10px">{{ scope.row.email }}</span>
          </template>
        </el-table-column>
        <el-table-column label="操作" align="center">
          <template slot-scope="scope">
            <el-button type="primary" @click="form.updateUserId = scope.row.userId;
                                              form.username = scope.row.username;
                                              form.email = scope.row.email;
                                              dialogFormVisible = true">修改</el-button>
          </template>
        </el-table-column>
      </el-table>
      <div style="height: 30px">
        <el-pagination
          @current-change="handleCurrentChange"
          @size-change="handleSizeChange"
          :current-page="this.table.currentPage"
          :page-sizes="[5, 7, 10, 15, 20]"
          :page-size=this.table.pageSize
          layout="total, sizes, prev, pager, next, jumper"
          :total=this.table.total>
        </el-pagination>
      </div>
    </template>
    <!--修改用户模态框-->
    <el-dialog title="修改用户信息" :visible.sync="dialogFormVisible">
      <el-form :model="form" :rules="rules" ref="form">
        <el-form-item label="用户名" prop="username" :label-width="formLabelWidth">
          <el-input v-model="form.username" type="text" auto-complete="false" placeholder="请输入修改后的用户名"></el-input>
        </el-form-item>
        <el-form-item label="密码" prop="password" :label-width="formLabelWidth">
          <el-input v-model="form.password" type="password" auto-complete="false" placeholder="请输入修改后的密码" show-password></el-input>
        </el-form-item>
        <el-form-item label="邮箱" prop="email" :label-width="formLabelWidth">
          <el-input v-model="form.email" type="text" auto-complete="false" placeholder="请输入修改后的邮箱"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">取 消</el-button>
        <el-button type="primary" @click="modifyUser">确 定</el-button>
      </div>
    </el-dialog>
    <!--添加用户模态框-->
    <el-dialog title="添加用户信息" :visible.sync="dialogFormVisibleAdd">
      <el-form :model="addForm" :rules="rules" ref="addForm">
        <el-form-item label="用户名" prop="username" :label-width="formLabelWidth">
          <el-input v-model="addForm.username" type="text" auto-complete="false" placeholder="请输入用户名"></el-input>
        </el-form-item>
        <el-form-item label="密码" prop="password" :label-width="formLabelWidth">
          <el-input v-model="addForm.password" type="password" auto-complete="false" placeholder="请输入密码" show-password></el-input>
        </el-form-item>
        <el-form-item label="邮箱" prop="email" :label-width="formLabelWidth">
          <el-input v-model="addForm.email" type="text" auto-complete="false" placeholder="请输入邮箱"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisibleAdd = false">取 消</el-button>
        <el-button type="primary" @click="addUser">确 定</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { Message } from 'element-ui'
const querystring = require('querystring')

export default {
  name: 'UserManage',

  created () {
    // 页面创建时填充数据
    this.getRequest('/user', this.table).then(resp => {
      this.table.total = resp.obj.total
      this.table.tableData = resp.obj.records
    })
  },
  data () {
    return {
      dialogFormVisible: false,
      dialogFormVisibleAdd: false,
      formLabelWidth: '70px',
      table: {
        tableData: [],
        currentPage: 1,
        pageSize: 7,
        total: 0,
        search: '',
        multipleSelection: []
      },
      form: {
        username: '',
        password: '',
        email: '',
        updateUserId: 0
      },
      addForm: {
        username: '',
        password: '',
        email: ''
      },
      rules: {
        username: [{ required: true, message: '请输入用户名', trigger: 'blur' }],
        password: [{ required: true, message: '请输入密码', trigger: 'blur' }],
        email: [{ required: true, message: '请输入邮箱', trigger: 'blur' }]
      }
    }
  },
  methods: {
    // 当前页数改变时的回调函数
    handleCurrentChange (val) {
      this.table.currentPage = val
      this.getRequest('/user', this.table).then(resp => {
        this.table.tableData = resp.obj.records
      })
    },
    // 每页大小改变时的回调函数
    handleSizeChange (val) {
      this.table.pageSize = val
      this.getRequest('/user', this.table).then(resp => {
        this.table.tableData = resp.obj.records
      })
    },
    // 复选框状态改变时的回调函数
    handleSelectionChange (val) {
      this.table.multipleSelection = []
      val.forEach((item) => {
        this.table.multipleSelection.push(item.userId)
      })
    },
    // 查询
    searchUser () {
      this.getRequest('/user', this.table).then(resp => {
        this.table.total = resp.obj.total
        this.table.tableData = resp.obj.records
      })
    },
    // 添加用户
    addUser () {
      this.$refs.addForm.validate(async (valid) => {
        if (valid) {
          await this.postRequest('/user', querystring.stringify(this.addForm))
          await this.getRequest('/user', this.table).then(resp => {
            this.table.total = resp.obj.total
            this.table.tableData = resp.obj.records
            this.addForm.username = ''
            this.addForm.password = ''
            this.addForm.email = ''
          })
          this.dialogFormVisibleAdd = false
        } else {
          this.$message.error('请正确填写信息哦')
          return false
        }
      })
    },
    // 修改用户
    modifyUser () {
      this.$refs.form.validate(async (valid) => {
        if (valid) {
          await this.putRequest('/user/' + this.form.updateUserId, this.form)
          await this.getRequest('/user', this.table).then(resp => {
            this.table.total = resp.obj.total
            this.table.tableData = resp.obj.records
            this.form.username = ''
            this.form.password = ''
            this.form.email = ''
          })
          this.dialogFormVisible = false
        } else {
          this.$message.error('请正确填写信息哦')
          return false
        }
      })
    },
    // 删除选中的用户
    deleteUser () {
      if (Array.from(this.table.multipleSelection).length === 0) {
        Message.error({ message: '请至少选中一项' })
        return
      }
      this.$confirm('此操作将永久删除选中用户, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(async () => {
        await this.deleteRequest('/user', this.table)
        await this.getRequest('/user', this.table).then(resp => {
          this.table.total = resp.obj.total
          this.table.tableData = resp.obj.records
          // 如果当前页数据全被删除，则退回上一页
          if (this.table.total % this.table.pageSize === 0 && this.table.total !== 0) {
            this.table.currentPage--
            this.getRequest('/user', this.table).then(resp => {
              this.table.tableData = resp.obj.records
            })
          }
        })
      }).catch(() => {
        this.$message({
          type: 'info',
          message: '已取消删除'
        })
      })
    }
  }
}
</script>
<style scoped>
.block {
  height: 50px;
}
</style>
