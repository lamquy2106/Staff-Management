import Home from "./pages/home";
import StaffStatus from "./pages/staffStatus";
import Project from "./pages/project";
import Room from "./pages/room";
import Violation from "./pages/violation";
import Staff from "./pages/staff";
import TimeKeeping from "./pages/timeKeeping";
import StaffRoom from "./pages/staffRoom";
import RoomProject from "./pages/roomProject";
import Salary from "./pages/salary";
import SalaryInMonth from "./pages/salaryInMonth";
import TimeKeepingArray from "./pages/timeKeepingArray";
import Login from "./pages/login";

const routes = [
  { path: "/login", exact: true, name: "Login", component: Login },
  { path: "/", exact: true, name: "Home", component: Home },
  { path: "/home", exact: true, name: Home, component: Home },
  {
    path: "/staffstatus",
    exact: true,
    name: StaffStatus,
    component: StaffStatus,
  },
  { path: "/project", exact: true, name: Project, component: Project },
  { path: "/room", exact: true, name: Room, component: Room },
  { path: "/violation", exact: true, name: Violation, component: Violation },
  { path: "/staff", exact: true, name: Staff, component: Staff },

  {
    path: "/timekeeping",
    exact: true,
    name: TimeKeeping,
    component: TimeKeeping,
  },
  { path: "/staffroom", exact: true, name: StaffRoom, component: StaffRoom },
  { path: "/salary", exact: true, name: Salary, component: Salary },
  {
    path: "/roomproject",
    exact: true,
    name: RoomProject,
    component: RoomProject,
  },
  {
    path: "/salaryinmonth",
    exact: true,
    name: SalaryInMonth,
    component: SalaryInMonth,
  },
  {
    path: "/timekeepingarray",
    exact: true,
    name: TimeKeepingArray,
    component: TimeKeepingArray,
  },

  // {
  //   path: "/dishcategory",
  //   exact: true,
  //   name: DishCategory,
  //   component: DishCategory,
  // },
  // {
  //   path: "/orderdetails",
  //   exact: true,
  //   name: OrderDetails,
  //   component: OrderDetails,
  // },
  // { path: "/customer", exact: true, name: Customer, component: Customer },
  // { path: "/order", exact: true, name: Order, component: Order },
  // { path: "/staff", exact: true, name: Staff, component: Staff },
  // { path: "/ingredient", exact: true, name: Ingredient, component: Ingredient },
  // {
  //   path: "/ingredientcategory",
  //   exact: true,
  //   name: IngredientCategory,
  //   component: IngredientCategory,
  // },
  // { path: "/table", exact: true, name: Table, component: Table },
  // { path: "/login", exact: true, name: Login, component: Login },
  // { path: "/area", exact: true, name: Area, component: Area },
  // {
  //   path: "/customerorder",
  //   exact: true,
  //   name: CustomerOrder,
  //   component: CustomerOrder,
  // },
  // { path: "/revenue", exact: true, name: Revenue, component: Revenue },
];

export default routes;
