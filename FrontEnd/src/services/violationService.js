const { default: api } = require("./api");

const getAll = () => {
  return api.get(api.url.violation).then((res) => res.data);
};

const add = (data) => {
  return api.post(api.url.violation, data).then((res) => res.data);
};

const getById = (id) =>
  api.get(`${api.url.violation}/${id}`).then((res) => res.data);

const update = (id, data) =>
  api.put(`${api.url.violation}/${id}`, data).then((res) => res.data);

const remove = (id) =>
  api.delete(`${api.url.violation}/${id}`).then((res) => res.data);
const violationService = {
  getAll,
  add,
  getById,
  update,
  remove,
};

export default violationService;
