const { default: api } = require("./api");

const getAll = () => {
  return api.get(api.url.timeKeeping).then((res) => res.data);
};

const add = (data, staffId) => {
  return api
    .post(`${api.url.baseUrl}/staff/${staffId}/timekeeping`, data)
    .then((res) => res.data);
};

const getById = (id) =>
  api.get(`${api.url.timeKeeping}/${id}`).then((res) => res.data);

const update = (id, data, staffId) =>
  api
    .put(`${api.url.baseUrl}/staff/${staffId}/timekeeping/${id}`, data)
    .then((res) => res.data);

const remove = (id) =>
  api.delete(`${api.url.timeKeeping}/${id}`).then((res) => res.data);
const timeKeepingService = {
  getAll,
  add,
  getById,
  update,
  remove,
};

export default timeKeepingService;
