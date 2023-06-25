import {createApp} from 'vue'
import router from "@/script/router"
import App from './App.vue'
import store from "@/script/store"

createApp(App).use(store).use(router).mount('#app')
