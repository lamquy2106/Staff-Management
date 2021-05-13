const { default: api } = require("./api");

const getAll = () => {
  return api.get(api.url.salary).then((res) => res.data);
};

const add = (data, staffId) => {
  return api
    .post(`${api.url.baseUrl}/staff/${staffId}/salary`, data)
    .then((res) => res.data);
};

const getById = (id) =>
  api.get(`${api.url.salary}/${id}`).then((res) => res.data);

const update = (id, data, staffId) =>
  api
    .put(`${api.url.baseUrl}/staff/${staffId}/salary/${id}`, data)
    .then((res) => res.data);

const remove = (id) =>
  api.delete(`${api.url.salary}/${id}`).then((res) => res.data);
const salaryService = {
  getAll,
  add,
  getById,
  update,
  remove,
};

export default salaryService;
