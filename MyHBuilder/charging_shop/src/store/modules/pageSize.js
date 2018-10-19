const state = {
    height: 1,
}

const mutations = {
    updatePageSize_mutations(state, obj) {
        state.height = obj;
    }
}

const actions = {
    updatePageSize_actions(context, obj) {
        context.commit('updatePageSize_mutations', obj);
    }
}

export default {
    state,
    mutations,
    actions
}