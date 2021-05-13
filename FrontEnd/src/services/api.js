import axios from "axios";
import store from "./../store/index";

const url = {
  baseUrl: "http://localhost:8080/api",
  staffStatus: "/staffstatus",
  project: "/project",
  room: "/room",
  staff: "/staff",
  violation: "/violation",
  salary: "/salary",
  timeKeeping: "timekeeping",
  staffRoom: "staffroom",
  roomProject: "roomproject",
  salaryInMonth: "salaryinmonth",
  timeKeepingArray: "timekeepingarray",
  login: "/signin",
};

// const instance = axios.create({
//   baseURL: url.baseUrl,
//   headers: {
//     "Content-Type": "application/json",
//     Accept: "application/json",
//   },
// });

// instance.interceptors.request.use((request) => {
//   const state = store.getState();
//   if (state.auth.token) {
//     request.headers.Authorization = `Bearer ${state.auth.token}`;
//   }
//   return request;
// });

// const api = {
//   url,
//   instance,
//   get: instance.get,
//   post: instance.post,
//   put: instance.put,
//   delete: instance.delete,
// };

const instance = axios.create({
  baseURL: url.baseUrl,
  headers: {
    "Content-Type": "application/json",
    Accept: "application/json",
  },
});

instance.interceptors.request.use((request) => {
  const state = store.getState();
  console.log(state);
  if (state.auth.token) {
    request.headers.Authorization = `Bearer ${state.auth.token}`;
  }
  return request;
});

const api = {
  url,
  instance,
  get: instance.get,
  post: instance.post,
  put: instance.put,
  delete: instance.delete,
};

export default api;
