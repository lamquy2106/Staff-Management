const { default: api } = require("./api");

const getAll = () => {
  return api.get(api.url.project).then((res) => res.data);
};

const add = (data, staffId) => {
  return api
    .post(`${api.url.baseUrl}/staff/${staffId}/project`, data)
    .then((res) => res.data);
};

const getById = (id) =>
  api.get(`${api.url.project}/${id}`).then((res) => res.data);

const update = (id, data, staffId) =>
  api
    .put(`${api.url.baseUrl}/staff/${staffId}/project/${id}`, data)
    .then((res) => res.data);

const remove = (id) =>
  api.delete(`${api.url.project}/${id}`).then((res) => res.data);
const projectService = {
  getAll,
  add,
  getById,
  update,
  remove,
};

export default projectService;
