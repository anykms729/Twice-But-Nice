import Home from "@/pages/Home.vue";
import Login from "@/pages/Login.vue";
import {createRouter, createWebHistory} from "vue-router/dist/vue-router";
import Cart from "@/pages/Cart.vue";
import Order from "@/pages/Order.vue";
import Orders from "@/pages/Orders.vue";
import AddItem from "@/pages/AddItem.vue";
import DeleteItem from "@/pages/DeleteItem.vue";
import SendMessageModal from "@/pages/SendMessageModal.vue";

const routes = [
    {path: '/', component: Home},
    {path: '/login', component: Login},
    {path: '/cart', component: Cart},
    {path: '/order', component: Order},
    {path: '/orders', component: Orders},
    {path: '/addItem', component: AddItem},
    {path: '/deleteItem', component: DeleteItem},
    {path: '/sendMessageModal', component: SendMessageModal}
]

const router = createRouter(
    {
        history: createWebHistory(),
        routes
    }
)
export default router;
