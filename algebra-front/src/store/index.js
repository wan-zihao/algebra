import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

export default new Vuex.Store({
  state: {
    currentUserId: '',
    currentUsername: '',
    questionId: '',
    questionTitle: '',
    questionUser: '',
    questionTime: '',
    questionContent: '',
    questionFlag: false
  },
  mutations: {
    modifyCurrentUserId (state, val) {
      state.currentUserId = val
    },
    modifyCurrentUsername (state, val) {
      state.currentUsername = val
    },
    modifyQuestionTitle (state, val) {
      state.questionTitle = val
    },
    modifyQuestionUser (state, val) {
      state.questionUser = val
    },
    modifyQuestionTime (state, val) {
      state.questionTime = val
    },
    modifyQuestionContent (state, val) {
      state.questionContent = val
    },
    modifyQuestionFlag (state, val) {
      state.questionFlag = val
    },
    modifyQuestionId (state, val) {
      state.questionId = val
    }
  },
  actions: {
  },
  modules: {
  }
})
