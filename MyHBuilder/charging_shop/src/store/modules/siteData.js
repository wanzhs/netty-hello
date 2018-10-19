const state = {
    poleData: null, //充电桩数据
    siteData: null, //充电站数据
}

const mutations = {
    updateSiteData_mutations(state, obj) {
        state.siteData = obj.siteData;
        state.poleData = obj.poleData;
    }
}

const actions = {
    updateSiteData_action(context, obj) {
        context.commit('updateSiteData_mutations', obj);
    }
}

export default {
    state,
    mutations,
    actions
}