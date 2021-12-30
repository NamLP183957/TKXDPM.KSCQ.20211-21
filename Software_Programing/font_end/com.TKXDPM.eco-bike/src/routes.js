
import DashboardPage from "views/Dashboard/Dashboard.js";
import Employees from "./views/Employees/Employees";
import Customers from "./views/Customer/Customer";




const dashboardRoutes = [
  {
    path: "/dashboard",
    name: "Danh sách bãi xe",
    component: DashboardPage,
    layout: "/admin",
  },
  {
    path: "/employees",
    name: "Danh sách xe đang thuê",
    component: Employees,
    layout: "/admin",
    
  },
  {
    path: "/customers",
    name: "Thuê xe",
    component: Customers,
    layout: "/admin",
  }
];

export default dashboardRoutes;
