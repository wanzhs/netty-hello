const state = {
    loginState: 1
}

const mutations = {
    updateLoginState_mutations(state, obj) {
        state.loginState = obj
    }
}

const actions = {
    updateLoginState_action(context, obj) {
        context.commit('updateLoginState_mutations', obj);
    }
}

export default {
    state,
    mutations,
    actions
}