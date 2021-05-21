<template>
  <div>
    <mavon-editor
      :placeholder="config.placeholder"
      :toolbarsFlag="config.toolbarsFlag"
      :toolbars="toolbars"
      style="height:500px"
      v-model="markdown.value"
      @change="change"
      ref=md
      @save="saveBefore"
    ></mavon-editor>
    <!--提交模态框-->
    <el-dialog title="输入文章标题" :visible.sync="dialogVisible">
      <el-form :model="markdown" :rules="rules" ref="markdown">
        <el-form-item label="标题" prop="title" :label-width="labelWidth">
          <el-input v-model="markdown.questionTitle" maxlength="30" show-word-limit type="text" auto-complete="false" placeholder="请输入文章标题"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogVisible = false">取 消</el-button>
        <el-button type="primary" @click="save">确 定</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { mavonEditor } from 'mavon-editor'
import 'mavon-editor/dist/css/index.css'
export default {
  name: 'markdown',
  components: {
    mavonEditor
  },
  data () {
    return {
      dialogVisible: false,
      labelWidth: '70px',
      config: {
        placeholder: ' ',
        toolbarsFlag: true
      },
      markdown: {
        value: '',
        questionContent: '',
        questionTitle: ''
      },
      rules: {
        questionTitle: [{ required: true, message: '标题不能为空', trigger: 'blur' }]
      },
      toolbars: {
        bold: true, // 粗体
        italic: true, // 斜体
        header: false, // 标题
        underline: true, // 下划线
        strikethrough: true, // 中划线
        mark: true, // 标记
        superscript: false, // 上角标
        subscript: false, // 下角标
        quote: true, // 引用
        ol: true, // 有序列表
        ul: true, // 无序列表
        imagelink: false, // 图片链接
        upload: false,
        code: true, // code
        table: true, // 表格
        fullscreen: false, // 全屏编辑
        readmodel: true, // 沉浸式阅读
        htmlcode: true, // 展示html源码
        help: true, // 帮助
        /* 1.3.5 */
        undo: true, // 上一步
        redo: true, // 下一步
        trash: true, // 清空
        save: true, // 保存（触发events中的save事件）
        /* 1.4.2 */
        navigation: false, // 导航目录
        /* 2.1.8 */
        alignleft: false, // 左对齐
        aligncenter: false, // 居中
        alignright: false, // 右对齐
        /* 2.2.1 */
        subfield: true, // 单双栏模式
        preview: true // 预览
      }
    }
  },
  methods: {
    // 监听markdown变化
    change (value, render) {
      this.markdown.questionContent = render
    },
    saveBefore () {
      if (this.markdown.value) {
        this.dialogVisible = true
      } else {
        this.$message.error('文章内容不可为空')
      }
    },
    save () {
      this.$refs.markdown.validate((valid) => {
        if (valid) {
          this.postRequest2('/question', this.markdown)
          this.dialogVisible = false
        } else {
          this.$message.error('请正确填写信息哦')
          return false
        }
      })
    }
  }
}
</script>

<style scoped>

</style>
