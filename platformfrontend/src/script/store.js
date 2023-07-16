import {createStore} from 'vuex'

// A Vuex store instance is an object that represents the centralized state management in a Vue.js application using Vuex.
// It serves as a single source of truth for the application's data, which can be accessed and modified from different components.
const store = createStore({
    // The state represents the data in the store.
    // It is a reactive object that holds the application's global state. The state should only be modified by committing mutations.
    state() {
        return {
            account: {
                id: 0
            }
        }
    },
    // Create some functions
    mutations: {
        setAccount(state, payload) {
            state.account.id = payload;
        }
    }
})

export default store;
